package com.wang.io;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by 1 on 2018/2/13.
 */
public class ObjectIO {

    private static class Obj implements Serializable{
        public String name;
        public String id;
        public int age;

        @Override
        public String toString() {
            return "{name='"+name+"' id='"+id+"' age='"+age+"'}";
        }
    }

    public static void main(String[] args) {

        Obj obj = new Obj();
        obj.age = 11;
        obj.name = "sss";
        obj.id = "mmm";

        try {
            URL fileUrl = ObjectIO.class.getResource("obj.obj");
            String ss = URLDecoder.decode(fileUrl.getPath(), "UTF-8");
            File file = new File(URLDecoder.decode(fileUrl.getPath(), "UTF-8"));

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.writeInt(1234);
            objectOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Obj readedObj = (Obj) objectInputStream.readObject();
            System.out.println(readedObj.toString());
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
