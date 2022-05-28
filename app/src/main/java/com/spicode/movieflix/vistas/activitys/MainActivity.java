package com.spicode.movieflix.vistas.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.spicode.movieflix.vistas.fragments.CustomLoading;
import com.spicode.movieflix.R;
import com.spicode.movieflix.adaptador.SliderAdapter;
import com.spicode.movieflix.modelo.ModeloDatosPelicula;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase  database =FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
    private List<ModeloDatosPelicula> peliculaList;
    private SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FirebaseApp.initializeApp(this);

        SliderView sliderView=findViewById(R.id.imageSlider);
        sliderAdapter= new SliderAdapter(this);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setScrollTimeInSec(6);
        renovarItems(sliderView);
        CargarBannerFirebase();

    }

    private void CargarBannerFirebase() {
        CustomLoading dialog= new CustomLoading(MainActivity.this);
        dialog.show();
        reference.child("Peliculas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot  contenidoSlider: snapshot.getChildren()){
                    ModeloDatosPelicula sliderItem=contenidoSlider.getValue(ModeloDatosPelicula.class);
                    peliculaList.add(sliderItem);
                }
                sliderAdapter.notifyDataSetChanged();
                dialog.hide();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, " "+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void renovarItems(View view) {
        peliculaList=new ArrayList<>();
        ModeloDatosPelicula dataItems= new ModeloDatosPelicula();
        peliculaList.add(dataItems);
        sliderAdapter.renovarItems(peliculaList);
        sliderAdapter.EliminarItems(0);


    }
}