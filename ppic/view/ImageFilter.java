package ppic.view;

import java.io.File;
import javax.swing.filechooser.FileFilter;


public class ImageFilter extends FileFilter
{
    public static final String JPEG = "jpeg";
    public static final String JPG = "jpg";
    public static final String GIF = "gif";
    public static final String PNG = "png";


    public boolean accept (File f)
    {
        String filename = f.getName();
        return (f.isDirectory() || 
                filename.endsWith(JPEG) ||
                filename.endsWith(JPG) ||
                filename.endsWith(GIF) ||
                filename.endsWith(PNG));
    }

    
    public String getDescription ()
    {
        return "Images";
    }


    public static String getExtension (File f)
    {
        String name = f.getName();
        int idx = name.lastIndexOf('.');
        if (idx > 0 && idx < name.length() - 1)
        {
            return name.substring(idx + 1).toLowerCase();
        }
        return null;
    }
}
