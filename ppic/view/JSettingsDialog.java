package ppic.view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import ppic.model.Parser;
import ppic.model.Expression;
import ppic.model.operators.APPExtImage;
import ppic.view.jpc.JPCFactory;


@SuppressWarnings("serial")
public class JSettingsDialog extends JDialog implements ActionListener
{
    private static JFileChooser ourChooser = 
        new JFileChooser(System.getProperties().getProperty("user.dir"));
    static {
        ourChooser.addChoosableFileFilter(new ImageFilter());
        ourChooser.setAcceptAllFileFilterUsed(false);
    }

    private JList<String> _imageList;
    private DefaultListModel<String> _listModel;
    private JButton _addImage;
    private JButton _removeImage;
    private JButton _sendToSideBar;
    private JButton _ok;
    private JPictureBrowser _log;


    public JSettingsDialog (Frame parent, JPictureBrowser log)
    {
        super(parent, true);
        _log = log;

        setTitle("Settings");
        setDefaultCloseOperation(2);
        getContentPane().setLayout(new BoxLayout(getContentPane(), 3));
        Box everything = new Box(1);
        everything.add(Box.createGlue());
        Box imagestuff = new Box(1);
        imagestuff.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createBevelBorder(0), "External Image Sources"));
        _imageList = new JList<String>();
        _listModel = new DefaultListModel<String>();
        _imageList.setSelectionMode(0);
        _imageList.setSelectedIndex(0);
        _imageList.setVisibleRowCount(5);

        ArrayList<String> imglist = Parser.singleton.getImageList();
        for (int i = 0; i < imglist.size(); i++)
        {
            _listModel.addElement(imglist.get(i));
        }
        JScrollPane listScrollPane = new JScrollPane(_imageList);
        _imageList.setModel(_listModel);
        Box imagearea = new Box(0);
        imagearea.add(Box.createGlue());
        imagearea.add(listScrollPane);
        imagearea.add(Box.createGlue());
        imagestuff.add(imagearea);
        imagestuff.add(Box.createGlue());
        Box imageoptions = new Box(0);
        imageoptions.add(Box.createGlue());
        _addImage = new JButton();
        _addImage.setText("Add Image");
        _addImage.setActionCommand("addimage");
        _addImage.addActionListener(this);
        imageoptions.add(_addImage);
        _removeImage = new JButton();
        _removeImage.setText("Remove Image");
        _removeImage.setActionCommand("removeimage");
        _removeImage.addActionListener(this);
        imageoptions.add(_removeImage);
        _sendToSideBar = new JButton("Send to Sidebar");
        _sendToSideBar.setActionCommand("sendside");
        _sendToSideBar.addActionListener(this);
        imageoptions.add(_sendToSideBar);
        imageoptions.add(Box.createGlue());
        imagestuff.add(imageoptions);
        imagestuff.add(Box.createGlue());
        everything.add(imagestuff);
        everything.add(Box.createGlue());
        _ok = new JButton();
        _ok.setText("Ok");
        _ok.setActionCommand("ok");
        _ok.addActionListener(this);
        everything.add(_ok);
        getContentPane().add(everything);
        pack();
    }


    public void actionPerformed (ActionEvent evt)
    {
        if (evt.getActionCommand().equals("ok")) setVisible(false);
        else if (evt.getActionCommand().equals("addimage"))
        {
            int returnVal = ourChooser.showDialog(this, "Attach");
            if (returnVal == 0)
            {
                File file = ourChooser.getSelectedFile();
                if (! file.exists())
                {
                    _log.logError("Unable to locate file " + file.getName());
                    return;
                }
                _listModel.addElement(file.toString());
                Parser.singleton.addFile(file.toString());
            }
            ourChooser.setSelectedFile(null);
        }
        else if (evt.getActionCommand().equals("removeimage"))
        {
            int idx = _imageList.getSelectedIndex();
            if (idx >= 0)
            {
                Parser.singleton.removeFile((String)_listModel.remove(idx));
            }
        }
        else if (evt.getActionCommand().equals("sendside"))
        {
            int idx = _imageList.getSelectedIndex();
            if (idx >= 0) try
            {
                Expression ipp = APPExtImage.simpleImage((String)_listModel.get(idx));
                _log.sendToSidebar(JPCFactory.makeThumbnail(ipp, _log));
            }
            catch (Exception e)
            {
                _log.logError("Unable to load external file.");
            }
        }
    }
}
