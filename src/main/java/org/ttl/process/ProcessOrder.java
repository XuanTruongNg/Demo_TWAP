package org.ttl.process;

import org.ttl.data.Price;
import org.ttl.order.ConditionChecking;
import org.ttl.order.Context;
import org.ttl.order.SystemConditionChecking;
import org.ttl.order.UserConditionChecking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.ttl.calculator.TWAPCalculator.calculateTWAP;
import static org.ttl.calculator.TWAPCalculator.getPriceCurrent;
import static org.ttl.calculator.TimeCalculator.getTime;
import static org.ttl.data.MessageQueue.pollOrder;

public class ProcessOrder extends Thread
{
    private volatile boolean running = true;
    HashMap<Integer, Context> schedulerList = new HashMap<Integer, Context>();
    List<Context>             scheduleList  = new ArrayList<>();
    ConditionChecking         conditionChecking;

    public ProcessOrder(boolean isUserConditionChecking)
    {
        super("ProcessOrderThread");
        if (isUserConditionChecking)
        {
            conditionChecking = new UserConditionChecking();
        }
    }

    public void run()
    {
        while (running)
        {
            // process new message
            Context context = pollOrder();
            if (context != null)
            {
                int time = getTime();
                context.setScheduleTime((int) (time + context.getIntervalTime()));
                context.setTimeStartOrder(time);
                scheduleList.add(context);
            }

            // process scheduler list
            Iterator<Context> iterator = scheduleList.iterator();
            while (iterator.hasNext())
            {
                Context contextSchedule = iterator.next();
                int currentTime = getTime();
                boolean isChecking = false;
                if (currentTime >= contextSchedule.getScheduleTime() && contextSchedule.getLastCheckingTime() != currentTime)
                {
                    double twapPrice = calculateTWAP(Double.valueOf(contextSchedule.getTimeStartOrder()), Double.valueOf(currentTime), contextSchedule.getStockId());
                    if (conditionChecking.isConditionMatching(twapPrice, getPriceCurrent().getMarketPrice(), contextSchedule))
                    {
                        contextSchedule.setScheduleTime((int) (currentTime + contextSchedule.getIntervalTime()));
                        placeOrder(contextSchedule);
                        System.out.println("\u2705 " + "Parent Order Id : " + contextSchedule.getOrderId() + " \u2316 Quantity: " + contextSchedule.getPriceQuantity() + " \uD83D\uDCB5 Price: " + getPriceCurrent().getMarketPrice() + " \uD83D\uDCB5 TWAP: " + twapPrice + " \uD83D\uDD53 Time: " + currentTime);
                        if (contextSchedule.getQuantity() == 0)
                        {
                            iterator.remove();
                            System.out.println("\u2714 Parent Order Id : " + contextSchedule.getOrderId() + " is done at \uD83D\uDD53 Time:  " + currentTime);
                        }
                        else
                        {
                            if (currentTime - contextSchedule.getTimeStartOrder() > contextSchedule.getDuration() && !contextSchedule.isAllowOrderWhenDurationEnd())
                            {
                                System.out.println("❌ Parent Order Id : " + contextSchedule.getOrderId() + " is stopped " + " at \uD83D\uDD53 Time: " + currentTime + " because duration is end " + " \u2316 Total quantity is " + contextSchedule.getQuantity() + " and \u2316 Total quantity ordered is " + contextSchedule.getQuantityOrdered());
                                iterator.remove();
                            }
                        }
                    }
                    else
                    {
                        System.out.println("\u23F3 " + "Parent Order Id : " + contextSchedule.getOrderId() + " is not executed at \uD83D\uDD53 Time: " + currentTime + " Waiting for next schedule");
                    }
                    contextSchedule.setLastCheckingTime(currentTime);
                }
            }
        }

    }

    private void placeOrder(Context context)
    {
        context.setQuantity((context.getQuantity() - context.getPriceQuantity()));
        context.setQuantityOrdered((context.getQuantityOrdered() + context.getPriceQuantity()));
    }

    public void stopThread() {
        running = false;
    }

}
