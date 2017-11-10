package ppic.view.jpc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import ppic.view.JPictureBrowser;


class ThumbnailState extends AMovableJPC
{
    private JPictureBrowser _parentBrowser;


    ThumbnailState (JPictureComponent parentpic, JPictureBrowser parentbrowser)
    {
        super(parentpic);
        _parentBrowser = parentbrowser;
    }


    void mouseClickEvent (MouseEvent e)
    {
        if (e.getButton() == 1 && e.getClickCount() == 1)
        {
            toggleSelected();
        }
        else if (e.getButton() == 1 && e.getClickCount() == 2 || e.getButton() == 3)
        {
            focus();
        }
        drawBorder();
    }


    private void toggleSelected ()
    {
        _isSelected = !_isSelected;
    }


    private void focus ()
    {
        _parentBrowser.sendToSidebar(_parent);
    }


    private void drawBorder ()
    {
        if (_isSelected)
        {
            _parent.setBorder(BorderFactory.createLineBorder(Color.green, 4));
        }
        else
        {
            _parent.setBorder(BorderFactory.createEmptyBorder());
        }
        _parent.repaint();
    }
}
