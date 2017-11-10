package ppic.model.operators;

import ppic.model.*;


public class PPColorPerlin extends ATwoArgumentFunc
{
    public static final String NAME = "PPColorPerlin";


    public PPColorPerlin (Expression l, Expression r)
    {
        super(NAME, l, r);
    }


    public PPColorPerlin (String expr)
        throws Exception
    {
        super(NAME, expr);
    }


    public RGBColor evaluate (RGBColor left, RGBColor right)
    {
        // BUGBUG: is it always 0?
        return new RGBColor(
                PerlinNoiseGenerator.noise3(left.getR() + 0.3, right.getR() + 0.3, 0),
                PerlinNoiseGenerator.noise3(left.getG() - 0.8, right.getG() - 0.8, 0),
                PerlinNoiseGenerator.noise3(left.getB() + 0.1, right.getB() + 0.1, 0));
    }
}
