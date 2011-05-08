package vc.controller;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import vc.api.VCAPI;
import vc.ui.UserInterface;
import vc.ui.VCFrame;

/**
 *
 * @author Макс
 */
public final class Controller implements IController{
    static UserInterface ui = new UserInterface();
    VCAPI api = new VCAPI();
    VCFrame vcf;

    public void createGUI() {     
        ui.createGUI();
    }

    public boolean encrypt(String load, String name, int type) {
        return api.Encrypt(load, name, type);
    }

    public String getFileName(String path) {
        return api.getFileName(path);
    }

    public BufferedImage loadImage(String filename) {
        return api.loadImage(filename);
    }
    
    public boolean validateImage(BufferedImage pic) {
        return api.validateImage(pic);
    }

    public boolean saveImage(BufferedImage pic, String filename, int format){
        return api.saveImage(pic, filename, format);
    }

    public static void setEncImage(String encleft, String encright) {
        ui.setEncImage(encleft, encright);
    }

    public BufferedImage decryptImage() {
        return api.decryptImage();
    }

    public Dimension getFileSize(String path) {
        return api.getFileSize(path);
    }

}
