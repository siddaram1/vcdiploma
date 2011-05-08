package vc.listener;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vc.controller.Controller;
import vc.ui.BigImage;

/**
 *
 * @author Макс
 */
public class vcMouseListener implements MouseListener{
    String imageurl;

    public vcMouseListener(String imageurl){
        this.imageurl = imageurl;
    }
    public void mouseClicked(MouseEvent e) {
        Controller contr = new Controller();
        BigImage bi = new BigImage(imageurl,contr.getFileSize(imageurl));
        bi.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = bi.getSize();
        bi.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        bi.setVisible(true);
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }

}
