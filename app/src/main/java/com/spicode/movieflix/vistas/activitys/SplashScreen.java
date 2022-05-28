package com.spicode.movieflix.vistas.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.spicode.movieflix.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();
                }catch (Exception e){
                    Toast.makeText(SplashScreen.this, e +" error", Toast.LENGTH_SHORT).show();

                }

            }
        },3000);
    }
}