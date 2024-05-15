package org.ttl.order;

public class Context
{
    private int     stockId;
    private int     orderId;
    private int     quantity;
    private double  duration;
    private double  lastTimePlaceOrder;
    private double  deviation;
    private double  intervalTime;
    private double  quantityOrdered;
    private int     scheduleTime;
    private int     timeStartOrder;
    private String  BS;
    private int     priceQuantity;
    private boolean allowOrderWhenDurationEnd;
    private int     lastCheckingTime;

    public int getLastCheckingTime()
    {
        return lastCheckingTime;
    }

    public void setLastCheckingTime(int lastCheckingTime)
    {
        this.lastCheckingTime = lastCheckingTime;
    }

    public boolean isAllowOrderWhenDurationEnd()
    {
        return allowOrderWhenDurationEnd;
    }

    public void setAllowOrderWhenDurationEnd(boolean allowOrderWhenDurationEnd)
    {
        this.allowOrderWhenDurationEnd = allowOrderWhenDurationEnd;
    }

    public int getPriceQuantity()
    {
        return priceQuantity;
    }

    public void setPriceQuantity(int priceQuantity)
    {
        this.priceQuantity = priceQuantity;
    }

    public String getBS()
    {
        return BS;
    }

    public void setBS(String BS)
    {
        this.BS = BS;
    }

    public int getTimeStartOrder()
    {
        return timeStartOrder;
    }

    public void setTimeStartOrder(int timeStartOrder)
    {
        this.timeStartOrder = timeStartOrder;
    }

    public int getStockId()
    {
        return stockId;
    }

    public void setScheduleTime(int scheduleTime)
    {
        this.scheduleTime = scheduleTime;
    }

    public int getScheduleTime()
    {
        return scheduleTime;
    }

    public void setStockId(int stockId)
    {
        this.stockId = stockId;
    }

    public int getOrderId()
    {
        return orderId;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public double getDuration()
    {
        return duration;
    }

    public void setDuration(double duration)
    {
        this.duration = duration;
    }

    public double getLastTimePlaceOrder()
    {
        return lastTimePlaceOrder;
    }

    public void setLastTimePlaceOrder(double lastTimePlaceOrder)
    {
        this.lastTimePlaceOrder = lastTimePlaceOrder;
    }

    public double getDeviation()
    {
        return deviation;
    }

    public void setDeviation(double deviation)
    {
        this.deviation = deviation;
    }

    public double getIntervalTime()
    {
        return intervalTime;
    }

    public void setIntervalTime(double intervalTime)
    {
        this.intervalTime = intervalTime;
    }

    public double getQuantityOrdered()
    {
        return quantityOrdered;
    }

    public void setQuantityOrdered(double quantityOrdered)
    {
        this.quantityOrdered = quantityOrdered;
    }

}
