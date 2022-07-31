package com.spicode.movieflix.vistas.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.installations.Utils;
import com.spicode.movieflix.R;


public class PlayerActivity extends AppCompatActivity {



    private String VIDEO_URI;
    private String VIDEO_TITLE;
    private TextView MovieName;
    private Button boton;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);


        VIDEO_URI = getIntent().getStringExtra("vid");
        VIDEO_TITLE = getIntent().getStringExtra("title");

        MovieName=(TextView) findViewById(R.id.NombrePelicula);

        MovieName.setText(VIDEO_TITLE);
        boton = (Button) findViewById(R.id.button);

        Uri uri = Uri.parse(VIDEO_URI);

        IniciarVLC();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlayerActivity.this, "Reitentando", Toast.LENGTH_SHORT).show();

                IniciarVLC();

            }
        });
      

    }


    public void   IniciarVLC(){
        boolean paqueteInstalado= isAppInstalled(PlayerActivity.this,"org.videolan.vlc");
        Toast.makeText(this, ""+paqueteInstalado, Toast.LENGTH_SHORT).show();

        if (paqueteInstalado==true){
            Intent vlcIntent = new Intent(Intent.ACTION_VIEW);
            vlcIntent.setPackage("org.videolan.vlc");
            vlcIntent.setDataAndTypeAndNormalize(Uri.parse(VIDEO_URI), "video/*");
            vlcIntent.putExtra("title", VIDEO_TITLE);
            vlcIntent.putExtra("from_start", true);
            startActivity(vlcIntent);
        }else if (paqueteInstalado==false){
            Toast.makeText(this, "Error: No Poses VLC Media Player se abrirá en La playstore", Toast.LENGTH_SHORT).show();
            try {
                Intent appStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "org.videolan.vlc"));
                appStoreIntent.setPackage("com.android.vending");

                startActivity(appStoreIntent);
            } catch (android.content.ActivityNotFoundException exception) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "org.videolan.vlc")));
            }
        }
    }
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}





