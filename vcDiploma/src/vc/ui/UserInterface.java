package vc.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import vc.listener.VCListener;
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
//------------------------------------------------------------------------------
    public static void getAboutFrame(String imageurl, Dimension size){
        JFrame frame = new JFrame();
        frame.setTitle("О программе");
        frame.setBounds(0,0,500,380);
        frame.setResizable(false);
        JPanel panel = new JPanel();

        panel.setBounds(100,100,(int)size.getWidth(),(int)size.getHeight());
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon(imageurl);
        label.setIcon(icon);
        panel = (JPanel) frame.getContentPane();
        panel.setLayout(new FlowLayout());
        panel.add(label);
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
        panel.updateUI();
    }
 //-----------------------------------------------------------------------------
    public static String[] getOpenEncFrame(){
        JFrame frame = new JFrame();
        frame.setTitle("Открыть шифрованные изображения");
        frame.setBounds(0,0,300,300);
        frame.setResizable(false);

        JPanel panel = new JPanel();

        TextField path1 = new TextField();
        path1.setFont(new Font("sansserif", Font.BOLD, 14));
        path1.setEnabled(false);

        TextField path2 = new TextField();
        path2.setFont(new Font("sansserif", Font.BOLD, 14));
        path2.setEnabled(false);

        JButton open1 = new JButton();
        open1.setText("Изображение №1");
        open1.setToolTipText("Открыть первое изображние");
        open1.setBorderPainted(true);
        open1.addMouseListener(new VCListener(null, "openenc1"));

        JButton open2 = new JButton();
        open2.setText("Изображение №2");
        open2.setToolTipText("Открыть второе изображение");
        open2.setBorderPainted(true);
        open2.addMouseListener(new VCListener(null, "openenc2"));

        JButton done = new JButton();
        done.setText("Готово");
        done.setBorderPainted(true);
        done.addMouseListener(new VCListener(null, "openencboth"));

        panel = (JPanel) frame.getContentPane();
        panel.setLayout(new GridLayout(5,1));
        
        panel.add(path1);
        panel.add(path2);
        panel.add(open1);
        panel.add(open2);
        panel.add(done);

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
        panel.updateUI();
        return null;
    }
}
