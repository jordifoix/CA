package com.chillasso.chillasso.Class;

import com.chillasso.chillasso.Class.RegisteredUser;

import java.util.Stack;

import io.realm.RealmObject;

/**
 * Created by Enric on 30/03/2017.
 */

public class RegistratedUserStack extends RealmObject{
    private Stack<RegisteredUser> registeredUsers;

    public RegistratedUserStack(){
        registeredUsers = new Stack<RegisteredUser>();
    }

    public RegistratedUserStack(Stack<RegisteredUser> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public Stack<RegisteredUser> getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(Stack<RegisteredUser> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }
}
