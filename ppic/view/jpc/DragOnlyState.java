package ppic.view.jpc;

import java.awt.dnd.DropTargetDropEvent;


class DragOnlyState extends ImageWellState
{
    DragOnlyState (JPictureComponent p)
    {
        super(p);
    }


    void acceptDrop (DropTargetDropEvent e)
    {
        e.rejectDrop();
    }
}
