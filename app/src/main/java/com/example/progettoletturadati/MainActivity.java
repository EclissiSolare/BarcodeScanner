package com.example.progettoletturadati;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    float x1, x2, y1, y2;

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

        TextView textView = findViewById(R.id.textView);
        // Creare un drawable dal file vettoriale XML
        Drawable homeDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.home, null);
        homeDrawable.setBounds(0, 0, homeDrawable.getIntrinsicWidth(), homeDrawable.getIntrinsicHeight());

        Drawable EANDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ean, null);
        EANDrawable.setBounds(0, 0, EANDrawable.getIntrinsicWidth(), EANDrawable.getIntrinsicHeight());

        Drawable InfoDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.info, null);
        InfoDrawable.setBounds(0, 0, InfoDrawable.getIntrinsicWidth(), InfoDrawable.getIntrinsicHeight());

        Drawable LabelDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.label, null);
        LabelDrawable.setBounds(0, 0, LabelDrawable.getIntrinsicWidth(), LabelDrawable.getIntrinsicHeight());

        Drawable MatchingDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.insert, null);
        MatchingDrawable.setBounds(0, 0, MatchingDrawable.getIntrinsicWidth(), MatchingDrawable.getIntrinsicHeight());


        // Calcolare la larghezza dell'immagine in pixel
        int imageSize = (int) (textView.getLineHeight() * 1.5f);

        // Ridimensionare il drawable per adattarlo alla larghezza desiderata
        Drawable scaledHomeDrawable = homeDrawable.getConstantState().newDrawable().mutate();
        scaledHomeDrawable.setBounds(0, 0, imageSize, imageSize);

        Drawable scaledEANDrawable = EANDrawable.getConstantState().newDrawable().mutate();
        scaledEANDrawable.setBounds(0, 0, imageSize, imageSize);

        Drawable scaledINFODrawable = InfoDrawable.getConstantState().newDrawable().mutate();
        scaledINFODrawable.setBounds(0, 0, imageSize, imageSize);

        Drawable scaledLabelsDrawable = LabelDrawable.getConstantState().newDrawable().mutate();
        scaledLabelsDrawable.setBounds(0, 0, imageSize, imageSize);

        Drawable scaledMatchingDrawable = MatchingDrawable.getConstantState().newDrawable().mutate();
        scaledMatchingDrawable.setBounds(0, 0, imageSize, imageSize);

        // Creare un oggetto ImageSpan con l'immagine e l'allineamento desiderato
        ImageSpan imageSpan = new ImageSpan(scaledHomeDrawable, ImageSpan.ALIGN_BOTTOM);
        ImageSpan imageSpanEan = new ImageSpan(scaledEANDrawable, ImageSpan.ALIGN_BOTTOM);
        ImageSpan imageSpanLabels = new ImageSpan(scaledLabelsDrawable, ImageSpan.ALIGN_BOTTOM);
        ImageSpan imageSpanInfo = new ImageSpan(scaledINFODrawable, ImageSpan.ALIGN_BOTTOM);
        ImageSpan imageSpanMatching = new ImageSpan(scaledMatchingDrawable, ImageSpan.ALIGN_BOTTOM);


        String text = "Barcode Scanner infos\n\n" +

                "Barcode Scanner is an app that uses DataWedge API to scan EAN and Label codes using Zebra integrated scanner.\n\n" +

                "To navigate inside the app you can use the bottom navigation bar.\n\n" +

                "●HomeHome                     ●EAN ScannerEAN Scanner\n" +
                "●Label ScannerLabel Scanner      ●Info ViewerInfo Viewer\n" +
                "      ●MatchingMatching EAN to a Label\n\n"+

                "@2023 Belletti & D’Esposito\n";
                SpannableString spannableString = new SpannableString(text);

        // Aggiungi lo span di colore rosso alla prima parola
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLUE);
        spannableString.setSpan(colorSpan, 0, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Aggiungo lo span di dimensione del testo alla prima parola
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(2f);
        spannableString.setSpan(sizeSpan, 0, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        RelativeSizeSpan sizeSpan2 = new RelativeSizeSpan(1.25f);
        spannableString.setSpan(sizeSpan2, 22, 370, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(boldSpan, 0, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Aggiungi l'immagine al testo accanto a "●Home"
        int homeStart = text.indexOf("●Home");
        int homeEnd = homeStart + "●Home".length();

        int LabelsStart = text.indexOf("●Label Scanner");
        int LabelsEnd = LabelsStart + "●Label Scanner".length();

        int InfoStart = text.indexOf("●Info Viewer");
        int InfoEnd = InfoStart + "●Info Viewer".length();

        int EanStart = text.indexOf("●EAN Scanner");
        int EanEnd = EanStart + "●EAN Scanner".length();

        int MatchingStart = text.indexOf("●Matching");
        int MatchingEnd = MatchingStart + "●Matching".length();


        spannableString.setSpan(imageSpan, homeStart, homeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(imageSpanEan, EanStart, EanEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(imageSpanLabels, LabelsStart, LabelsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(imageSpanInfo, InfoStart, InfoEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(imageSpanMatching, MatchingStart, MatchingEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


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