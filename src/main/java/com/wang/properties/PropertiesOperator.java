package com.wang.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 1 on 2018/2/13.
 */
public class PropertiesOperator {
    public static void main(String[] args){
        try{
            InputStream in = PropertiesOperator.class.getResourceAsStream("pro.properties");
            Properties  properties = new Properties();
            properties.load(in);
            System.out.println(properties);
            System.out.println("user = " + properties.getProperty("user"));
            System.out.println();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
