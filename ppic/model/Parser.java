package ppic.model;

import java.util.ArrayList;
import java.util.Scanner;
import ppic.util.Reflection;


public class Parser
{
    public static final String PACKAGE = "ppic.model.operators.";

    public static final Parser singleton = new Parser();

    public String[] functions = {
            ppic.model.operators.PPConstant.NAME,
            ppic.model.operators.PPX.NAME,
            ppic.model.operators.PPY.NAME,
            ppic.model.operators.PPAbs.NAME,
            ppic.model.operators.PPAtan.NAME,
            ppic.model.operators.PPCeil.NAME,
            ppic.model.operators.PPClip.NAME,
            ppic.model.operators.PPCos.NAME,
            ppic.model.operators.PPExp.NAME,
            ppic.model.operators.PPFloor.NAME,
            ppic.model.operators.PPLog.NAME,
            ppic.model.operators.PPNegate.NAME,
            ppic.model.operators.PPRGBYCrCb.NAME,
            ppic.model.operators.PPSin.NAME,
            ppic.model.operators.PPWrap.NAME,
            ppic.model.operators.PPYCrCbRGB.NAME,
            ppic.model.operators.PPAdd.NAME,
            ppic.model.operators.PPBWPerlin.NAME,
            ppic.model.operators.PPColorPerlin.NAME,
            ppic.model.operators.PPDiv.NAME,
            ppic.model.operators.PPMul.NAME,
            ppic.model.operators.PPSub.NAME,
            ppic.model.operators.PPExtImageClip.NAME,
            ppic.model.operators.PPExtImageWrap.NAME
        };
    

    private ArrayList<String> _availableImages;


    private Parser ()
    {
        _availableImages = new ArrayList<String>();
    }


    public void addFile (String s)
    {
        if (! _availableImages.contains(s))
        {
            _availableImages.add(s);
        }
    }


    public void removeFile (String s)
    {
        if (! _availableImages.contains(s))
        {
            _availableImages.remove(s);
        }
    }


    public ArrayList<String> getImageList ()
    {
        return _availableImages;
    }


    public Expression parse (String expr)
        throws Exception
    {
        if (! expr.matches("(.*?)"))
            throw new Exception("Function parsing error, expected (, found " + expr.substring(0, 1));

        expr = expr.trim();
        Scanner tokenizer = new Scanner(expr.substring(1, expr.length() - 1));
        String name = tokenizer.next();
        String args = tokenizer.hasNext() ? tokenizer.nextLine().trim() : "";

        return (Expression)Reflection.createInstance(PACKAGE + name, args);
    }
}
