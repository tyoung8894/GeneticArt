package ppic.view.jpc;

import ppic.model.Expression;
import ppic.view.JPictureBrowser;
import ppic.view.JPictureSet;


public class JPCFactory
{
    public static JPictureComponent makeThumbnail (Expression ipp, JPictureBrowser parent)
    {
        JPictureComponent pic = new JPictureComponent(ipp, JPictureSet.IMAGE_SIZE, JPictureSet.IMAGE_SIZE);
        pic.setState(new ThumbnailState(pic, parent));
        return pic;
    }


    public static JPictureComponent makeGeneric (Expression ipp, int h, int w)
    {
        return new JPictureComponent(ipp, h, w);
    }


    public static JPictureComponent makeEmptyGeneric (int h, int w)
    {
        return makeGeneric(null, h, w);
    }


    public static JPictureComponent makeImageWell (Expression ipp, int h, int w)
    {
        JPictureComponent pic = new JPictureComponent(ipp, h, w);
        pic.setState(new ImageWellState(pic));
        return pic;
    }


    public static JPictureComponent makeEmptyImageWell (int h, int w)
    {
        return makeImageWell(null, h, w);
    }


    public static JPictureComponent makeDragOnlyImageWell (Expression ipp, int h, int w)
    {
        JPictureComponent pic = new JPictureComponent(ipp, h, w);
        pic.setState(new DragOnlyState(pic));
        return pic;
    }
}
