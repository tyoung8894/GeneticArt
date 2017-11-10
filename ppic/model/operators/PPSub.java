package ppic.model.operators;

import ppic.model.*;


public class PPSub extends ATwoArgumentFunc
{
    public static final String NAME = "PPSub";


    public PPSub (Expression l, Expression r)
    {
        super(NAME, l, r);
    }


    public PPSub (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getR() - right.getR(),
                            left.getG() - right.getG(),
                            left.getB() - right.getB());
    }
}
