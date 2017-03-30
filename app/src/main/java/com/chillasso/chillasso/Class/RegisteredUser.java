package com.chillasso.chillasso.Class;

import io.realm.RealmObject;

/**
 * Created by Enric on 30/03/2017.
 */

public class RegisteredUser extends RealmObject {
    private String phoneNumber;
    private String password;

    public RegisteredUser(){
        phoneNumber = "";
        password = "";
    }

    public RegisteredUser(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
