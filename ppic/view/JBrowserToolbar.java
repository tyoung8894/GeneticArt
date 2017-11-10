package ppic.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class JBrowserToolbar extends JToolBar
    implements ActionListener
{
    private JPictureBrowser _parent;
    private JButton _back;
    private JButton _forward;
    private JButton _clear;
    private JButton _reset;
    private JButton _mutate;
    private JButton _breed;
    private JSlider _mutationSlider;
    private JSlider _randomSlider;


    JBrowserToolbar (JPictureBrowser parent)
    {
        _parent = parent;
        init();
    }


    private void init ()
    {
        _back = makeNavigationButton("Back24", "back",
                "Go Back to Previous Set", "Back");
        add(_back);
        _forward = makeNavigationButton("Forward24", "forward",
                "Go Forward to Next Set", "Forward");
        add(_forward);
        addSeparator();
        _clear = new JButton();
        _clear.setActionCommand("clear");
        _clear.setToolTipText("Clears all sets and generates a random one");
        _clear.setText("Clear");
        _clear.addActionListener(this);
        add(_clear);
        _reset = new JButton();
        _reset.setActionCommand("reset");
        _reset.setToolTipText("Resets all pictures in the current set and clears future sets");
        _reset.setText("Reset set");
        _reset.addActionListener(this);
        add(_reset);
        _mutate = new JButton();
        _mutate.setActionCommand("mutate");
        _mutate.setToolTipText("Mutate selected pictures");
        _mutate.setText("Mutate");
        _mutate.addActionListener(this);
        add(_mutate);
        _breed = new JButton();
        _breed.setActionCommand("breed");
        _breed.setToolTipText("Breed selected pictures");
        _breed.setText("Breed");
        _breed.addActionListener(this);
        add(_breed);
        addSeparator();
        add(new JLabel("Mutation Rate:"));
        _mutationSlider = new JSlider(0, 100);
        _mutationSlider.setMajorTickSpacing(25);
        _mutationSlider.setMinorTickSpacing(10);
        _mutationSlider.setPaintTicks(true);
        _mutationSlider.setPaintLabels(true);
        _mutationSlider.addChangeListener(
                new ChangeListener() {
                    public void stateChanged (ChangeEvent evt)
                    {
                        _parent.setMutationRate(_mutationSlider.getValue() * 0.01);
                    }
                });
        _mutationSlider.setValue((int)(_parent.getMutationRate() * 100));
        _mutationSlider.setMaximumSize(new Dimension(150, 50));
        _mutationSlider.setToolTipText("Rate at which mutations occur during breeding");
        add(_mutationSlider);

        addSeparator();
        add(new JLabel("Random Depth:"));
        _randomSlider = new JSlider(0, 100);
        _randomSlider.setMajorTickSpacing(25);
        _randomSlider.setMinorTickSpacing(10);
        _randomSlider.setPaintTicks(true);
        _randomSlider.setPaintLabels(true);
        _randomSlider.addChangeListener(
                new ChangeListener() {
                    public void stateChanged (ChangeEvent evt)
                    {
                        _parent.setDepthRate(_randomSlider.getValue() * 0.01);
                    }
                });
        _randomSlider.setValue((int)(_parent.getDepthRate() * 100));
        _randomSlider.setMaximumSize(new Dimension(150, 50));
        _randomSlider.setToolTipText("Depth for which to generate functions");
        add(_randomSlider);

        addSeparator();
        JButton settings = new JButton();
        settings.setText("Add Image");
        settings.addActionListener(
                new ActionListener() {
                    public void actionPerformed (ActionEvent evt)
                    {
                        _parent.addImage();
                    }
                });                
        add(settings);
        addSeparator();
        JButton exit = new JButton();
        exit.setText("Exit");
        exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed (ActionEvent evt)
                    {
                        System.exit(0);
                    }
                });
        add(exit);
    }

    
    protected JButton makeNavigationButton (String imageName, String actionCommand,
                                            String toolTipText, String altText)
    {
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);

        String imgLocation = imageName + ".gif";
        java.net.URL imageURL = JBrowserToolbar.class.getResource(imgLocation);
        if (imageURL != null)
        {
            button.setIcon(new ImageIcon(imageURL, altText));
        }
        else
        {
            button.setText(altText);
            //System.err.println("Resource not found: " + imgLocation);
        }
        return button;
    }


    public void actionPerformed (ActionEvent evt)
    {
        if (evt.getActionCommand().equals("back"))         _parent.backward();
        else if (evt.getActionCommand().equals("forward")) _parent.forward();
        else if (evt.getActionCommand().equals("reset"))   _parent.reset();
        else if (evt.getActionCommand().equals("breed"))   _parent.breed();
        else if (evt.getActionCommand().equals("mutate"))  _parent.mutate();
        else if (evt.getActionCommand().equals("clear"))   _parent.clear();
    }
}
