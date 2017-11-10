package ppic.view;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import ppic.view.jpc.JPCFactory;


@SuppressWarnings("serial")
class JGrabBar extends JComponent
{
    private final static SimpleDateFormat FORMATTER = new SimpleDateFormat("HH:mm:ss- ");

    private TextArea _log;


    JGrabBar (JPictureBrowser parent)
    {
        //setPreferredSize(new Dimension(720, 150));
        setLayout(new FlowLayout());

        JComponent myWells = Box.createHorizontalBox();
        myWells.setPreferredSize(new Dimension(655, 150));
        myWells.add(Box.createHorizontalGlue());
        for (int i = 0; i < 6; i++)
        {
            myWells.add(JPCFactory.makeEmptyImageWell(95, 95));
            myWells.add(Box.createHorizontalStrut(7));
        }

        myWells.add(Box.createHorizontalGlue());
        myWells.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Image Wells"));
        add(myWells);
        _log = new TextArea("", 7, 20, 1);
        _log.setEditable(false);
        appendToLog("Log begins.");
        Box logbox = Box.createHorizontalBox();
        logbox.setPreferredSize(new Dimension(250, 150));
        logbox.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Program Log"));
        logbox.add(_log);
        add(logbox);
        revalidate();
        repaint();
    }


    void appendToLog (String message)
    {
        _log.append(FORMATTER.format(new Date()) + message + "\n");
    }


    void warn (String message)
    {
        appendToLog("\nWarning: " + message);
    }


    void error (String message)
    {
        appendToLog("\nERROR! " + message);
    }
}
