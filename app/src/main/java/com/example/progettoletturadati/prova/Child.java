package com.example.progettoletturadati.prova;

public class Child {
    private String childName;
    private String childValue;


    public Child(){
        this.childName = null;
        this.childValue = null;
    }

    public Child(String childName, String childValue) {
        this.childName = childName;
        this.childValue = childValue;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public void setChildValue(String childValue) {
        this.childValue = childValue;
    }

    public String getChildName() {
        return childName;
    }

    public String getChildValue() {
        return childValue;
    }
}
