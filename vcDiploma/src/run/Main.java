package run;

import javax.swing.UIManager;
import vc.controller.Controller;

/**
 *
 * @author Макс
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Controller c = new Controller();
        c.createGUI();
    }

}
