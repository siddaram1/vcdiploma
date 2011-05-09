package vc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import vc.listener.vcMouseListener;

/**
 *
 * @author Макс
 */
public class UserInterface implements IUserInterfase{
    boolean packFrame = false;
    VCFrame frame;

    public void createGUI() {
       frame = new VCFrame();

    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
    frame.validate();
    }

//------------------------------------------------------------------------------
    public void setEncImage(String  pathleft, String pathright){
        ImageIcon iconleft = new ImageIcon(pathleft);
        JLabel lblencleft = new JLabel();
        lblencleft.addMouseListener(new vcMouseListener(pathleft));
        lblencleft.setIcon(iconleft);
        frame.encleft.add(lblencleft);
        frame.encleft.updateUI();

        ImageIcon iconright = new ImageIcon(pathright);
        JLabel lblencright = new JLabel();
        lblencright.addMouseListener(new vcMouseListener(pathright));
        lblencright.setIcon(iconright);
        frame.encright.add(lblencright);
        frame.encright.updateUI();
    }

}
