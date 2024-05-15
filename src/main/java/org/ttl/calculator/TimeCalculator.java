package org.ttl.calculator;

import org.ttl.data.Price;

import java.util.Random;

import static org.ttl.data.DummyData.dummyData;

public class TimeCalculator extends Thread
{
    private static Integer time;
    private        Random  random;
    private volatile boolean running = true;

    public TimeCalculator()
    {
        super("TimeCalculatorThread");
        time = 6;
    }

    @Override
    public void run()
    {
        try
        {
            while (running)
            {
                Thread.sleep(100);
                time++;
                dummyData(time);
            }
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static Integer getTime()
    {
        return time;
    }

    public void stopThread() {
        running = false;
    }
}
