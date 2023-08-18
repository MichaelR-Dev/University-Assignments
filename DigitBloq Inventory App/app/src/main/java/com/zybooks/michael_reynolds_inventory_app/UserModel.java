package com.zybooks.michael_reynolds_inventory_app;

public class UserModel{

    //Members

    public String username;
    public String password;

    //Constructor

    public UserModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    //Getters & Setters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
