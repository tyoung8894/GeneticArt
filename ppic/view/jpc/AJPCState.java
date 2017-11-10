package ppic.view.jpc;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseEvent;


abstract class AJPCState
{
    protected JPictureComponent _parent;
    protected boolean _isSelected;


    protected AJPCState (JPictureComponent parent)
    {
        _isSelected = false;
        _parent = parent;
    }


    boolean isSelected ()
    {
        return _isSelected;
    }


    void startDrag (DragGestureEvent evt)
    {
    }


    void acceptDrop (DropTargetDropEvent evt)
    {
        evt.rejectDrop();
    }


    void mouseClickEvent (MouseEvent evt)
    {
    }
}
