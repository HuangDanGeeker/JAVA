package com.wang.io.BinaryFileIO;

import java.io.*;

/**
 * Created by 1 on 2018/2/13.
 */
public class BFileIO {
    public static void main(String[] args) {
        String inFileUrl = BFileIO.class.getResource("img.jpg").getPath();
        String outFileUrl_1 = inFileUrl.substring(0, inFileUrl.lastIndexOf("/")+1)+"fileOut.jpg";
        String outFileUrl_2 = inFileUrl.substring(0, inFileUrl.lastIndexOf("/")+1)+"bufferedOut.jpg";
        File file1 = new File(outFileUrl_1);
        File file2 = new File(outFileUrl_2);
        try {
            file1.delete();
            file2.delete();
            file1.createNewFile();
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            /**
             * 1. use FileInputStream && FileOutputStream
             */
            System.out.println("1. use FileInputStream && FileOutputStream");
            System.out.println("created file url : " + outFileUrl_1);
            System.out.println("created file : " + file1.createNewFile());
            FileInputStream fileInputStream = new FileInputStream(inFileUrl);
            int len ;
            byte[] buffer = new byte[1024];
            FileOutputStream fileOutputStream = new FileOutputStream(outFileUrl_1);
            while((len = fileInputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, len);
            }
            fileInputStream.close();
            fileOutputStream.close();


            /**
             * 2. use BufferedInputStream && BufferedOutputStream
             */
            System.out.println("2. use BufferedInputStream && BufferedOutputStream");
            System.out.println("created file url : " + outFileUrl_2);
            System.out.println("created file : " + file2.createNewFile());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(inFileUrl));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outFileUrl_2));
            while((len = bufferedInputStream.read(buffer)) != -1){
                bufferedOutputStream.write(buffer, 0, len);
            }
            bufferedInputStream.close();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }


    }
}
