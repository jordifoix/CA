package com.chillasso.chillasso.Class;

import java.util.List;

/**
 * Created by Enric on 01/04/2017.
 */

public class Contact {

    private List<String> groupsId;
    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Contact(List<String> groupsId, String name, String phone) {
        this.groupsId = groupsId;
        this.name = name;
        this.phone = phone;
    }

    public List<String> getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(List<String> groupsId) {
        this.groupsId = groupsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
