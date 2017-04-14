package com.chillasso.chillasso.Class;

import java.util.List;

/**
 * Created by Enric on 01/04/2017.
 */

public class Contact {

    private List<String> groupsId;
    private String name;
    private String phone;
    private boolean selected;

    public Contact(String name, String phone,boolean selected) {
        this.name = name;
        this.phone = phone;
        this.selected = selected;
    }

    public Contact(boolean selected, List<String> groupsId, String name, String phone) {
        this.selected = selected;
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
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
