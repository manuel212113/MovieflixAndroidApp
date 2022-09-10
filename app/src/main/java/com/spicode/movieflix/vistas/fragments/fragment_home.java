package com.spicode.movieflix.vistas.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.spicode.movieflix.R;
import com.spicode.movieflix.adaptador.MovieAdapterRecycleView;
import com.spicode.movieflix.adaptador.SliderAdapter;
import com.spicode.movieflix.modelo.ModeloDatosPelicula;
import com.spicode.movieflix.modelo.UltimasPeliculas;

import java.util.List;

public class fragment_home  extends Fragment {
}