package vc.api;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import vc.controller.Controller;

/**
 *
 * @author Макс
 */
public class VCAPI {
    public static BufferedImage pic1;
    public static BufferedImage pic2;

  static int getBrightness(int rgb) {
    int r,g,b;
    Color pixColour = new Color(rgb);
    r=pixColour.getRed();
    g=pixColour.getGreen();
    b=pixColour.getBlue();
    rgb = (int)((r+g+b)/3);
    return rgb;
  }
//------------------------------------------------------------------------------
  static BufferedImage monochrome(BufferedImage picture) {
    int i,j,min=128,max=128,value=0,col;

    for (i=0;i<picture.getHeight();i++){
      for (j=0;j<picture.getWidth();j++) {
        value=getBrightness(picture.getRGB(j,i));
        if (value<min) min=value;
        if (value>max) max=value;
      }
    }

    value=(int)Math.ceil((max+min)/2);

    for (i=0;i<picture.getHeight();i++){
      for (j=0;j<picture.getWidth();j++) {
        col=getBrightness(picture.getRGB(j,i));
        if (col<value) picture.setRGB(j,i,-16777216);//black
        else picture.setRGB(j,i,-1);//white
      }
    }

    return picture;
  }
//------------------------------------------------------------------------------
  static BufferedImage meldImages(BufferedImage template, BufferedImage random) {
    int i,j;
    BufferedImage output = new BufferedImage(template.getWidth(),template.getHeight(),BufferedImage.TYPE_INT_RGB);

    for (i=0;i<template.getHeight();i++){
      for (j = 0; j < template.getWidth(); j++) {
        if (template.getRGB(j,i)<-8388608) {//black
          if (random.getRGB(j,i)<-8388608) output.setRGB(j,i,-1);
          else output.setRGB(j,i,-16777216);
        }
        else {
          if (random.getRGB(j,i)<-8388608) output.setRGB(j,i,-16777216);
          else output.setRGB(j,i,-1);
        }
      }
    }
    return output;
  }
//------------------------------------------------------------------------------
  static BufferedImage makeGrey(BufferedImage template) {//note; 0 is black in the top left corner, 1 has white in that corner
    BufferedImage output = new BufferedImage(template.getWidth()*2,template.getHeight()*2,BufferedImage.TYPE_INT_RGB);
    int i,j;

    for (i=0;i<template.getHeight();i++){
      for (j=0;j<template.getWidth();j++) {
        if (template.getRGB(j,i)<-8388608) {//black
          output.setRGB((j*2),(i*2),-16777216);//black
          output.setRGB((j*2),(i*2)+1,-1);//white
          output.setRGB((j*2)+1,(i*2),-1);//white
          output.setRGB((j*2)+1,(i*2)+1,-16777216);//black
        }
        else {
          output.setRGB((j*2),(i*2),-1);//white
          output.setRGB((j*2),(i*2)+1,-16777216);//black
          output.setRGB((j*2)+1,(i*2),-16777216);//black
          output.setRGB((j*2)+1,(i*2)+1,-1);//white
        }

      }
    }
    return output;
  }
//------------------------------------------------------------------------------
  static BufferedImage createRandom(BufferedImage template) {
    int i,j;
	//IF INTENDED FOR ANY 'SERIOUS' USE, REPLACE THE LINE BELOW WITH A BETTER SOURCE OF RANDOM NUMBERS
    Random rn = new Random();

    BufferedImage output = new BufferedImage(template.getWidth(),template.getHeight(),BufferedImage.TYPE_INT_RGB);
    for (i=0;i<template.getHeight();i++){
      for (j=0;j<template.getWidth();j++) {
        if (rn.nextBoolean()==false) output.setRGB((j),(i),-16777216);//black
        else output.setRGB((j),(i),-1);//white
      }
    }

    return output;
  }
//==============================================================================
  //work with files
//==============================================================================
  public BufferedImage loadImage(String filename){
    BufferedImage in;
    try {
      File filein = new File(filename);
      in = ImageIO.read(filein);
    }
    catch (Exception ex) {
      in = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }
    return in;
  }
//------------------------------------------------------------------------------
  public boolean validateImage(BufferedImage pic){
    boolean valid;
    int x=pic.getWidth();
    if (x>0) {
      x=pic.getHeight();
      if (x>0) valid=true;
        else valid=false;
    }
    else valid=false;
    return valid;
  }
//------------------------------------------------------------------------------
  public boolean saveImage(BufferedImage pic, String filename, int format) {
    boolean valid=false;
    String type="png";
    if (format==1) type="gif";
    filename.trim();
    filename=filename+"."+type;
    try {
      File fileout = new File(filename);
      ImageIO.write(pic, type, fileout);
      valid=true;
    }
    catch (Exception ex) {
      valid = false;
    }
    return valid;
  }
//==============================================================================
  public boolean Encrypt(String load, String name, int type) {
     boolean valid = false;
     BufferedImage input;

     input =loadImage(load);
     valid = validateImage(input);
     if (valid==true) {
       input = monochrome(input);
       BufferedImage pad1 = createRandom(input);
       BufferedImage pad2 = meldImages(input, pad1);
       valid = saveImage(makeGrey(pad1), name+"_1", type);
       pic1 = pad1;
       valid = saveImage(makeGrey(pad2), name+"_2", type);
       pic2 = pad2;
       String syfix = ".png";
       if (type == 1)
           syfix = ".gif";
       Controller.setEncImage("./"+name+"_1"+syfix, "./"+name+"_2"+syfix);
     }
     return valid;
   }
//------------------------------------------------------------------------------
   public static String getFileName(String path) {
     String named = "";
     String name = "";
     int i,j=0;

     String[] pathall = path.split("\\\\");
     named=pathall[pathall.length-1];
     String[] nameall = named.split("");

     for (i=0;i<nameall.length;i++) 
         if (nameall[i].equals("."))
             j=i;
        for (i=0;i<j;i++)
            name = name + nameall[i];
            return name;
   }

   public String decryptImage(){


       
   }
}
