package ppic.model.operators;

import ppic.model.*;


public class PPCeil extends AOneArgumentFunc
{
    public static final String NAME = "PPCeil";


    public PPCeil (Expression base)
    {
        super(NAME, base);
    }


    public PPCeil (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(Math.ceil(c.getR()),
                            Math.ceil(c.getG()),
                            Math.ceil(c.getB()));
    }
}
