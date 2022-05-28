package com.spicode.movieflix.vistas.activitys;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.spicode.movieflix.R;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;


public class PlayerActivity extends AppCompatActivity {


    private  String VIDEO_URI;
    private  String VIDEO_TITLE;
    private  String Api_Drive="AIzaSyBNAEikbhPbtJPjR22YBfdXklO_RmZ6ZZQ";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        VIDEO_URI = getIntent().getStringExtra("vid");
        VIDEO_TITLE = getIntent().getStringExtra("title");





        try {
            JzvdStd jzvdStd = (JzvdStd) findViewById(R.id.jz_video);
            jzvdStd.loadingProgressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));

            jzvdStd.setUp(VIDEO_URI
                    , VIDEO_TITLE, 0);
            jzvdStd.setScreenFullscreen();
            jzvdStd.startButton.setVisibility(View.VISIBLE);
            jzvdStd.backButton.setVisibility(View.GONE);
            jzvdStd.widthRatio = 16;
            jzvdStd.heightRatio = 9;
            jzvdStd.batteryLevel.setVisibility(View.VISIBLE);
            jzvdStd.bottomProgressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
            jzvdStd.batteryTimeLayout.setVisibility(View.VISIBLE);
            jzvdStd.titleTextView.setVisibility(View.VISIBLE);
            jzvdStd.fullscreenButton.setVisibility(View.GONE);


        } catch (ExceptionInInitializerError e) {
            Toast.makeText(PlayerActivity.this, "erro:" + e, Toast.LENGTH_LONG).show();
            Toast.makeText(PlayerActivity.this, "erro:" + e, Toast.LENGTH_LONG).show();
            finish();


        }


    }






    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            super.onBackPressed();
            finish();
            return;
        }
        super.onBackPressed();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
}