package com.example.progettoletturadati;


import android.content.Intent;
import android.os.AsyncTask;


import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;

import java.io.Serializable;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

    private Exception exception;
    private MainActivity mainActivity;
    private Response response;
    private String codiceBarre;
    private Item item;
    private String responseBody;

    public RetrieveFeedTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public RetrieveFeedTask(MainActivity mainActivity, String codiceBarre) {
        this.mainActivity = mainActivity;
        this.codiceBarre = codiceBarre;
    }

    protected void onPostExecute(String result) {
        Intent intent = new Intent(mainActivity, ActivityJSONLIST.class);
        intent.putExtra("json", (Serializable) item);
        mainActivity.startActivity(intent);
    }



    protected String doInBackground(Void... urls) {
        String dati=Singleton.getInstance().getString();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-eu.vusion.io/vcloud/v1/stores/retex_it.vlab/items/"+dati)
                .get()
                .addHeader("Ocp-Apim-Subscription-Key", "d16a4c91d510483994904456946e8579")
                .build();

        try {
            Response response = client.newCall(request).execute();
            responseBody = response.body().string();
            System.out.println(responseBody);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            item = gson.fromJson(responseBody, Item.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    class Item implements Serializable{
        String itemId;
        String modificationDate;
        Object deletionDate;
        Custom custom;
        String name;
        String id;
        String storeId;
        String creationDate;
        Object extended;
        Matching matching;
        String status;
        float _score;
    }

    class Custom implements Serializable{
        String promo_flag;
        String prezzo_vendita;
        String annata;
        String cantina;
        String prezzo_um;
        String prezzo_intero;
    }

    class Matching implements Serializable{
        int count;
        boolean matched;
        List<String> labels;
    }


}