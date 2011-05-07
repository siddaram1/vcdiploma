package vc.listener;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import vc.api.VCAPI;
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
       
//------------------------------------------------------------------------------
    public VCListener(VCFrame frame, String oper){
        this.frame = frame;
        this.oper = oper;
    }
//------------------------------------------------------------------------------
    void buttonOpen_mouseClicked() {
        if (JFileChooser.APPROVE_OPTION == jFileChooser1.showOpenDialog(null)) {
            path = jFileChooser1.getSelectedFile().getPath();
            called = controller.getFileName(path);
            frame.textField.setText(path);

            ImageIcon iconleft = new ImageIcon(path);
            JLabel label1 = new JLabel();
            label1.setIcon(iconleft);
            frame.original.add(label1);
            frame.original.updateUI();
        }
  }
//------------------------------------------------------------------------------
  void buttonProcess_mouseClicked(MouseEvent e) {
    boolean success = controller.encrypt(path, called, frame.comboBox.getSelectedIndex());
    if (success==true ) {
        Toolkit.getDefaultToolkit().beep();
    }
  }

  void showLayer(int layer){
      if(frame.check1.getState() && frame.check2.getState()){
          frame.result1.removeAll();
          controller.decryptImage(); //расшифровать, сохранить и загрузить
      }
      if ((layer == 1)&&(frame.check1.getState())){
          ImageIcon iconleft = new ImageIcon("./"+called+"_1.png");
          JLabel label1 = new JLabel();
          label1.setOpaque(true);
          label1.setIcon(iconleft);
          frame.result1.add(label1);
          frame.result1.updateUI();
      }else if ((layer == 1)&&(!frame.check2.getState())){
           frame.result1.removeAll();
           frame.result1.updateUI();
      }
      if ((layer == 2)&&(frame.check2.getState())){
          ImageIcon iconleft = new ImageIcon("./"+called+"_2.png");
          JLabel label2 = new JLabel();
          label2.setOpaque(true);
          label2.setIcon(iconleft);
          frame.result1.add(label2);
          frame.result1.updateUI();
      }else if ((layer == 2)&&(!frame.check1.getState())){
          frame.result1.removeAll();
          frame.result1.updateUI();
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

    public void actionPerformed(ActionEvent e) {

    if( oper.equals( "open" ) ) {
      buttonOpen_mouseClicked();
    }
    else if( oper.equals( "exit" ) ) {
      System.exit(0);
    }
    else if( oper.equals( "save" ) ) {
      System.out.println( "save" );
    }
    else if( oper.equals( "help" ) ) {
      System.out.println( "help" );
    }
    else if( oper.equals( "about" ) ) {
      System.out.println( "about" );
    }
    }
}
