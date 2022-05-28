package com.spicode.movieflix.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.spicode.movieflix.R;
import com.spicode.movieflix.modelo.ModeloDatosPelicula;
import com.spicode.movieflix.vistas.activitys.PlayerActivity;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.MyViewHolder> {

     private Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    private List<ModeloDatosPelicula> datosPeliculaList = new ArrayList<>();

    public void renovarItems(List<ModeloDatosPelicula> datosPeliculaList){
        this.datosPeliculaList=datosPeliculaList;
        notifyDataSetChanged();
    }

    public void EliminarItems(int posicion){
        this.datosPeliculaList.remove(posicion);
        notifyDataSetChanged();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout,
                parent , false );

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {

        viewHolder.titulo_pelicula.setText(datosPeliculaList.get(position).getPTitulo());

        Glide.with(viewHolder.itemView).load(datosPeliculaList.get(position).getPImagen()).into(viewHolder.slider_imagen);
        viewHolder.btnBannerPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Pelicula_video  = new Intent(context, PlayerActivity.class);
                Pelicula_video.putExtra("vid", datosPeliculaList.get(position).getPVideo());
                Pelicula_video.putExtra("title", datosPeliculaList.get(position).getPTitulo());
                view.getContext().startActivity(Pelicula_video);

            }
        });
    }

    @Override
    public int getCount() {
        return datosPeliculaList.size();
    }

    public class MyViewHolder  extends  SliderViewAdapter.ViewHolder{

        ImageView slider_imagen;
        TextView titulo_pelicula;
        FloatingActionButton btnBannerPlay;

        public MyViewHolder(View itemView) {

            super(itemView);

            slider_imagen=itemView.findViewById(R.id.imagen_peli);
            titulo_pelicula=itemView.findViewById(R.id.titulo_pelicula);
            btnBannerPlay= itemView.findViewById(R.id.playBanner);

        }
    }
}
