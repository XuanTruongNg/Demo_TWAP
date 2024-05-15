package org.ttl.calculator;

import org.ttl.data.Price;

import java.util.ArrayList;
import java.util.List;

import static org.ttl.data.DummyData.getHistoryPriceStockById;
import static org.ttl.data.DummyData.stockAPrice;
import static org.ttl.data.DummyData.stockPrice;

public class TWAPCalculator
{
    private static TWAPCalculator instance;

    public static Double calculateTWAP(Double t0, Double tn, int stockId)
    {
        List <Price> listPriceInDuration = new ArrayList<>();
        for (Price price : getHistoryPriceStockById(stockId))
        {
            if (price.getTime() >= t0 && price.getTime() <= tn)
            {
                listPriceInDuration.add(price);
            }
        }
        if (listPriceInDuration.isEmpty())
        {
            return null;
        }
        if (listPriceInDuration.size() == 1)
        {
            return listPriceInDuration.get(0).getMarketPrice();
        }
        double totalPrice = 0.0;
        for (int i = 1; i < listPriceInDuration.size(); i++)
        {
            totalPrice += listPriceInDuration.get(i).priceWeighted(listPriceInDuration.get(i - 1));
        }

        return totalPrice / (listPriceInDuration.get(listPriceInDuration.size() - 1).getTime() - listPriceInDuration.get(0).getTime());
    }

    public static Price getPriceCurrent()
    {
        return stockAPrice.get(stockAPrice.size() - 1);
    }
}
