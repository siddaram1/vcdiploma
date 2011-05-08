package vc.ui;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import vc.listener.VCListener;

/**
 *
 * @author Макс
 */
public class VCFrame extends JFrame{

     public JComboBox comboBox = new JComboBox();
     public JPanel original = new JPanel();
     public JPanel encleft = new JPanel();
     public JPanel encright = new JPanel();
     public JPanel result1 = new JPanel();
     public TextField textField;
     public java.awt.Checkbox check1 = new java.awt.Checkbox();
     public java.awt.Checkbox check2 = new java.awt.Checkbox();
//------------------------------------------------------------------------------
  //Construct the frame
  public VCFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      VCInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
//------------------------------------------------------------------------------
  //Component initialization
  private void VCInit() throws Exception  {
    MenuBar menu = new MenuBar();
    Menu file = new Menu("Файл");
    MenuItem openfile = new MenuItem("Открыть");
    openfile.addActionListener(new VCListener(this, "open"));

    MenuItem savefile = new MenuItem("Сохранить");
    savefile.addActionListener(new VCListener(this, "save"));

    MenuItem exit = new MenuItem("Выйти");
    exit.addActionListener(new VCListener(this, "exit"));

    Menu info = new Menu("Справка");
    MenuItem help = new MenuItem("Помощь");
    help.addActionListener(new VCListener(this, "help"));

    MenuItem about = new MenuItem("О программе");
    about.addActionListener(new VCListener(this, "about"));

    JPanel contentPane = new JPanel();
    JLabel name = new JLabel();
    JButton buttonOpen = new JButton();
    JButton buttonProcess = new JButton();

    file.add(openfile);
    file.add(savefile);
    file.add(exit);

    info.add(help);
    info.add(about);
    menu.add(file);
    menu.add(info);

    //name.setIcon(new ImageIcon(new java.net.URL("file:G://logo.jpg")));
    name.setText("");
    name.setBounds(new Rectangle(1, 3, 227, 189));

    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(null);

    this.setResizable(false);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(0,0,screenSize.width, screenSize.height);    
    this.setTitle("== Visual Cryptography ==");

    buttonOpen.setBounds(new Rectangle(10, 6, 120, 60));
    buttonOpen.setText("Открыть");
    buttonOpen.setToolTipText("Загрузить изображение");
    buttonOpen.setBorderPainted(true);
    buttonOpen.setRolloverEnabled(true);    
    buttonOpen.addMouseListener(new VCListener(this,"open"));

    buttonProcess.setBounds(new Rectangle(130, 6, 120, 60));
    buttonProcess.setText("Шифровать");
    buttonProcess.setToolTipText("Сгенерировать изображния");
    buttonProcess.setBorderPainted(true);
    buttonProcess.addMouseListener(new VCListener(this, "process"));

    this.setMenuBar(menu);

    original.setBounds(new Rectangle(10, 70, 200, 200));
    //original.addComponentListener(new VCListener(this, "origclick"));
    encleft.setBounds(new Rectangle(10, 280, 200, 200));
    encright.setBounds(new Rectangle(220, 280, 200, 200));

    result1.setBounds(new Rectangle(10, 510, 200, 200));
    result1.setOpaque(true);

    textField = new TextField();
    textField.setBounds(new Rectangle(350,6,500,30));
    textField.setFont(new Font("sansserif", Font.BOLD, 14));
    textField.setEnabled(false);

    check1.setBounds(new Rectangle(230, 510, 100, 10));
    check1.setLabel("Слой 1");
    check1.addMouseListener(new VCListener(this, "check1"));
    check1.setEnabled(false);

    check2.setBounds(new Rectangle(230, 560, 100, 10));
    check2.setLabel("Слой 2");
    check2.addMouseListener(new VCListener(this, "check2"));
    check2.setEnabled(false);

    contentPane.add(comboBox, null);
    contentPane.add(textField, null);
    contentPane.add(buttonOpen, null);
    contentPane.add(buttonProcess, null);
    contentPane.add(original, null);
    contentPane.add(encleft, null);
    contentPane.add(encright, null);
    contentPane.add(result1, null);
    contentPane.add(check1, null);
    contentPane.add(check2, null);

    comboBox.setBounds(new Rectangle(270, 6, 74, 34));
    comboBox.insertItemAt(".png",0);
    comboBox.insertItemAt(".gif",1);
    comboBox.setSelectedIndex(0);
    //comboBox.setVisible(false);
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }
}
