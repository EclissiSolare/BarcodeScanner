package com.example.progettoletturadati;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
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
                        startActivity(new Intent(getApplicationContext(),ActivityEAN.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.labels:
                        startActivity(new Intent(getApplicationContext(),ActivityLabels.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // Imposta la ActionBar personalizzata
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.turchese)));

        String text = "Ciao, come s\ntai?";
        SpannableString spannableString = new SpannableString(text);

// Aggiungi lo span di colore rosso alla prima parola
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(colorSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

// Aggiungi lo span di dimensione del testo alla prima parola
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(1.5f);
        spannableString.setSpan(sizeSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = findViewById(R.id.textView);
        textView.setText(spannableString);
    }

    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                if (x1+200 < x2) {
                    Intent i = new Intent(MainActivity.this, ActivityEAN.class);
                    startActivity(i);
                } else if (x1 > x2+200) {
                    Intent i = new Intent(MainActivity.this, ActivityLabels.class);
                    startActivity(i);
                }
                break;
        }
        return false;
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