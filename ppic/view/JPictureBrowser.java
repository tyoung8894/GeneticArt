package ppic.view;

import java.awt.BorderLayout;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

import ppic.model.Algorithm;
import ppic.model.Expression;
import ppic.model.Parser;
import ppic.model.algorithms.Breed;
import ppic.model.algorithms.Mutate;
import ppic.model.algorithms.Randomize;
import ppic.util.PredictablyRandom;
import ppic.view.jpc.JPCFactory;
import ppic.view.jpc.JPictureComponent;


@SuppressWarnings("serial")
public class JPictureBrowser extends JComponent
{
    public static final int NUMBER_TO_DISPLAY = 25;

    private static JFileChooser ourChooser = 
        new JFileChooser(System.getProperties().getProperty("user.dir"));

    private ArrayList<ArrayList<JPictureComponent>> _history;
    private JPictureSet _currentSet;
    private int _currentIndex;
    private JBrowserSidebar _sidebar;
    private JBrowserToolbar _toolbar;
    private JGrabBar _grabBar;
    private JFrame _parent;
    private double _mutationRate;
    private double _depthRate;
   

    public JPictureBrowser (JFrame mainWindow)
    {
        setLayout(new BorderLayout());

        _mutationRate = 0.5;
        _depthRate = 0.5;
        _currentIndex = 0;
        _history = new ArrayList<ArrayList<JPictureComponent>>();
        _currentSet = new JPictureSet(this, NUMBER_TO_DISPLAY);
        add(_currentSet, BorderLayout.CENTER);

        _sidebar = new JBrowserSidebar(this);
        add(_sidebar, BorderLayout.EAST);
        _toolbar = new JBrowserToolbar(this);
        add(_toolbar, BorderLayout.NORTH);
        _grabBar = new JGrabBar(this);
        add(_grabBar, BorderLayout.SOUTH);

        reset();
    }


    public void forward ()
    {
        if (_currentIndex < _history.size() - 1)
        {
            go(_currentIndex + 1);
        }
    }


    public void backward ()
    {
        if (_currentIndex >= 1)
        {
            go(_currentIndex - 1);
        }
    }


    public void go (int idx)
    {
        _currentIndex = idx;
        _currentSet.setSet(_history.get(_currentIndex));
        _currentSet.go();
    }


    public void displayPictures (ArrayList<JPictureComponent> pictures)
    {
        _history.add(pictures);
        _currentSet.setSet(pictures);
        _currentSet.go();
    }


    public void reset ()
    {
        try
        {
            if (_history.size() > 0)
            {
                _history.remove(_currentIndex);
            }

            ArrayList<JPictureComponent> pictures = new ArrayList<JPictureComponent>();
            for (int i = 0; i < NUMBER_TO_DISPLAY; i++)
            {
            		Expression kid = new Randomize().apply(1.0 - _depthRate);
            		pictures.add(JPCFactory.makeThumbnail(kid, this));
            }
            displayPictures(pictures);
        }
        catch (Exception e)
        {
        		e.printStackTrace();
            System.out.println("Error generating base functions: " + e);
        }
    }


    public void breed ()
    {
        ArrayList<Expression> possibles = _currentSet.getSelected();
        if (possibles.isEmpty())
        {
            logWarning("No functions selected for breeding.");
        }
        else
        {
            try
            {
                int size = possibles.size();
                logMessage("Breeding " + size + " functions.");
                ArrayList<JPictureComponent> pictures = new ArrayList<JPictureComponent>();
                for (int i = 0; i < NUMBER_TO_DISPLAY; i++)
                {
                		Expression mom = possibles.get((int)(Algorithm.random.nextDouble() * size));
                		Expression dad = possibles.get((int)(Algorithm.random.nextDouble()  * size));
                		Expression kid = new Breed().apply(mom, dad, _mutationRate);
                		pictures.add(JPCFactory.makeThumbnail(kid, this));
                }
                _currentIndex++;
                displayPictures(pictures);
            }
            catch (Exception e)
            {
                logError("Error breeding functions");
            }
        }
    }


    public void mutate ()
    {
        ArrayList<Expression> possibles = _currentSet.getSelected();
        if (possibles.isEmpty())
        {
            logWarning("No functions selected for mutating.");
        }
        else
        {
            try
            {
                int size = possibles.size();
                logMessage("Mutating " + size + " functions.");
                ArrayList<JPictureComponent> pictures = new ArrayList<JPictureComponent>();
                for (int i = 0; i < NUMBER_TO_DISPLAY; i++)
                {
                		Expression kid = possibles.get((int)(Algorithm.random.nextDouble() * size));
                		kid = new Mutate().apply(kid, _mutationRate);
                		pictures.add(JPCFactory.makeThumbnail(kid, this));
                }
                _currentIndex++;
                displayPictures(pictures);
            }
            catch (Exception e)
            {
                logError("Error breeding functions");
            }
        }
    }

    public void clear ()
    {
        _history.clear();
        _currentIndex = 0;
        reset();
    }

    public void zoom (Expression picture)
    {
        new JZoomDialog(_parent, picture, this).setVisible(true);
    }

    public void changeMutationRate (int rate)
    {
        setMutationRate(rate * 0.01);
    }

    public void addImage ()
    {
        new JSettingsDialog(_parent, this).setVisible(true);
    }

    public void sendToSidebar (JPictureComponent p)
    {
        _sidebar.setExpression(p.getExpression());
        revalidate();
        repaint();
    }

    public void saveExpression (Expression expr)
    {
        if (ourChooser.showSaveDialog(this) == 0)
        {
            try
            {
                File file = ourChooser.getSelectedFile();
                FileWriter writer = new FileWriter(file);
                file.createNewFile();
                writer.write(expr.toString());
                writer.flush();
            }
            catch (Exception e)
            {
                spawnBasicDialog("Could not save function to the requested location.  The reason was:\n" + e.getMessage());
                logError(e.getMessage());
            }
        }
    }

    public Expression loadExpression ()
    {
        if (ourChooser.showOpenDialog(this) == 0)
        {
            try
            {
                File file = ourChooser.getSelectedFile();
                String expr = "";
                Scanner reader = new Scanner(file);
                while (reader.hasNext())
                {
                    expr += reader.nextLine();
                }
                return Parser.singleton.parse(expr);
            }
            catch (Exception e)
            {
                spawnBasicDialog("Could not load function to the requested location.");
                if (e.getMessage() == null) logError("Error loading external image");
                else logError(e.getMessage());
            }
        }
        return null;
    }

    public double getDepthRate ()
    {
        return _depthRate;
    }

    public void setDepthRate (double rate)
    {
        _depthRate = Math.max(0.05, rate);
    }

    public double getMutationRate ()
    {
        return _mutationRate;
    }

    public void setMutationRate (double rate)
    {
        _mutationRate = rate;
    }
    
    public void logMessage (String message)
    {
        _grabBar.appendToLog(message);
    }

    public void logWarning (String message)
    {
        _grabBar.warn(message);
    }

    public void logError (String message)
    {
        _grabBar.error(message);
    }

    public void spawnBasicDialog (String message)
    {
        JOptionPane.showMessageDialog(_parent, message);
    }
}
