package ppic.view.jpc;

import javax.swing.BorderFactory;


class ImageWellState extends AMovableJPC
{
    ImageWellState (JPictureComponent pic)
    {
        super(pic);
        _parent.setBorder(BorderFactory.createBevelBorder(1));
    }
}
