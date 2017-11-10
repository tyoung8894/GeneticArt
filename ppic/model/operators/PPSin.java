package ppic.model.operators;

import ppic.model.*;


public class PPSin extends AOneArgumentFunc
{
    public static final String NAME = "PPSin";


    public PPSin (Expression base)
    {
        super(NAME, base);
    }


    public PPSin (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(Math.sin(c.getR()),
                            Math.sin(c.getG()),
                            Math.sin(c.getB()));
    }
}
