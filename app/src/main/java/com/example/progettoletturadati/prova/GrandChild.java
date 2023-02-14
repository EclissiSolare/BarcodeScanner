package com.example.progettoletturadati.prova;

public class GrandChild {

    private String grandChildName;

    private String grandChildValue;


    public GrandChild(){
        this.grandChildName=null;
        this.grandChildValue=null;
    }


    public GrandChild(String grandChildName, String grandChildValue) {
        this.grandChildName = grandChildName;
        this.grandChildValue = grandChildValue;
    }

    public String getGrandChildValue() {
        return grandChildValue;
    }

    public void setGrandChildValue(String grandChildValue) {
        this.grandChildValue = grandChildValue;
    }

    public String getGrandChildName() {
        return grandChildName;
    }

    public void setGrandChildName(String grandChildName) {
        this.grandChildName = grandChildName;
    }
}
