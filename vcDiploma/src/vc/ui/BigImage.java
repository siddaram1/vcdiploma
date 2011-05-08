package vc.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Макс
 */
public class BigImage extends JFrame{

    public BigImage(String imageurl, Dimension size){
        getImagePanel(imageurl, size);
    }

    private void getImagePanel(String imageurl, Dimension size){
        this.setTitle("Изображение в оригинальную величину");
        JPanel panel = new JPanel();
        panel.setBounds(100,100,(int)size.getWidth(),(int)size.getHeight());
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon(imageurl);
        label.setIcon(icon);        
        panel = (JPanel) this.getContentPane();
        panel.setLayout(new FlowLayout());

        panel.add(label);
    }
}
