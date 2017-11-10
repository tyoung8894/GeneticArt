package ppic.model.operators;

import ppic.model.*;


public class PPDiv extends ATwoArgumentFunc
{
    public static final String NAME = "PPDiv";


    public PPDiv (Expression l, Expression r)
    {
        super(NAME, l, r);
    }


    public PPDiv (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor left, RGBColor right)
    {
        return new RGBColor(getDiv(left.getR(), right.getR()),
                            getDiv(left.getG(), right.getG()),
                            getDiv(left.getB(), right.getB()));
    }


    private double getDiv (double left, double right)
    {
        return (right != 0) ? left / right : 0;
    }
}
