package ppic.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JComponent;
import ppic.view.jpc.JPictureComponent;
import ppic.model.Expression;
import ppic.view.jpc.JPCFactory;


@SuppressWarnings("serial")
public class JPictureSet extends JComponent
{
	public static final int NUM_COLUMNS = 5;
    public static final int SPACING = 4;
    public static final int IMAGE_SIZE = 125;

    private JPictureComponent[] _pictures;


    public JPictureSet (JPictureBrowser parent, int numToShow)
    {
        GridLayout mylayout = new GridLayout(NUM_COLUMNS, NUM_COLUMNS);
        mylayout.setHgap(SPACING);
        mylayout.setVgap(SPACING);
        setLayout(mylayout);
        int size = (IMAGE_SIZE + SPACING) * NUM_COLUMNS;
        //setPreferredSize(new Dimension(size, size));
        setMaximumSize(new Dimension(size, size));
        //setMinimumSize(new Dimension(size, size));
        setDoubleBuffered(true);

        _pictures = new JPictureComponent[numToShow];
        for (int k = 0; k < _pictures.length; k++)
        {
            _pictures[k] = JPCFactory.makeThumbnail(null, parent);
            add(_pictures[k]);
        }
    }

    public void setSet (ArrayList<JPictureComponent> set)
    {
        if (set.size() == _pictures.length)
        {
            for (int k = 0; k < _pictures.length; k++)
            {
                _pictures[k] = set.get(k);
            }
        }
        else
        {
            System.out.println("Error: incorrect set size");
        }
    }

    public void go ()
    {
        removeAll();
        for (JPictureComponent current : _pictures)
        {
            if (current != null)
            {
                current.go();
                add(current);
            }
        }
        revalidate();
        repaint();
    }

    public ArrayList<Expression> getSelected ()
    {
        ArrayList<Expression> result = new ArrayList<Expression>();
        for (JPictureComponent current : _pictures)
        {
            if (current.isSelected())
            {
                result.add(current.getExpression());
            }
        }
        return result;
    }
}
