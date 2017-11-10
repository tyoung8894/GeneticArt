package ppic.view.jpc;

import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import ppic.model.Expression;


@SuppressWarnings("serial")
public class JPictureComponent extends JComponent
    implements DragGestureListener, DragSourceListener, DropTargetListener, MouseListener
{
    private int _h;
    private int _w;
    private Expression _pic;
    private BufferedImage _buff;
    private AJPCState _state;
    private Vector<ChangeListener> _listeners;


    JPictureComponent (Expression p, int h, int w)
    {
        _h = h;
        _w = w;
        _pic = p;
        _state = new GenericState(this);
        _listeners = new Vector<ChangeListener>();

        DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(this, 3, this);
        new DropTarget(this, 3, this);
        addMouseListener(this);
        init();
    }


    private void init ()
    {
        setPreferredSize(new Dimension(_w, _h));
        setMaximumSize(new Dimension(_w, _h));
        setMinimumSize(new Dimension(_w, _h));
        setDoubleBuffered(true);

        _buff = new BufferedImage(_w, _h, 1);
        for (int i = 0; i < _h; i++)
        {
            for (int j = 0; j < _w; j++)
            {
                _buff.setRGB(j, i, 0);
            }
        }
        revalidate();
        repaint();
    }


    public Expression getExpression ()
    {
        return _pic;
    }


    public void setExpression (Expression p)
    {
        _pic = p;
        fireStateChanged();
    }


    public void setState (AJPCState s)
    {
        _state = s;
    }


    public boolean isSelected ()
    {
        return _state.isSelected();
    }


    public void go ()
    {
        Runnable drawthread = new Runnable()
        {
            public void run ()
            {
                for (int inty = 0; inty < _h; inty++)
                {
                    float curry = (inty / (float)_h) * 2 - 1;
                    for (int intx = 0; intx < _w; intx++)
                    {
                        float currx = (intx / (float)_w) * 2 - 1;
                        _buff.setRGB(intx, inty,
                                     _pic.evaluate(currx, curry, 0).getJavaColor().getRGB());
                    }
                    repaint();
                }
            }
        };
        new Thread(drawthread).start();
    }


    protected void paintComponent (Graphics g)
    {
        if (isOpaque())
        {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.drawImage(_buff, 0, 0, this);
        g2d.dispose();
    }


    public void mouseClicked (MouseEvent e)
    {
        _state.mouseClickEvent(e);
    }


    public void mouseEntered (MouseEvent mouseevent)
    {
    }


    public void mouseExited (MouseEvent mouseevent)
    {
    }


    public void mousePressed (MouseEvent mouseevent)
    {
    }


    public void mouseReleased (MouseEvent mouseevent)
    {
    }


    public void dragGestureRecognized (DragGestureEvent e)
    {
        _state.startDrag(e);
    }


    public void dragDropEnd (DragSourceDropEvent dragsourcedropevent)
    {
    }


    public void dragEnter (DragSourceDragEvent dragsourcedragevent)
    {
    }


    public void dragExit (DragSourceEvent dragsourceevent)
    {
    }


    public void dragOver (DragSourceDragEvent dragsourcedragevent)
    {
    }


    public void dropActionChanged (DragSourceDragEvent dragsourcedragevent)
    {
    }


    public void drop (DropTargetDropEvent e)
    {
        _state.acceptDrop(e);
    }


    public void dragEnter (DropTargetDragEvent droptargetdragevent)
    {
    }


    public void dragExit (DropTargetEvent droptargetevent)
    {
    }


    public void dragOver (DropTargetDragEvent droptargetdragevent)
    {
    }


    public void dropActionChanged (DropTargetDragEvent droptargetdragevent)
    {
    }


    public void addChangeListener (ChangeListener listener)
    {
        _listeners.add(listener);
    }


    protected void fireStateChanged ()
    {
        ChangeEvent evt = new ChangeEvent(this);
        for (ChangeListener current : _listeners)
        {
            current.stateChanged(evt);
        }
    }
}
