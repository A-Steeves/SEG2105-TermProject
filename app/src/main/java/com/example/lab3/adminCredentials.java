package com.example.lab3;

public class adminCredentials {

    private String AdminUsername = "Admin@uottawa.ca";
    private String AdminPassword = "AdminPassword";


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
