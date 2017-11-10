package ppic.model.operators;

import ppic.model.*;


public class PPX extends Expression
{
    public static final String NAME = "PPX";


    public PPX ()
    {
        super(NAME);
    }


    public PPX (String expr)
        throws Exception
    {
        super(NAME, 0);
    }


    public RGBColor evaluate (float x, float y, float z)
    {
        return new RGBColor(x, x, x);
    }
}
