package ppic.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import ppic.model.Expression;
import ppic.view.jpc.JPCFactory;
import ppic.view.jpc.JPictureComponent;


@SuppressWarnings("serial")
class JBrowserSidebar extends JComponent
    implements ActionListener, ChangeListener
{
    private JPictureBrowser _parent;
    private JPictureComponent _pic;
    private TextArea _function;


    JBrowserSidebar (JPictureBrowser parent)
    {
        _parent = parent;
        BorderLayout mylayout = new BorderLayout();
        mylayout.setVgap(50);
        setLayout(mylayout);
        //setPreferredSize(new Dimension(250, 655));
        _function = new TextArea("", 7, 23, 1);
        _function.setEditable(false);
        Box functionbox = new Box(0);
        functionbox.add(Box.createHorizontalGlue());
        functionbox.add(_function);
        functionbox.add(Box.createHorizontalGlue());
        _pic = JPCFactory.makeEmptyImageWell(200, 200);
        _pic.addChangeListener(this);
        Box picbox = new Box(0);
        picbox.add(Box.createHorizontalGlue());
        picbox.add(_pic);
        picbox.add(Box.createHorizontalGlue());
        add(picbox, "North");
        add(functionbox, "Center");
        add(makeButtonStack(), "South");
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Picture Info"));
        revalidate();
        repaint();
    }


    JComponent makeButtonStack ()
    {
        Box outerButtons = new Box(1);
        Box buttons = new Box(0);
        buttons.add(Box.createGlue());
        JButton save = new JButton("Save");
        save.setActionCommand("save");
        save.setToolTipText("Save this function to a text file");
        save.addActionListener(this);
        buttons.add(save);
        JButton load = new JButton("Load");
        load.setActionCommand("load");
        load.setToolTipText("Replace this function with one loaded from a text file.");
        load.addActionListener(this);
        buttons.add(load);
        JButton zoom = new JButton("Zoom");
        zoom.setActionCommand("zoom");
        zoom.setToolTipText("Open function inspector window");
        zoom.addActionListener(this);
        buttons.add(zoom);
        outerButtons.add(buttons);
        buttons.add(Box.createGlue());
        outerButtons.add(Box.createGlue());
        return outerButtons;
    }


    public void actionPerformed (ActionEvent evt)
    {
        if (evt.getActionCommand().equals("save"))      doSave();
        else if (evt.getActionCommand().equals("load")) doLoad();
        else if (evt.getActionCommand().equals("zoom")) doZoom();
    }


    public void stateChanged (ChangeEvent evt)
    {
        _function.setText(_pic.getExpression().toString());
        repaint();
    }


    void setExpression (Expression expr)
    {
        if (expr != null)
        {
            _function.setText(expr.toString());
            _pic.setExpression(expr);
            _pic.go();
            repaint();
        }
    }


    private void doSave ()
    {
        if (_pic.getExpression() == null)
        {
            _parent.logError("No picture currently in sidebar.");
        }
        else
        {
            _parent.saveExpression(_pic.getExpression());
        }
    }


    private void doLoad ()
    {
        setExpression(_parent.loadExpression());
    }


    private void doZoom ()
    {
        if (_pic.getExpression() == null)
        {
            _parent.logError("No picture currently in sidebar.");
        }
        else
        {
            _parent.zoom(_pic.getExpression());
        }
    }
}
