package com.wang.io.BinaryFileIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

/**
 * Created by 1 on 2018/2/13.
 */
public class BFileIO {
    public static void main(String[] args) {
        String inFileUrl = BFileIO.class.getResource("img.jpg").getPath();
        String outFileUrl = inFileUrl.substring(0, inFileUrl.lastIndexOf("/")+1)+"out.jpg";
        File file = new File(outFileUrl);
        try {
            if(file.exists()){
                file.delete();
            }
            System.out.println("created file url : "+ outFileUrl);
            System.out.println("created file : " + file.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //use FileInputStream && FileOutputStream
        try {
            FileInputStream fileInputStream = new FileInputStream(inFileUrl);
            int len ;
            byte[] buffer = new byte[1024];
            FileOutputStream fileOutputStream = new FileOutputStream(outFileUrl);
            while((len = fileInputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }


    }
}
