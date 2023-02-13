package com.example.progettoletturadati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        new Handler().postDelayed(new Runnable() {
        @Override
            public void run() {
            startActivity(new Intent(TitleActivity.this, MainActivity.class));
            finish();
            }
        }, 1500);
    }
}