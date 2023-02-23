package com.example.progettoletturadati;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progettoletturadati.APIDir.Singleton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ActivityInfoList extends AppCompatActivity {

    private TextView txtLabelID, txtItemID, txtPrezzo, txtCantina, txtAnnata, txtNomeProdotto;
    private TextView txtStatoBatteria, txtConnectivityStatus;
    private ImageView imgEtichetta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);

        txtLabelID = findViewById(R.id.txtLabelID);
        txtItemID = findViewById(R.id.txtItemID);
        txtPrezzo = findViewById(R.id.txtPrezzo);
        txtCantina = findViewById(R.id.txtCantina);
        txtAnnata = findViewById(R.id.txtAnnata);
        txtNomeProdotto = findViewById(R.id.txtNomeProdotto);

        txtStatoBatteria = findViewById(R.id.txtStatoBatteria);
        txtConnectivityStatus = findViewById(R.id.txtConnectivityStatus);

        imgEtichetta = findViewById(R.id.imgEtichetta);

        new HttpGetTask().execute();
    }

    private class HttpGetTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            String dato=Singleton.getInstance().getDatoInfo();

            System.out.println(dato);

            Request request1 = new Request.Builder()
                    .url("https://api-eu.vusion.io/vcloud/v1/stores/retex_it.vlab/labels/"+dato)
                    .get()
                    .addHeader("Ocp-Apim-Subscription-Key", "d16a4c91d510483994904456946e8579")
                    .build();

            Request request2 = new Request.Builder()
                    .url("https://api-eu.vusion.io/vcloud/v1/stores/retex_it.vlab/labels/"+dato+"/pages?expected=False")
                    .get()
                    .addHeader("Ocp-Apim-Subscription-Key", "d16a4c91d510483994904456946e8579")
                    .build();

            try {
                Response response1 = client.newCall(request1).execute();
                Response response2 = client.newCall(request2).execute();
                if (!response1.isSuccessful()) {
                    Log.e("HTTP GET Request", "Request 1 failed with code: " + response1.code());
                    return null;
                }
                if (!response2.isSuccessful()) {
                    Log.e("HTTP GET Request", "Request 2 failed with code: " + response2.code());
                    return null;
                }

                String jsonString1 = response1.body().string();
                String jsonString2 = response2.body().string();
                JSONObject json1 = new JSONObject(jsonString1);
                String labelID = json1.getString("labelId");
                JSONObject matching = json1.getJSONObject("matching");
                JSONObject items = matching.getJSONArray("items").getJSONObject(0);
                String itemID = items.getString("itemId");
                String nomeProdotto = items.getString("name");

                String prezzoVendita;
                JSONObject custom = items.optJSONObject("custom");
                if (custom != null && custom.has("prezzo_vendita")) {
                    prezzoVendita = custom.getString("prezzo_vendita");
                } else {
                    prezzoVendita = items.optString("price", "0");
                }


                String annata = items.getJSONObject("custom").optString("annata", "null");


                String cantina = items.getJSONObject("custom").optString("cantina","null");

                JSONObject connectivity = json1.getJSONObject("connectivity");
                String status = connectivity.getString("status");

                JSONObject hardware = json1.getJSONObject("hardware");
                ;
                String battery = hardware.getString("battery");

                JSONObject json2 = new JSONObject(jsonString2);
                JSONObject pages = json2.getJSONArray("pages").getJSONObject(0);
                String imageData = pages.getString("image");

                return new String[]{nomeProdotto, prezzoVendita, annata, cantina,imageData, status, battery,labelID,itemID};

            } catch (IOException | JSONException e) {
                Log.e("HTTP GET Request", "Request failed: " + e.getMessage());
                return null;
            }
        }

        protected void onPostExecute(String[] result) {
            super.onPostExecute(result);

            if (result == null) {
                Toast.makeText(getApplicationContext(), "Error retrieving data", Toast.LENGTH_SHORT).show();
                return;
            }

            txtLabelID.setText("Label ID: "+result[7]);
            txtItemID.setText("Item ID: "+result[8]);
            txtNomeProdotto.setText("Nome prodotto: "+result[0]);
            txtPrezzo.setText("Prezzo :"+result[1]+"€");
            txtAnnata.setText("Annata :"+result[2]);
            txtCantina.setText("Cantina :"+result[3]);
            txtConnectivityStatus.setText("Connectivity Status: "+result[5]);
            txtStatoBatteria.setText("Stato batteria: "+result[6]);

            byte[] decodedString = Base64.decode(result[4], Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imgEtichetta.setImageBitmap(decodedByte);
        }
    }
}

