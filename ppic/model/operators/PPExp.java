package ppic.model.operators;

import ppic.model.*;


public class PPExp extends AOneArgumentFunc
{
    public static final String NAME = "PPExp";


    public PPExp (Expression base)
    {
        super(NAME, base);
    }


    public PPExp (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(Math.exp(c.getR()),
                            Math.exp(c.getG()),
                            Math.exp(c.getB()));
    }
}
