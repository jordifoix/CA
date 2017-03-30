package com.chillasso.chillasso.Class;

import io.realm.RealmObject;

/**
 * Created by Enric on 30/03/2017.
 */

public class UserRegistration extends RealmObject {

    private String phoneNumber;
    private String password;

    public UserRegistration(){
        phoneNumber = "";
        password = "";
    }

    public UserRegistration(String phoneNumber, String password) {
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
