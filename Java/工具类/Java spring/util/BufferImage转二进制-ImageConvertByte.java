package com.example.demo.util;

/**
 * @author HuaRunSheng
 * @date 2023/2/20 21:20
 * @description :
 */
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImageConvertByte {
    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    public static void main(String[] args) {
        System.out.println(getImageBinary("c://20090709442.jpg", "jpg"));

        base64StringToImage(getImageBinary("c://20090709442.jpg", "jpg"), "c://20090709443.jpg", "jpg");
    }

    static String getImageBinary(String filePath, String fileType){
        File f = new File(filePath);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            ImageIO.write(bi, fileType, bas);
            byte[] bytes = bas.toByteArray();

            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    static String getImageBinary(BufferedImage bi){
        try {
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", bas);
            byte[] bytes = bas.toByteArray();

            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @param base64String
     * @param filePath
     * @param fileType: jpg,bmp,
     */
    static void base64StringToImage(String base64String,String filePath, String fileType){
        try {
            byte[] bytes1 = decoder.decodeBuffer(base64String);

            ByteArrayInputStream bis = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 =ImageIO.read(bis);
            File w2 = new File(filePath);//可以是jpg,png,gif格式
            ImageIO.write(bi1, fileType, w2);//不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

