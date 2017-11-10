package ppic.model.operators;

import ppic.model.*;


public class PPClip extends AOneArgumentFunc
{
    public static final String NAME = "PPClip";


    public PPClip (Expression base)
    {
        super(NAME, base);
    }


    public PPClip (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(clipRange(c.getR()),
                            clipRange(c.getG()),
                            clipRange(c.getB()));
    }


    private double clipRange (double value)
    {
        return Math.max(-1, Math.min(1, value));
    }
}
