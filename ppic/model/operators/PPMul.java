package ppic.model.operators;

import ppic.model.*;


public class PPMul extends ATwoArgumentFunc
{
    public static final String NAME = "PPMul";


    public PPMul (Expression l, Expression r)
    {
        super(NAME, l, r);
    }


    public PPMul (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getR() * right.getR(),
                            left.getG() * right.getG(),
                            left.getB() * right.getB());
    }
}
