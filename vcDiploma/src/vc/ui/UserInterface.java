package vc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    frame.validate();
    frame.repaint();
    frame.setVisible(true);
    
    }

//------------------------------------------------------------------------------
    public void setEncImage(String  pathleft, String pathright){
        ImageIcon iconleft = new ImageIcon(pathleft);
        JLabel label1 = new JLabel();
        label1.setIcon(iconleft);
        frame.encleft.add(label1);
        frame.encleft.updateUI();

        ImageIcon iconright = new ImageIcon(pathright);
        JLabel label2 = new JLabel();
        label2.setIcon(iconright);
        frame.encright.add(label2);
        frame.encright.updateUI();
    }
}
