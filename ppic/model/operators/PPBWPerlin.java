package ppic.model.operators;

import ppic.model.*;


public class PPBWPerlin extends ATwoArgumentFunc
{
    public static final String NAME = "PPBWPerlin";


    public PPBWPerlin (Expression l, Expression r)
    {
        super(NAME, l, r);
    }


    public PPBWPerlin (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor left, RGBColor right)
    {
        double grey = 
            PerlinNoiseGenerator.noise3(left.getR() + right.getR(),
                                        left.getG() + right.getG(),
                                        left.getB() + right.getB());
        return new RGBColor(grey, grey, grey);
    }
}
