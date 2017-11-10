package ppic.model.operators;

import ppic.model.*;


public class PPY extends Expression
{
    public static final String NAME = "PPY";


    public PPY ()
    {
        super(NAME);
    }


    public PPY (String expr)
        throws Exception
    {
        super(NAME, 0);
    }


    public RGBColor evaluate (float x, float y, float z)
    {
        return new RGBColor(y, y, y);
    }
}
