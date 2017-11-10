package ppic.model;

import java.awt.Color;


public class RGBColor
{
    private double _r;
    private double _g;
    private double _b;


    public RGBColor (double r, double g, double b)
    {
        _r = r;
        _g = g;
        _b = b;
    }


    public double getR ()
    {
        return _r;
    }


    public double getG ()
    {
        return _g;
    }


    public double getB ()
    {
        return _b;
    }


    public Color getJavaColor ()
    {
        return new Color((float)Math.max(0, Math.min(1, _r / 2 + 0.5)),
                         (float)Math.max(0, Math.min(1, _g / 2 + 0.5)),
                         (float)Math.max(0, Math.min(1, _b / 2 + 0.5)));
    }


    public boolean equals (Object o)
    {
        if (o instanceof RGBColor)
        {
            RGBColor other = (RGBColor)o;
            return _r == other._r && _g == other._g && _b == other._b;
        }
        return false;
    }


    public String toString ()
    {
        return getR() + " " + getG() + " " + getB();
    }


/*
    public RGBColor scale (float fac)
    {
        float rgb[] = getJavaColor().getColorComponents(null);
        return new RGBColor(rgb[0] * fac * 2.0 - 1.0,
                            rgb[1] * fac * 2.0 - 1.0,
                            rgb[2] * fac * 2.0 - 1.0);
    }


    public RGBColor add (RGBColor other)
    {
        float rgb1[] = getJavaColor().getColorComponents(null);
        float rgb2[] = other.getJavaColor().getColorComponents(null);
        return new RGBColor((rgb1[0] + rgb2[0]) * 2.0 - 1.0,
                            (rgb1[1] + rgb2[1]) * 2.0 - 1.0,
                            (rgb1[2] + rgb2[2]) * 2.0 - 1.0);
    }


    public RGBColor mul (RGBColor other)
    {
        float rgb1[] = getJavaColor().getColorComponents(null);
        float rgb2[] = other.getJavaColor().getColorComponents(null);
        return new RGBColor(rgb1[0] * rgb2[0] * 2.0 - 1.0,
                            rgb1[1] * rgb2[1] * 2.0 - 1.0, 
                            rgb1[2] * rgb2[2] * 2.0 - 1.0);
    }
*/
}
