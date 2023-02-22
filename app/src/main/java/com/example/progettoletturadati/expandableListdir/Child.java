package com.example.progettoletturadati.expandableListdir;

import java.util.ArrayList;
import java.util.List;

public class Child {
    private String childName;
    private String childValue;

    private List<GrandChild> grandChildren;


    public Child() {
        this.childName = null;
        this.childValue = null;
        this.grandChildren = new ArrayList<GrandChild>();
    }

    public Child(String childName, String childValue, List<GrandChild> grandChildren) {
        this.childName = childName;
        this.childValue = childValue;
        this.grandChildren = grandChildren;
    }

    public void addGrandChild(GrandChild grandchild) {
        grandChildren.add(grandchild);
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildValue() {
        return childValue;
    }

    public void setChildValue(String childValue) {
        this.childValue = childValue;
    }

    public List<GrandChild> getGrandChildren() {
        return grandChildren;
    }

    public void setGrandChildren(List<GrandChild> grandChildren) {
        this.grandChildren = grandChildren;
    }
}
