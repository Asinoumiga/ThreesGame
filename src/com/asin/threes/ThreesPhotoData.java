package com.asin.threes;

import javax.swing.*;

public class ThreesPhotoData {
    public static ImageIcon image0 = new ImageIcon(ThreesPhotoData.class.getResource("photo/0.png"));
    public static ImageIcon image1 = new ImageIcon(ThreesPhotoData.class.getResource("photo/1.png"));
    public static ImageIcon image2 = new ImageIcon(ThreesPhotoData.class.getResource("photo/2.png"));
    public static ImageIcon image3 = new ImageIcon(ThreesPhotoData.class.getResource("photo/3.png"));
    public static ImageIcon image6 = new ImageIcon(ThreesPhotoData.class.getResource("photo/6.png"));
    public static ImageIcon image12 = new ImageIcon(ThreesPhotoData.class.getResource("photo/12.png"));
    public static ImageIcon image24 = new ImageIcon(ThreesPhotoData.class.getResource("photo/24.png"));
    public static ImageIcon image48 = new ImageIcon(ThreesPhotoData.class.getResource("photo/48.png"));
    public static ImageIcon image96 = new ImageIcon(ThreesPhotoData.class.getResource("photo/96.png"));
    public static ImageIcon image192 = new ImageIcon(ThreesPhotoData.class.getResource("photo/192.png"));
    public static ImageIcon image384 = new ImageIcon(ThreesPhotoData.class.getResource("photo/384.png"));
    public static ImageIcon image768 = new ImageIcon(ThreesPhotoData.class.getResource("photo/768.png"));
    public static ImageIcon image1536 = new ImageIcon(ThreesPhotoData.class.getResource("photo/1536.png"));
    public static ImageIcon image3072 = new ImageIcon(ThreesPhotoData.class.getResource("photo/3072.png"));
    public static ImageIcon image6144 = new ImageIcon(ThreesPhotoData.class.getResource("photo/6144.png"));
    public static ImageIcon turn1 = new ImageIcon(ThreesPhotoData.class.getResource("photo/turn1.png"));
    public static ImageIcon turn2 = new ImageIcon(ThreesPhotoData.class.getResource("photo/turn2.png"));
    public static ImageIcon turn3 = new ImageIcon(ThreesPhotoData.class.getResource("photo/turn3.png"));
    public static ImageIcon pigpig = new ImageIcon(ThreesPhotoData.class.getResource("photo/pigpig.png"));

    public static ImageIcon getImage(int num){
        String imageString = "image";
        int imageNum = 0;
        if(num>0&&num<3){
            imageNum = num;
        }else if(num>=3){
            imageNum = 3*(int)Math.pow(2,(num-3));
        }
        imageString+=imageNum;
        ImageIcon image = null;
        try {
            image = (ImageIcon) ThreesPhotoData.class.getField(imageString).get(ThreesPhotoData.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return image;
    }

    public static ImageIcon getTurn(int num){
        String imageString = "turn";
        if(num<=3&&num>0){
            imageString+=num;
        }else{
            return null;
        }
        ImageIcon image = null;
        try{
            image = (ImageIcon) ThreesPhotoData.class.getField(imageString).get(ThreesPhotoData.class);
        } catch (Exception e ){
            e.printStackTrace();
        }
        return image;
    }

    public static ImageIcon getPig(){
        ImageIcon image = pigpig;
        return pigpig;
    }
}
