package ppic.model.operators;

import ppic.model.Expression;


public class PPExtImageClip extends APPExtImage
{
    public static final String NAME = "PPExtImageClip";


    public PPExtImageClip (Expression left, Expression right, String file)
        throws Exception
    {
        super(NAME, left, right, file);
    }


    public PPExtImageClip (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    protected double convertColorValue (double value)
    {
        return Math.max(Math.min(value, 1), -1) / 2 + 0.5;
    }
}
