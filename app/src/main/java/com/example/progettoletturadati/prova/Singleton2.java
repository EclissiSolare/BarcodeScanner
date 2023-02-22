package com.example.progettoletturadati.prova;

public class Singleton2 {
    private static Singleton2 instance;
    private String string;

    private String JSON;

    private Singleton2() {}

    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
    public String getJSON() {
        return JSON;
    }

    public void setJSON(String JSON) {
        this.JSON = JSON;
    }
}