package ppic.model.operators;

import ppic.model.*;


public class PPFloor extends AOneArgumentFunc
{
    public static final String NAME = "PPFloor";


    public PPFloor (Expression base)
    {
        super(NAME, base);
    }


    public PPFloor (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(Math.floor(c.getR()),
                            Math.floor(c.getG()),
                            Math.floor(c.getB()));
    }
}
