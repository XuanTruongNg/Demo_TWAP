package org.ttl.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;

public class DummyData
{
    public static        List<Price>                   stockAPrice = new CopyOnWriteArrayList<>();
    public static        HashMap<Integer, List<Price>> stockPrice  = new HashMap<>();
    private static final Random                        random;
    private static final Double                        MIN_PRICE   = 100.0;
    private static final Double                        MAX_PRICE   = 120.0;

    static
    {
        stockAPrice.add(new Price(100.0, 100.0, 100.0, 1));
        stockAPrice.add(new Price(101.0, 101.0, 101.0, 3));
        stockAPrice.add(new Price(102.0, 102.0, 102.0, 4));
        stockAPrice.add(new Price(103.0, 103.0, 103.0, 5));
        stockAPrice.add(new Price(104.0, 104.0, 104.0, 6));
        stockPrice.put(1, stockAPrice);
        random = new Random();
    }

    public static void dummyData(int time)
    {
        for (Integer stockId : stockPrice.keySet())
        {

            if ((random.nextInt((4 - 1) + 1) + 1) == 1) // 25% chance to add new price, price fluctuation level
            {
                int randomNumber = (int) (random.nextInt((int) ((MAX_PRICE - MIN_PRICE) + 1)) + MIN_PRICE); // fluctuation amplitude
                if (stockPrice.get(stockId).size() < Integer.MAX_VALUE)
                {
                    stockPrice.get(stockId).add(new Price(randomNumber, randomNumber, randomNumber, time));
                }
            }
        }
    }

    public static List<Price> getHistoryPriceStockById(int stockId)
    {
        return stockPrice.get(stockId);
    }
}
