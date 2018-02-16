package com.wang.reflaction;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by HuangDanGeeker on 2018/2/15.
 */
public class Reflaction {

    public static void main(String[] args) {
        /**
         * there ways to get the relative 'Class'
         */
        try {
            // 1. static method : Class.forName(String)
            Class.forName("com.wang.reflaction.Student");
            // 2.
            Class sc = Student.class;
            // 3.
            Student s1 = new Student("wang", "wang", 11);
            Class sc2 = s1.getClass();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // invoke constructor
        /**
         *  use 'getConstructors()' get all the public constructors
         *  use 'getDeclearedConstuctors()' get all the constructors
         */
        Constructor[] publicConstructors = Student.class.getConstructors();
        Constructor[] allConstructors = Student.class.getDeclaredConstructors();
        /**
         * use 'getConstructor(Class .... parameterTypes)' get single public constructor
         * use 'getConstructor(Class .... parameterTypes)' get single constructor
         */
        Constructor c = null;
        try {
            // public constructor
            c = Student.class.getConstructor(String.class, String.class, int.class);    //can't use Integer.class replace int.class
            Student student =(Student)c.newInstance("sss", "ssss", 123 );
            System.out.println(student.toString());
            // private&&protected constructor
            c = Student.class.getDeclaredConstructor(String.class);
            c.setAccessible(true);
            student = (Student) c.newInstance("ls");
            System.out.println(student);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        /**
         * Field
         *  use 'getField(String)' to get public Fields
         *  use 'getDeclearedField(String)' to get private&protected&&default Fields
         *  use 'getField&&getDeclaredField' to get all fields
         */

        Student s = new Student("wang", "wang", 22);
        try {
            Field nameField = s.getClass().getField("name");
            System.out.println("gotten name : " + nameField.toString());
            Field nickNameField = s.getClass().getDeclaredField("nickName");
            System.out.println("gotten name : " + nickNameField.toString());
            Field ageField = s.getClass().getDeclaredField("age");
            System.out.println("gotten name : " + ageField.toString());
            // change object field
            nameField.set(s, "changed");
            System.out.println(s.toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        /**
         * method
         *  use 'getMethod(String)' to get public method
         *  use 'getDeclaredMethod(String)' to get private && protected method
         *  use 'getMethods&&getDeclaredMethods()' to get all methods
         */

        try {
            // reflect public method
            Method privMethod = s.getClass().getDeclaredMethod("selfIntroduction");
            privMethod.setAccessible(true);
            String msg = (String) privMethod.invoke(s);
            System.out.println(msg);
            // reflect protected&&private method
            Method pubMethod = s.getClass().getMethod("introduction");
            msg = (String) pubMethod.invoke(s);
            System.out.println(msg);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
