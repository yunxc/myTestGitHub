package com.yun.effective.java.builder;

/**
 * @author Administrator
 * @date 2020/11/30 10:54
 */
public class User {
    private String userName;
    private int age;
    //可选参数
    private String address;
    private String phoneNumber;
    public static class Builder{
        private String userName;
        private int age;
        //可选参数的默认值
        private String address="";
        private String phoneNumber="";
        public Builder(String userName, int age){
            this.userName = userName;
            this.age = age;
        }
        public Builder address(String address){
            this.address = address;
            return this;
        }
        public Builder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }
    private User(Builder builder){
        this.userName = builder.userName;
        this.age = builder.age;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
    }
}
