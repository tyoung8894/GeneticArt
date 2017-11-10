package ppic.view.jpc;

import java.awt.datatransfer.*;
import java.awt.dnd.*;
import ppic.model.Parser;


abstract class AMovableJPC extends AJPCState
{
    AMovableJPC (JPictureComponent parent)
    {
        super(parent);
    }


    void startDrag (DragGestureEvent evt)
    {
        if (_parent.getExpression() != null)
        {
            evt.startDrag(DragSource.DefaultCopyDrop,
                          new StringSelection(_parent.getExpression().toString()),
                          _parent);
        }
    }


    void acceptDrop (DropTargetDropEvent evt)
    {
        try
        {
            Transferable tr = evt.getTransferable();
            if (evt.isDataFlavorSupported(DataFlavor.stringFlavor))
            {
                String expr = (String)tr.getTransferData(DataFlavor.stringFlavor);
                System.out.println(expr);
                _parent.setExpression(Parser.singleton.parse(expr));
                _parent.go();
                evt.acceptDrop(3);
                evt.dropComplete(true);
            }
            else
            {
                evt.rejectDrop();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("A drag exception occured.  You may need to repeat your drag action for it to take effect.");
        }
    }
}
