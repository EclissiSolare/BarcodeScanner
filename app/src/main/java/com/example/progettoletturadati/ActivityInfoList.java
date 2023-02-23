package com.example.progettoletturadati;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class ActivityInfoList extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);

        TextView txtLabelID=findViewById(R.id.txtLabelID);
        TextView txtItemID=findViewById(R.id.txtItemID);
        TextView txtPrezzo=findViewById(R.id.txtPrezzo);
        TextView txtCantina=findViewById(R.id.txtCantina);
        TextView txtAnnata=findViewById(R.id.txtAnnata);
        TextView txtNomeProdotto=findViewById(R.id.txtNomeProdotto);

        TextView txtStatoBatteria=findViewById(R.id.txtStatoBatteria);
        TextView txtConnectivityStatus=findViewById(R.id.txtConnectivityStatus);


        ImageView imgEtichetta=findViewById(R.id.imgEtichetta);






    }
}