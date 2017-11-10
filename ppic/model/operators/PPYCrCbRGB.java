package ppic.model.operators;

import ppic.model.*;


public class PPYCrCbRGB extends AOneArgumentFunc
{
    public static final String NAME = "PPYCrCbRGB";


    public PPYCrCbRGB (Expression base)
    {
        super(NAME, base);
    }


    public PPYCrCbRGB (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(
                c.getR() + c.getB() *  1.4022,
                c.getR() + c.getG() * -0.3456 + c.getB() * -0.7145,
                c.getR() + c.getG() *  1.7710);
    }
}
