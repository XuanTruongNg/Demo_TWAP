package org.ttl.order;

public class UserConditionChecking implements ConditionChecking
{
    @Override
    public boolean isConditionMatching(Double twapPrice, Double marketPrice, Context context)
    {
        if (context.getBS().equals("B") && twapPrice < marketPrice)
        {
            return true;
        }
        else if (context.getBS().equals("S") && twapPrice > marketPrice)
        {
            return true;
        }
        else
        {
            if (context.getBS().equals("B"))
            {
                return (twapPrice / marketPrice) < (1 + context.getDeviation());
            }
            else
            {
                return (twapPrice / marketPrice) > (1 - context.getDeviation());
            }
        }
    }
}
