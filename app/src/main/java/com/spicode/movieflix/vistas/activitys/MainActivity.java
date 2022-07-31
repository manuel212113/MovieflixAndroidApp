package com.spicode.movieflix.vistas.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.spicode.movieflix.adaptador.MovieAdapterRecycleView;
import com.spicode.movieflix.modelo.UltimasPeliculas;
import com.spicode.movieflix.vistas.fragments.CustomLoading;
import com.spicode.movieflix.R;
import com.spicode.movieflix.adaptador.SliderAdapter;
import com.spicode.movieflix.modelo.ModeloDatosPelicula;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
    RequestQueue queue,queue2;
    private List<UltimasPeliculas> UltimasPeliculasList;

    private List<ModeloDatosPelicula> peliculaList;
    private SliderAdapter sliderAdapter;
    private MovieAdapterRecycleView UltimasPeliculasAdapter;
    private FirebaseAuth mAuth;
    private RecyclerView UltimasPeliculasRV;

    String jsonURL = "https://pastebin.com/raw/8dmTqusb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseApp.initializeApp(this);

        SliderView sliderView = findViewById(R.id.imageSlider);
        UltimasPeliculasRV= findViewById(R.id.Rv_UltimasPeliculas);
        sliderAdapter = new SliderAdapter(this);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        renovarItems(sliderView);

        queue = Volley.newRequestQueue(this);
        queue2 = Volley.newRequestQueue(this);

        CargarDesdeJson();
        mAuth = FirebaseAuth.getInstance();
        CargarUltimasPeliculasDesdeJson();



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }else{

        }
    }




    private  void CargarDesdeJson(){
        CustomLoading dialog = new CustomLoading(MainActivity.this);
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.GET, jsonURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("Peliculas");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object1 = array.getJSONObject(i);
                        String TituloPeli = object1.getString("PTitulo");
                        String ImagenPeli = object1.getString("PImagen");
                        String LinkPeli = object1.getString("PVideo");
                        peliculaList.add(new ModeloDatosPelicula(TituloPeli, ImagenPeli, LinkPeli));
                    }
                    sliderAdapter.notifyDataSetChanged();
                    dialog.hide();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
        queue.add(request);

    }

    private  void CargarUltimasPeliculasDesdeJson(){
        CustomLoading dialog = new CustomLoading(MainActivity.this);
        dialog.show();
        UltimasPeliculasList = new ArrayList<>();


        StringRequest request = new StringRequest(Request.Method.GET, jsonURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("UltimasPeliculas");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object1 = array.getJSONObject(i);
                        String TituloPeli = object1.getString("PUPTitulo");
                        String ImagenPeli = object1.getString("PUPImagen");
                        UltimasPeliculasList.add(new UltimasPeliculas(TituloPeli, ImagenPeli));
                    }

                    UltimasPeliculasRV = findViewById(R.id.Rv_UltimasPeliculas);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                    layoutManager.setReverseLayout(true);
                    layoutManager.setStackFromEnd(true);
                    UltimasPeliculasRV.setLayoutManager(layoutManager);


                    UltimasPeliculasAdapter = new MovieAdapterRecycleView(UltimasPeliculasList);
                    UltimasPeliculasRV.setAdapter(UltimasPeliculasAdapter);
                    UltimasPeliculasAdapter.notifyDataSetChanged();

                    dialog.hide();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
        queue2.add(request);

    }




    private void CargarBannerFirebase() {
        CustomLoading dialog = new CustomLoading(MainActivity.this);
        dialog.show();
        reference.child("Peliculas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contenidoSlider : snapshot.getChildren()) {
                    ModeloDatosPelicula sliderItem = contenidoSlider.getValue(ModeloDatosPelicula.class);
                    peliculaList.add(sliderItem);
                }
                sliderAdapter.notifyDataSetChanged();
                dialog.hide();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, " " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void renovarItems(View view) {
        peliculaList = new ArrayList<>();
        ModeloDatosPelicula dataItems = new ModeloDatosPelicula();
        peliculaList.add(dataItems);
        sliderAdapter.renovarItems(peliculaList);
        sliderAdapter.EliminarItems(0);


    }
}

