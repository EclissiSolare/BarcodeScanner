package com.example.progettoletturadati;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.ean:
                        finish();
                        startActivity(new Intent(getApplicationContext(),ActivityEAN.class));
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.labels:
                        finish();
                        startActivity(new Intent(getApplicationContext(),ActivityLabels.class));
                        return true;
                    case R.id.info:
                        finish();
                        startActivity(new Intent(getApplicationContext(),ActivityInfo.class));
                        return true;
                    case R.id.insert:
                        finish();
                        startActivity(new Intent(getApplicationContext(),ActivityInsert.class));
                        return true;
                }
                return false;
            }
        });



        // Imposta la ActionBar personalizzata
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.turchese)));

        String text =
                "Barcode Scanner infos\n\n" +

                "Barcode Scanner is an app that uses DataWedge API to scan EAN and Label codes using Zebra integrated scanner.\n\n" +

                "To navigate inside the app you can use the bottom navigation bar.\n\n" +
                "(home) = this activity, (EAN) EAN Scanner Activity, (Label) Label Scanner Activity, (Info) Info Viewer Activity, (Insert) Matching Activity"+

                "@2023 Belletti & D’Esposito\n" +
                "Corporation and/or its affiliates.\n";
        SpannableString spannableString = new SpannableString(text);

// Aggiungi lo span di colore rosso alla prima parola
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLUE);
        spannableString.setSpan(colorSpan, 0, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

// Aggiungi lo span di dimensione del testo alla prima parola
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(2f);
        spannableString.setSpan(sizeSpan, 0, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        RelativeSizeSpan sizeSpan2 = new RelativeSizeSpan(1.25f);
        spannableString.setSpan(sizeSpan2, 22, 361, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(boldSpan, 0, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = findViewById(R.id.textView);
        textView.setText(spannableString);
    }



    public void onClickEAN(View view) {
       Intent intent=new Intent(this,ActivityEAN.class);
       startActivity(intent);
    }

    public void onClickLabels(View view){
        Intent intent=new Intent(this,ActivityLabels.class);
        startActivity(intent);
    }


}