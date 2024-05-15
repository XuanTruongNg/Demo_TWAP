package org.ttl.order;

public interface ConditionChecking
{
     boolean isConditionMatching(Double twapPrice, Double marketPrice, Context context);
}
