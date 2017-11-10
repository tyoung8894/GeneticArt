package ppic.model.operators;

import ppic.model.*;


public class PPNegate extends AOneArgumentFunc
{
    public static final String NAME = "PPNegate";


    public PPNegate (Expression base)
    {
        super(NAME, base);
    }


    public PPNegate (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(-c.getR(),
                            -c.getG(),
                            -c.getB());
    }
}
