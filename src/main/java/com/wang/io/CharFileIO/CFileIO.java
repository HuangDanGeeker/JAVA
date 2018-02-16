package com.wang.io.CharFileIO;

import java.io.*;

/**
 * Created by 1 on 2018/2/13.
 */
public class CFileIO {

    public static void main(String[] args) {
        String fileUrl = CFileIO.class.getResource("article.txt").getPath();
        try {
            System.out.println("\nusing FileInputStream");
            FileInputStream fileInputStream = new FileInputStream(fileUrl);
            byte[] buffer = new byte[20];
            fileInputStream.read(buffer);
            System.out.println(new String(buffer));

            System.out.println("\nusing BufferedReader");
            BufferedReader reader = new BufferedReader(new FileReader(fileUrl));
            System.out.println(reader.readLine());
            char[] buff = new char[20];
            reader.read(buff);
            System.out.println(new String(buff));

            System.out.println("\nusing BufferedInputStream");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedInputStream.read(buffer);
            System.out.println(new String(buffer));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
