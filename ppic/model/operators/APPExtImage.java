package ppic.model.operators;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;
import ppic.model.*;


public abstract class APPExtImage extends ATwoArgumentFunc
{
    private static final int R_IDX = 0;
    private static final int G_IDX = 1;
    private static final int B_IDX = 2;
    private static final int X_IDX = 0;
    private static final int Y_IDX = 1;

    protected String _externalFilepath;
    protected BufferedImage _srcImage;
    protected int _width;
    protected int _height;


    protected APPExtImage (String type, Expression l, Expression r, String filename)
        throws Exception
    {
        super(type, l, r);
        setupImage(filename);
    }


    protected APPExtImage (String type, String expr)
        throws Exception
    {
        super(type, expr.substring(expr.indexOf(" ") + 1));
        setupImage(expr.substring(1, expr.indexOf("\"", 1)));
    }


    private void setupImage (String filename)
        throws Exception
    {
        try
        {
            _externalFilepath = filename;
            _srcImage = ImageIO.read(new File(_externalFilepath));
            _width = _srcImage.getWidth();
            _height = _srcImage.getHeight();
            // BUGBUG: fix this
            Parser.singleton.addFile(_externalFilepath);
        }
        catch (FileNotFoundException e)
        {
            throw new Exception("Error, external image file not found");
        }        
    }


    public RGBColor evaluate (RGBColor left, RGBColor right)
    {
        double coords[][] = getCoords(left, right);

        double result[] = new double[3];
        for (int i = 0; i < 3; i++)
        {
            int x_lo = (int)Math.floor(coords[i][X_IDX] * (_width - 1));
            int x_hi = (int)Math.ceil(coords[i][X_IDX] * (_width - 1));
            int y_lo = (int)Math.floor(coords[i][Y_IDX] * (_height - 1));
            int y_hi = (int)Math.ceil(coords[i][Y_IDX] * (_height - 1));
            Color p00 = new Color(_srcImage.getRGB(x_lo, y_lo));
            Color p01 = new Color(_srcImage.getRGB(x_lo, y_hi));
            Color p10 = new Color(_srcImage.getRGB(x_hi, y_lo));
            Color p11 = new Color(_srcImage.getRGB(x_hi, y_hi));
            double t0 = (1.0 - coords[i][X_IDX])
                    * (double)p00.getColorComponents(null)[i] + coords[i][X_IDX]
                    * (double)p10.getColorComponents(null)[i];
            double t1 = (1.0 - coords[i][X_IDX])
                    * (double)p01.getColorComponents(null)[i] + coords[i][X_IDX]
                    * (double)p11.getColorComponents(null)[i];
            result[i] = (1.0 - coords[i][Y_IDX]) * t0 + coords[i][Y_IDX] * t1;
        }
        return new RGBColor((result[R_IDX] - 0.5) * 2,
                            (result[G_IDX] - 0.5) * 2,
                            (result[B_IDX] - 0.5) * 2);
    }


    public Object getFilename ()
    {
        return _externalFilepath;
    }


    public String toString ()
    {
        return "(" + getType() + " \"" + _externalFilepath + "\" " + 
               getLeft() + " " + getRight() + ")";
    }


    public boolean equals (Object other)
    {
        return super.equals(other) && 
               _externalFilepath.equals(((APPExtImage)other)._externalFilepath);
    }


    private double[][] getCoords (RGBColor left, RGBColor right)
    {
        double[][] results = new double[3][2];
        results[R_IDX][X_IDX] = convertColorValue(left.getR());
        results[R_IDX][Y_IDX] = convertColorValue(right.getR());
        results[G_IDX][X_IDX] = convertColorValue(left.getG());
        results[G_IDX][Y_IDX] = convertColorValue(right.getG());
        results[B_IDX][X_IDX] = convertColorValue(left.getB());
        results[B_IDX][Y_IDX] = convertColorValue(right.getB());
        return results;
    }
    

    protected abstract double convertColorValue (double value);
    


    public static Expression simpleImage (String img)
        throws Exception
    {
        return new PPExtImageWrap(new PPX(), new PPY(), img);
    }
}
