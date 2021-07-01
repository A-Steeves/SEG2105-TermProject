package com.example.lab3;

public class adminCredentials {

    private String AdminUsername = "admin";
    private String AdminPassword = "admin123";

    //Class that stores admin pass and username.
    public adminCredentials(){

    }
    public String getUsername(){
        return AdminUsername;
    }
    public String getPassword(){
        return AdminPassword;
    }
    public void setUsername(String name){
        AdminUsername = name;
    }
    public void setPassword(String pass){
        AdminUsername = pass;
    }

}
