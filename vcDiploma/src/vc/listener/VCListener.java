package vc.listener;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import vc.controller.Controller;
import vc.ui.VCFrame;

/**
 *
 * @author Макс
 */
public class VCListener extends MouseAdapter implements  ActionListener{
    private static String path="";
    private static String called="";
    private String oper = "";
    VCFrame frame;
    JFileChooser jFileChooser1 = new JFileChooser();
    Controller controller = new Controller();
    ImageIcon icon;
    
    
       
//------------------------------------------------------------------------------
    public VCListener(VCFrame frame, String oper){
        this.frame = frame;
        this.oper = oper;        
    }
//------------------------------------------------------------------------------
    void buttonOpen_mouseClicked() {
        JLabel lblorig = new JLabel();
        if (JFileChooser.APPROVE_OPTION == jFileChooser1.showOpenDialog(null)) {
            path = jFileChooser1.getSelectedFile().getPath();
            called = controller.getFileName(path);
            frame.textField.setText(path);
            frame.original.removeAll();
            frame.encleft.removeAll();
            frame.encright.removeAll();
            frame.result1.removeAll();

            frame.original.updateUI();
            frame.encleft.updateUI();
            frame.encright.updateUI();
            frame.result1.updateUI();

            frame.check1.setState(false);
            frame.check2.setState(false);
            frame.check1.setEnabled(false);
            frame.check2.setEnabled(false);
            icon = new ImageIcon(path);

            lblorig.setIcon(controller.scale(icon.getImage(), frame));
            frame.original.add(lblorig);
            frame.original.updateUI();

            lblorig.addMouseListener(new vcMouseListener(path));
            frame.buttonProcess.setEnabled(true);
        }
  }
//------------------------------------------------------------------------------
  void buttonProcess_mouseClicked(MouseEvent e) {
      if (frame.buttonProcess.isEnabled()){
        boolean success = controller.encrypt(path, called, frame.comboBox.getSelectedIndex());
        frame.check1.setEnabled(true);
        frame.check2.setEnabled(true);
        if (success==true ) {
            Toolkit.getDefaultToolkit().beep();
        }
      }
  }

  void showLayer(int layer){
      JLabel lblresult = new JLabel();
      if(frame.check1.getState() && frame.check2.getState()){ //both checked
          frame.result1.removeAll();
          frame.result1.updateUI();
          BufferedImage out = controller.decryptImage(); //расшифровать, сохранить и загрузить
          controller.saveImage(out, "decrypt"+called, frame.comboBox.getSelectedIndex());
          icon = new ImageIcon("./decrypt"+called+frame.comboBox.getSelectedItem().toString());
          lblresult.setIcon(controller.scale(icon.getImage(), frame));
          frame.result1.add(lblresult);
          frame.result1.updateUI();
          lblresult.addMouseListener(new vcMouseListener("./decrypt"+called+frame.comboBox.getSelectedItem().toString()));
      }else{
        if ((layer == 1)&&(frame.check1.getState())){ //1-st checked
          icon = new ImageIcon("./"+called+"_1"+ frame.comboBox.getSelectedItem().toString());
          lblresult.setIcon(icon);
          frame.result1.add(lblresult);
          frame.result1.updateUI();
        }else if ((layer == 1)&&(!frame.check1.getState())){ //1-st unchecked
                if(frame.check2.getState()){ //2-nd checked
                    frame.result1.removeAll();
                    frame.result1.updateUI();
                    icon = new ImageIcon("./"+called+"_2"+ frame.comboBox.getSelectedItem().toString());
                    lblresult.setIcon(icon);
                    frame.result1.add(lblresult);
                    frame.result1.updateUI();
                }else{
                    frame.result1.removeAll();
                    frame.result1.updateUI();
                }
        }
        if ((layer == 2)&&(frame.check2.getState())){ //2-nd checked
          icon = new ImageIcon("./"+called+"_2"+ frame.comboBox.getSelectedItem().toString());
          lblresult.setIcon(icon);
          frame.result1.add(lblresult);
          frame.result1.updateUI();
        }else if ((layer == 2)&&(!frame.check2.getState())){ //2-nd unchecked
                if (frame.check1.getState()){ // 1-st checked
                    frame.result1.removeAll();
                    frame.result1.updateUI();
                    icon = new ImageIcon("./"+called+"_1"+ frame.comboBox.getSelectedItem().toString());
                    lblresult.setIcon(icon);
                    frame.result1.add(lblresult);
                    frame.result1.updateUI();
                }else{
                    frame.result1.removeAll();
                    frame.result1.updateUI();
            }
        }
      }      
  }
//------------------------------------------------------------------------------
  public void mouseClicked(MouseEvent e) {
      if (oper.equals("open")){
        buttonOpen_mouseClicked();
      }
      else if (oper.equals("process")){
        buttonProcess_mouseClicked(e);
      }else if (oper.equals("check1")){
        showLayer(1);
      }else if (oper.equals("check2")){
        showLayer(2);
      }
  }
//------------------------------------------------------------------------------
    public void actionPerformed(ActionEvent e) {

    if( oper.equals( "open" ) ) {
      buttonOpen_mouseClicked();
    }
    else if( oper.equals( "exit" ) ) {
      System.exit(0);
    }
    else if( oper.equals( "save" ) ) {
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle("Сохранить шифр. изображения");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    chooser.setAcceptAllFileFilterUsed(false);
    //
    if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
      String path = chooser.getSelectedFile().toString();
      BufferedImage bi1  = controller.loadImage("./"+called+"_1"+frame.comboBox.getSelectedItem().toString());
      BufferedImage bi2  = controller.loadImage("./"+called+"_2"+ frame.comboBox.getSelectedItem().toString());
      controller.saveImage(bi1, path+"\\_1", frame.comboBox.getSelectedIndex());
      controller.saveImage(bi2, path+"\\_2", frame.comboBox.getSelectedIndex());
      }
    else {
      System.out.println("No Selection ");
      }
    } else if (oper.equals("help")) {
      System.out.println( "help" );
    }
    else if( oper.equals( "about" ) ) {
      Controller contr = new Controller();
      getAboutFrame("./info.png",contr.getFileSize("./info.png"));
    }
    }
//------------------------------------------------------------------------------
    private void getAboutFrame(String imageurl, Dimension size){
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
}
