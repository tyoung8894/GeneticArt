package ppic;

import java.awt.Dimension;
import javax.swing.JFrame;

import ppic.model.*;

import ppic.model.Algorithm;
import ppic.model.algorithms.Breed;
import ppic.model.algorithms.Mutate;
import ppic.model.algorithms.Randomize;
import ppic.util.PredictablyRandom;
import ppic.view.JPictureBrowser;


public class Main
{
    public static final Dimension SIZE = new Dimension(915, 855);
    public static final String TITLE = "Pretty Pictures";


    public static void main (String args[])
    {
        // Comment out if you are not testing.
        Algorithm.setRandom(new PredictablyRandom());
    	
    	
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JPictureBrowser(frame));
        frame.pack();
        frame.setSize(SIZE);
        frame.setVisible(true);
        try
        {
            String expr = "(PPDiv (PPRGBYCrCb (PPMul (PPWrap (PPY)) (PPMul (PPY) (PPConstant 0.807246936340217 -0.9721076173634562 -0.4931793482530733)))) (PPBWPerlin (PPX) (PPX)))";
            System.out.println(ppic.model.Parser.singleton.parse(expr));
        }
        catch (Exception e)
        {}
    }
}
