package vc.listener;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
    ImageIcon iconleft;
    
    
       
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
            iconleft = new ImageIcon(path);
            lblorig.setIcon(iconleft);
            frame.original.add(lblorig);
            frame.original.updateUI();

            lblorig.addMouseListener(new vcMouseListener(path));
        }
  }
//------------------------------------------------------------------------------
  void buttonProcess_mouseClicked(MouseEvent e) {
    boolean success = controller.encrypt(path, called, frame.comboBox.getSelectedIndex());
    frame.check1.setEnabled(true);
    frame.check2.setEnabled(true);
    if (success==true ) {
        Toolkit.getDefaultToolkit().beep();        
    }
  }

  void showLayer(int layer){
      JLabel lblresult = new JLabel();
      if(frame.check1.getState() && frame.check2.getState()){ //both checked
          frame.result1.removeAll();
          frame.result1.updateUI();
          BufferedImage out = controller.decryptImage(); //расшифровать, сохранить и загрузить
          controller.saveImage(out, "decrypt"+called, frame.comboBox.getSelectedIndex());
          iconleft = new ImageIcon("./decrypt"+called+frame.comboBox.getSelectedItem().toString());
          lblresult.setIcon(iconleft);
          frame.result1.add(lblresult);
          frame.result1.updateUI();
          lblresult.addMouseListener(new vcMouseListener("./decrypt"+called+frame.comboBox.getSelectedItem().toString()));
      }else{
        if ((layer == 1)&&(frame.check1.getState())){ //1-st checked
          iconleft = new ImageIcon("./"+called+"_1"+ frame.comboBox.getSelectedItem().toString());
          lblresult.setIcon(iconleft);
          frame.result1.add(lblresult);
          frame.result1.updateUI();
        }else if ((layer == 1)&&(!frame.check1.getState())){ //1-st unchecked
                if(frame.check2.getState()){ //2-nd checked
                    frame.result1.removeAll();
                    frame.result1.updateUI();
                    iconleft = new ImageIcon("./"+called+"_2"+ frame.comboBox.getSelectedItem().toString());
                    lblresult.setIcon(iconleft);
                    frame.result1.add(lblresult);
                    frame.result1.updateUI();
                }else{
                    frame.result1.removeAll();
                    frame.result1.updateUI();
                }
        }
        if ((layer == 2)&&(frame.check2.getState())){ //2-nd checked
          iconleft = new ImageIcon("./"+called+"_2"+ frame.comboBox.getSelectedItem().toString());
          lblresult.setIcon(iconleft);
          frame.result1.add(lblresult);
          frame.result1.updateUI();
        }else if ((layer == 2)&&(!frame.check2.getState())){ //2-nd unchecked
                if (frame.check1.getState()){ // 1-st checked
                    frame.result1.removeAll();
                    frame.result1.updateUI();
                    iconleft = new ImageIcon("./"+called+"_1"+ frame.comboBox.getSelectedItem().toString());
                    lblresult.setIcon(iconleft);
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
