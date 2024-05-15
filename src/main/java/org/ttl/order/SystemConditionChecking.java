package org.ttl.order;

public class SystemConditionChecking implements ConditionChecking
{
    @Override
    public boolean isConditionMatching(Double twapPrice, Double marketPrice, Context context)
    {
        return true; // TODO: Implement this method
    }
}
