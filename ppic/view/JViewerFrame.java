package ppic.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import ppic.model.Expression;
import ppic.view.jpc.JPCFactory;
import ppic.view.jpc.JPictureComponent;


@SuppressWarnings("serial")
public class JViewerFrame extends JFrame
{
    private JPictureComponent _pic;


    public JViewerFrame (Expression expr, int w, int h)
    {
        _pic = JPCFactory.makeGeneric(expr, h, w);
        _pic.go();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().add(new JScrollPane(_pic));
        pack();
        setVisible(true);
    }
}
