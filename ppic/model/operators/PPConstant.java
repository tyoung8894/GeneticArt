package ppic.model.operators;

import ppic.model.*;


public class PPConstant extends Expression
{
    public static final String NAME = "PPConstant";

    RGBColor _mycolor;


    public PPConstant ()
    {
        this(2 * Math.random() - 1,
             2 * Math.random() - 1, 
             2 * Math.random() - 1);
    }


    public PPConstant (String expr)
        throws Exception
    {
        this(Double.parseDouble(expr.split(" ")[0]),
             Double.parseDouble(expr.split(" ")[1]),
             Double.parseDouble(expr.split(" ")[2]));
    }


    public PPConstant (double r, double g, double b)
    {
        super(NAME, 0);
        _mycolor = new RGBColor(r, g, b);
    }


    public RGBColor evaluate (float x, float y, float z)
    {
        return _mycolor;
    }


    public String toString ()
    {
        return "(" + getType() + " " + _mycolor + ")";
    }


    public boolean equals (Object other)
    {
        return super.equals(other) && 
               _mycolor.equals(((PPConstant)other)._mycolor);
    }


    public Expression copy ()
    {
        try
        {
            PPConstant result = (PPConstant)super.clone();
            result._mycolor = new RGBColor(_mycolor.getR(), _mycolor.getG(), _mycolor.getB());
            return result;
        }
        catch (CloneNotSupportedException e)
        {
            return null;
        }
    }
}
