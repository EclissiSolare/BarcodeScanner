package com.example.progettoletturadati.APIDir;

public class Singleton {
    private static Singleton instance;
    private String datoLabels;

    private String datoEAN;

    private String JSONLabels;

    private String JSONEAN;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public String getDatoLabels() {
        return datoLabels;
    }

    public void setDatoLabels(String datoLabels) {
        this.datoLabels = datoLabels;
    }

    public String getDatoEAN() {
        return datoEAN;
    }

    public void setDatoEAN(String datoEAN) {
        this.datoEAN = datoEAN;
    }

    public String getJSONLabels() {
        return JSONLabels;
    }

    public void setJSONLabels(String JSONLabels) {
        this.JSONLabels = JSONLabels;
    }

    public String getJSONEAN() {
        return JSONEAN;
    }

    public void setJSONEAN(String JSONEAN) {
        this.JSONEAN = JSONEAN;
    }
}

