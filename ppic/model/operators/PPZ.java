package ppic.model.operators;

import ppic.model.*;


public class PPZ extends Expression
{
    public static final String NAME = "PPZ";


    public PPZ ()
    {
        super(NAME);
    }


    public PPZ (String expr)
        throws Exception
    {
        super(NAME, 0);
    }


    public RGBColor evaluate (float x, float y, float z)
    {
        return new RGBColor(z, z, z);
    }
}
