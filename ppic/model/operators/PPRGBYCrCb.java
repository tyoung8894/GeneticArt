package ppic.model.operators;

import ppic.model.*;


public class PPRGBYCrCb extends AOneArgumentFunc
{
    public static final String NAME = "PPRGBYCrCb";


    public PPRGBYCrCb (Expression base)
    {
        super(NAME, base);
    }


    public PPRGBYCrCb (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor c)
    {
        return new RGBColor(
                c.getR() *  0.2989 + c.getG() *  0.5866 + c.getB() *  0.1145,
                c.getR() * -0.1687 + c.getG() * -0.3312 + c.getB() *  0.5,
                c.getR() *  0.5000 + c.getG() * -0.4183 + c.getB() * -0.0816);

    }
}
