package com.example.progettoletturadati.APIDir;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.progettoletturadati.ActivityJSONLIST;
import com.example.progettoletturadati.ActivityLabels;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RetrieveFeedTask2 extends AsyncTask<Void, Void, String> {

    private Exception exception;
    private ActivityLabels activityLabels;
    private Response response;
    private String codiceBarre;
    private Item labels;
    public static String responseBody;

    public RetrieveFeedTask2(ActivityLabels activitylabels) {
        this.activityLabels = activitylabels;
    }

    public RetrieveFeedTask2(ActivityLabels activitylabels, String codiceBarre) {
        this.activityLabels = activitylabels;
        this.codiceBarre = codiceBarre;
    }

    protected void onPostExecute(String result) {
        Intent intent = new Intent(activityLabels, ActivityJSONLIST.class);
        intent.putExtra("json", (Serializable) labels);
        activityLabels.startActivity(intent);
    }



    protected String doInBackground(Void... urls) {
        String dati=Singleton2.getInstance().getDato();
        System.out.println(dati);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-eu.vusion.io/vcloud/v1/stores/retex_it.vlab/labels/"+dati)
                .get()
                .addHeader("Ocp-Apim-Subscription-Key", "d16a4c91d510483994904456946e8579")
                .build();

        try {
            Response response = client.newCall(request).execute();
            responseBody = response.body().string();

            Singleton2.getInstance().setJSON(responseBody);


            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            labels = gson.fromJson(responseBody, Item.class);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    class Item implements Serializable{
        String itemId;
        String modificationDate;
        Object deletionDate;
        RetrieveFeedTask.Custom custom;
        String name;
        String id;
        String storeId;
        String creationDate;
        Object extended;
        RetrieveFeedTask.Matching matching;
        String status;
        float _score;
    }
    class Labels implements Serializable{
         String storeId;
         Date creationDate;
         String securityStatus;
         double score;
         String transmitterId;
         Matching matching;
         Date modificationDate;
         Transmission transmission;
         String labelId;
         Connectivity connectivity;
         Encryption encryption;
         Date deletionDate;
         String correlationId;
         int page;
         String currentPage;
         String status;
         Hardware hardware;
    }

    class Date implements Serializable{
        String creationDate;
        String matchingDate;
        String modificationDate;
        String lastSuccessfulFlashingDate;
        String lastSuccessfulTransmissionDate;
        String registrationDate;
        String transmissionDate;
        String flashingDate;
        String lastFailedTransmissionDate;
        String lastOfflineDate;
        String lastOnlineDate;
        String modificationDateConnectivity;
        String lastBatteryBadStatusDate;
        String firstBatteryBadDate;
        String deletionDate;


    }

    class Matching implements Serializable{
        Scenario scenario;
        Date matchingDate;
        List<Object> items;
        Object extended;
    }
    static class Scenario implements Serializable{
        String name;
        Object automaticScenarioId;
        Object automaticScenarioName;
        String scenarioId;
    }

    class Transmission implements Serializable{
        Date lastSuccessfulFlashingDate;
        Date lastSuccessfulTransmissionDate;
        Date registrationDate;
        Date transmissionDate;
        Date flashingDate;
        Date lastFailedTransmissionDate;
    }
    class Connectivity implements Serializable{
        int rssi;
        String modificationDate;
        int lqi;
        String signalQuality;
        String lastOfflineDate;
        String status;
        String lastOnlineDate;
    }
    class Encryption implements Serializable{
        String type;
        String status;

    }
    class Hardware implements Serializable{
        String naturalOrder;
        int pageCount;
        String imageName;
        String typeName;
        String pattern;
        String lastBatteryBadStatusDate;
        String battery;
        String defaultOrientation;
        String firstBatteryBadDate;
        String displayType;
        String screenTechnology;
        String transmissionTechnology;
        String screenColor;
        String width;
        String animated;
        String typeId;
        String dpi;
        String firmware;
        String height;
        String unitaryUpdateDuration;
    }



}