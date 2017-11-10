package ppic.model;

import java.util.Random;

public abstract class Algorithm
{
    public static final String PACKAGE = "ppic.model.operators.";

    public static final String[] ZERO_ARG_EXPRESSIONS = {
        ppic.model.operators.PPConstant.NAME,
        ppic.model.operators.PPX.NAME,
        ppic.model.operators.PPY.NAME
    };
    public static final String[] ONE_ARG_EXPRESSIONS = {
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
        ppic.model.operators.PPYCrCbRGB.NAME 
    };
    public static final String[] TWO_ARG_EXPRESSIONS = { 
        ppic.model.operators.PPAdd.NAME,
        ppic.model.operators.PPBWPerlin.NAME,
        ppic.model.operators.PPColorPerlin.NAME,
        ppic.model.operators.PPDiv.NAME,
        ppic.model.operators.PPMul.NAME,
        ppic.model.operators.PPSub.NAME,
        ppic.model.operators.PPExtImageClip.NAME,
        ppic.model.operators.PPExtImageWrap.NAME 
    };

    public static Random random = new Random();
    

    public static void setRandom(Random newRandom) {
    		random = newRandom;
    }
    

    public Expression apply (double rate)
    {
        return null;
    }


    public Expression apply (Expression expr, double rate)
    {
        return null;
    }


    public Expression apply (Expression expr1, Expression expr2, double rate)
    {
        return null;
    }
}
