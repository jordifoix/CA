package com.chillasso.chillasso.Class;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Enric on 02/04/2017.
 */

public class UserPhone extends RealmObject {

    @PrimaryKey
    private String phone;

    public UserPhone(){
        phone = "";
    }
    public UserPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

   /* public void setPhone(String phone) {
        this.phone = phone;
    }*/
}
