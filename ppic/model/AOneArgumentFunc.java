package ppic.model;


public abstract class AOneArgumentFunc extends Expression
{
    public AOneArgumentFunc (String type, Expression base)
    {
        super(type, base);
    }


    protected AOneArgumentFunc (String type, String expr)
        throws Exception
    {
        this(type, Parser.singleton.parse(expr));
    }


    public RGBColor evaluate (float x, float y, float z)
    {
        return evaluate(getLeft().evaluate(x, y, z));
    }


    public abstract RGBColor evaluate (RGBColor rgbcolor);
}
