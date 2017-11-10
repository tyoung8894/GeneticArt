package ppic.model.operators;

import ppic.model.*;


public class PPCos extends AOneArgumentFunc
{
    public static final String NAME = "PPCos";


    public PPCos (Expression base)
    {
        super(NAME, base);
    }


    public PPCos (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(Math.cos(c.getR()),
                            Math.cos(c.getG()),
                            Math.cos(c.getB()));
    }
}
