package com.example.lab3;

public class Course {
    private String code;
    private String name;

    public Course(String code, String name){
        this.code = code;
        this.name = name;
    }

    public Course(){}

    public String getCode(){
        return code;
    }
    public String getName(){
        return name;
    }
    public void setCode(String newCode){code = newCode;}
    public void setName(String newName){name = newName;}
}
