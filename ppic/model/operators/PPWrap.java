package ppic.model.operators;

import ppic.model.*;


public class PPWrap extends AOneArgumentFunc
{
    public static final String NAME = "PPWrap";


    public PPWrap (Expression base)
    {
        super(NAME, base);
    }


    public PPWrap (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(wrapRange(c.getR()),
                            wrapRange(c.getG()),
                            wrapRange(c.getB()));
    }


    private double wrapRange (double value)
    {
        return Math.IEEEremainder(value, 2);
    }
}
