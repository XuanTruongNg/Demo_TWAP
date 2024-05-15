package org.ttl.data;

import org.ttl.order.Context;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MessageQueue
{

    private static BlockingQueue<Context> blockingQueue = new LinkedBlockingDeque<>();

    public static void pushOrder(Context context)
    {
        blockingQueue.add(context);
    }

    public static Context pollOrder()
    {
        return blockingQueue.poll();
    }
}
