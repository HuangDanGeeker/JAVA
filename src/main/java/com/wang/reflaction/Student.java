package com.wang.reflaction;

/**
 * Created by HuangDanGeeker on 2018/2/15.
 */
public class Student {
    public String name;
    private String nickName;
    int age;

    public Student(String name, String nickName, int age){
        this.name = name;
        this.nickName = nickName;
        this.age = age;
    }

    private Student(String name){
        this.name = name;
        this.nickName = "private name";
        this.age = -1;
    }

    @Override
    public String toString() {
        return "{name="+this.name+",nickName="+this.nickName+", age="+this.age+"}";
    }

    private String selfIntroduction(){
        return this.toString();
    }

    public String introduction(){
        return "{name="+this.name+","+ "age="+this.age+"}";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
