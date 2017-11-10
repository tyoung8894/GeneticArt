package ppic.model.operators;

import ppic.model.*;


public class PPAtan extends AOneArgumentFunc
{
    public static final String NAME = "PPAtan";


    public PPAtan (Expression base)
    {
        super(NAME, base);
    }


    public PPAtan (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(Math.atan(c.getR()),
                            Math.atan(c.getG()),
                            Math.atan(c.getB()));
    }
}
