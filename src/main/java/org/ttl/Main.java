package org.ttl;

import org.ttl.calculator.TimeCalculator;
import org.ttl.order.Context;
import org.ttl.process.ProcessOrder;

import static org.ttl.calculator.TimeCalculator.getTime;
import static org.ttl.data.MessageQueue.pushOrder;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        TimeCalculator timeCalculator = new TimeCalculator();
        timeCalculator.start();

        Context context = new Context();
        context.setOrderId(1);
        context.setStockId(1);
        context.setIntervalTime(5);
        context.setBS("B");
        context.setStockId(1);
        context.setQuantity(100);
        context.setPriceQuantity(1);
        context.setDeviation(0.001);
        context.setDuration(100);
        context.setAllowOrderWhenDurationEnd(false);

        Context context1 = new Context();
        context1.setOrderId(2);
        context1.setStockId(1);
        context1.setIntervalTime(5);
        context1.setBS("B");
        context1.setStockId(1);
        context1.setQuantity(100);
        context1.setPriceQuantity(1);
        context1.setDeviation(0.001);
        context1.setDuration(100);
        context1.setAllowOrderWhenDurationEnd(false);

        pushOrder(context);
        pushOrder(context1);

        ProcessOrder processOrder = new ProcessOrder(true);
        processOrder.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown executed");
            timeCalculator.stopThread();
            processOrder.stopThread();
        }));
    }
}