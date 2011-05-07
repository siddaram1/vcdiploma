package vc.controller;

import java.awt.image.BufferedImage;

/**
 *
 * @author Макс
 */
public interface IController {
    public void createGUI();    
    public boolean encrypt(String load, String name, int type);
    public String getFileName(String path);
    public boolean validateImage(BufferedImage pic);
    public boolean saveImage(BufferedImage pic, String filename, int format);
    public BufferedImage loadImage(String filename);
    public BufferedImage decryptImage();
}
