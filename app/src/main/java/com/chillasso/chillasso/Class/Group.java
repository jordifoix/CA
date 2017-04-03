package com.chillasso.chillasso.Class;

import java.util.List;

/**
 * Created by Enric on 03/04/2017.
 */

public class Group {

    private String name;
    private List<String> groupMembers;

    public Group(String name, List<String> groupMembers) {
        this.name = name;
        this.groupMembers = groupMembers;
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
