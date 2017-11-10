package ppic.model.operators;

import ppic.model.Expression;


public class PPExtImageWrap extends APPExtImage
{
    public static final String NAME = "PPExtImageWrap";


    public PPExtImageWrap (Expression left, Expression right, String file)
        throws Exception
    {
        super(NAME, left, right, file);
    }


    public PPExtImageWrap (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    protected double convertColorValue (double value)
    {
        return Math.IEEEremainder(value, 2) / 2 + 0.5;
    }
}
