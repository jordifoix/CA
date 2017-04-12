package com.chillasso.chillasso.Class;

import java.util.List;

/**
 * Created by Enric on 03/04/2017.
 */

public class Group {

    private String admin_phonenumber;
    private String name;
    private List<String> groupMembers;

    public Group(String admin_phonenumber,String name, List<String> groupMembers) {
        this.admin_phonenumber = admin_phonenumber;
        this.name = name;
        this.groupMembers = groupMembers;
    }


    public String getAdmin_phonenumber() {
        return admin_phonenumber;
    }

    public void setAdmin_phonenumber(String admin_phonenumber) {
        this.admin_phonenumber = admin_phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<String> groupMembers) {
        this.groupMembers = groupMembers;
    }
}
