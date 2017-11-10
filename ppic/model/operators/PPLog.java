package ppic.model.operators;

import ppic.model.*;


public class PPLog extends AOneArgumentFunc
{
    public static final String NAME = "PPLog";


    public PPLog (Expression base)
    {
        super(NAME, base);
    }


    public PPLog (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(getLog(c.getR()),
                            getLog(c.getG()),
                            getLog(c.getB()));
    }


    private double getLog (double value)
    {
        return Math.log((value != 0) ? Math.abs(value) : 1);
    }
}
