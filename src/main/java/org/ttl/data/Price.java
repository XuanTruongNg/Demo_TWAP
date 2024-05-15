package org.ttl.data;

public class Price
{
    private double marketPrice;
    private double buyPrice;
    private double sellPrice;
    private double time;

    public Price(double marketPrice, double buyPrice, double sellPrice, double time)
    {
        this.marketPrice = marketPrice;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.time = time;
    }

    public double getTime()
    {
        return this.time;
    }

    public double getMarketPrice()
    {
        return marketPrice;
    }

    public double duration(Price priceBefore)
    {
        return this.getTime() - priceBefore.getTime();
    }

    public double priceWeighted(Price priceBefore)
    {
        return this.getMarketPrice() * (this.getTime() - priceBefore.getTime());
    }
}
