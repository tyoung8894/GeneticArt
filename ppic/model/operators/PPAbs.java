package ppic.model.operators;

import ppic.model.*;


public class PPAbs extends AOneArgumentFunc
{
    public static final String NAME = "PPAbs";


    public PPAbs (Expression base)
    {
        super(NAME, base);
    }


    public PPAbs (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(Math.abs(c.getR()),
                            Math.abs(c.getG()),
                            Math.abs(c.getB()));
    }
}
