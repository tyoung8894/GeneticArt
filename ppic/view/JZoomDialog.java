package ppic.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import ppic.model.Expression;
import ppic.view.jpc.JPCFactory;
import ppic.view.jpc.JPictureComponent;


@SuppressWarnings("serial")
public class JZoomDialog extends JDialog implements ActionListener
{
    private Frame _parent;
    protected Expression _expr;
    protected JPictureComponent _pic;
    private JButton _savetofile;
    private JButton _rendertoscreen;
    private JButton _close;
    private JLabel _heightlbl;
    private JLabel _widthlbl;
    private SpinnerNumberModel _widthmodel;
    private SpinnerNumberModel _heightmodel;
    private JFileChooser fc;
    protected JPictureBrowser _log;


    public JZoomDialog (Frame parent, Expression expr, JPictureBrowser log)
    {
        super(parent);
        setTitle("Zoom Options");
        setDefaultCloseOperation(2);
        _parent = parent;
        _expr = expr;
        _log = log;
        _pic = JPCFactory.makeGeneric(_expr, 200, 200);
        getContentPane().setLayout(new BorderLayout());
        Box picarea = new Box(0);
        picarea.add(Box.createHorizontalStrut(20));
        picarea.add(_pic);
        picarea.add(Box.createHorizontalStrut(20));
        picarea.add(Box.createVerticalStrut(230));
        picarea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Preview"));
        getContentPane().add(picarea, BorderLayout.WEST);
        _pic.go();
        _widthmodel = new SpinnerNumberModel(320, 1, 0x186a0, 10);
        _heightmodel = new SpinnerNumberModel(320, 1, 0x186a0, 10);
        Box optionsarea = new Box(1);
        optionsarea.add(Box.createGlue());
        Box heightwidth = new Box(0);
        heightwidth.add(Box.createGlue());
        _heightlbl = new JLabel("Height (in pixels)");
        heightwidth.add(_heightlbl);
        JSpinner spinner = new JSpinner(_heightmodel);
        _heightlbl.setLabelFor(spinner);
        heightwidth.add(spinner);
        heightwidth.add(Box.createHorizontalStrut(10));
        _widthlbl = new JLabel("Width (in pixels)");
        heightwidth.add(_widthlbl);
        spinner = new JSpinner(_widthmodel);
        _widthlbl.setLabelFor(spinner);
        heightwidth.add(spinner);
        optionsarea.add(heightwidth);
        optionsarea.add(Box.createGlue());
        Box buttonarea = new Box(0);
        buttonarea.add(Box.createGlue());
        _rendertoscreen = new JButton("Render to Screen");
        _rendertoscreen.setActionCommand("renderscreen");
        _rendertoscreen.addActionListener(this);
        buttonarea.add(_rendertoscreen);
        buttonarea.add(Box.createGlue());
        _savetofile = new JButton("Save to File");
        _savetofile.setActionCommand("savefile");
        _savetofile.addActionListener(this);
        buttonarea.add(_savetofile);
        buttonarea.add(Box.createGlue());
        optionsarea.add(buttonarea);
        optionsarea.add(Box.createGlue());
        Box closearea = new Box(0);
        closearea.add(Box.createGlue());
        _close = new JButton("Close");
        _close.setActionCommand("close");
        _close.addActionListener(this);
        closearea.add(_close);
        closearea.add(Box.createGlue());
        optionsarea.add(closearea);
        optionsarea.add(Box.createGlue());
        optionsarea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Rendering Options"));
        getContentPane().add(optionsarea, BorderLayout.EAST);
        pack();
    }


    public void actionPerformed (ActionEvent arg0)
    {
        if (arg0.getActionCommand().equals("renderscreen")) rendertoscreen();
        else if (arg0.getActionCommand().equals("savefile")) savetofile();
        else if (arg0.getActionCommand().equals("close")) setVisible(false);
    }


    protected void rendertoscreen ()
    {
        JViewerFrame jvf = new JViewerFrame(_expr,
                ((Integer) _widthmodel.getValue()).intValue(),
                ((Integer) _heightmodel.getValue()).intValue());
        jvf.setVisible(true);
    }


    protected void drawPic (final Expression p, final File outfile)
    {
        final int maxy[] = { ((Integer) _heightmodel.getValue()).intValue() };
        final int maxx[] = { ((Integer) _widthmodel.getValue()).intValue() };
        final ProgressMonitor pm[] = { new ProgressMonitor(this,
                "Rendering to disk", null, 0, maxy[0] - 1) };
        pm[0].setMillisToDecideToPopup(200);
        pm[0].setProgress(0);
        final BufferedImage buff[] = { new BufferedImage(maxx[0], maxy[0], 1) };
        Runnable r = new Runnable()
        {
            public void run ()
            {
                for (int inty = 0; inty < maxy[0]; inty++)
                {
                    pm[0].setProgress(inty);
                    if (pm[0].isCanceled())
                    {
                        pm[0].close();
                        return;
                    }
                    float curry = ((float) inty / (float) maxy[0]) * 2 - 1;
                    for (int intx = 0; intx < maxx[0]; intx++)
                    {
                        float currx = ((float) intx / (float) maxx[0]) * 2 - 1;
                        buff[0].setRGB(intx, inty, p.evaluate(currx, curry, 0).getJavaColor().getRGB());
                    }
                }

                _log.logMessage("Writing " + outfile.getName() + " to disk ");
                try
                {
                    ImageIO.write(buff[0], ImageFilter.getExtension(outfile), outfile);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                pm[0].close();
            }
        };
        new Thread(r).start();
    }


    protected void savetofile ()
    {
        if (fc == null)
        {
            fc = new JFileChooser();
            fc.addChoosableFileFilter(new ImageFilter());
            fc.setAcceptAllFileFilterUsed(false);
        }
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == 0)
        {
            File file = fc.getSelectedFile();
            if ((new ImageFilter()).accept(file)) drawPic(_expr, file);
            else JOptionPane.showMessageDialog(_parent,
                    "Must have an image extension (.gif,.png,.jpg).",
                    "Error", 2);
        }
    }
}
