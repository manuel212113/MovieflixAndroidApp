package com.spicode.movieflix.vistas.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spicode.movieflix.R;

public class MovieDetail extends AppCompatActivity {


    private String tituloPeli,LinkPeli;
    private String ImgBanner;
    private String DescripcionPeli;
    private String logoPeli;
    private ImageView banner,logo;
    private TextView txtSinopsis;
    private Button btnReproductor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        LinkPeli= getIntent().getStringExtra("linkPeli");
        tituloPeli = getIntent().getStringExtra("titulo");
        ImgBanner = getIntent().getStringExtra("ImgBanner");
        logoPeli=getIntent().getStringExtra("logo");
        DescripcionPeli = getIntent().getStringExtra("descripcion");

        banner= findViewById(R.id.bannerimg);
        logo=findViewById(R.id.imglogo);
        txtSinopsis= findViewById(R.id.txtdesc);
        btnReproductor= findViewById(R.id.btnReproducir);

        Glide.with(this).load(ImgBanner).into(banner);
        Glide.with(this).load(logoPeli).into(logo);
        txtSinopsis.setText(DescripcionPeli);

        btnReproductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PlayMovie  = new Intent(MovieDetail.this, PlayerActivity.class);
                PlayMovie.putExtra("vid", LinkPeli);
                PlayMovie.putExtra("title", tituloPeli);
                MovieDetail.this.startActivity(PlayMovie);

            }
        });



    }
}