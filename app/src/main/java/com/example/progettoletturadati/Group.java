package com.example.progettoletturadati;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupName;
    private Object groupValue;
    private List<Child> children;

    public Group() {
        this.groupName = null;
        this.groupValue = null;
        this.children = new ArrayList<Child>();
    }

    public Group(String groupName, Object groupValue) {
        this.groupName = groupName;
        this.groupValue = groupValue;
        this.children = new ArrayList<Child>();
    }

    public void addChild(Child child) {
        children.add(child);
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupValue(Object groupValue) {
        this.groupValue = groupValue;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getGroupName() {
        return groupName;
    }

    public Object getGroupValue() {
        return groupValue;
    }

    public List<Child> getChildren() {
        return children;
    }
}
