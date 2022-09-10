package com.spicode.movieflix.adaptador;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spicode.movieflix.R;
import com.spicode.movieflix.modelo.UltimasPeliculas;
import com.spicode.movieflix.vistas.activitys.MainActivity;
import com.spicode.movieflix.vistas.activitys.MovieDetail;
import com.spicode.movieflix.vistas.activitys.PlayerActivity;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapterRecycleView extends RecyclerView.Adapter<MovieAdapterRecycleView.MyViewHolder> {

    private List<UltimasPeliculas> mData;
    private Context context;


    public MovieAdapterRecycleView( List<UltimasPeliculas> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_movie_layout,viewGroup,false);
        return  new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {

        viewHolder.TvTitle.setText(mData.get(i).getPTitulo());
        Glide.with(viewHolder.itemView).load(mData.get(i).getPImagen()).override(300, 200).into(viewHolder.imgMovie);
        viewHolder.imgMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Detail_Pelicula  = new Intent(viewHolder.itemView.getContext(), MovieDetail.class);
                Detail_Pelicula.putExtra("descripcion", mData.get(i).getPDescripcion());
                Detail_Pelicula.putExtra("titulo", mData.get(i).getPTitulo());
                Detail_Pelicula.putExtra("logo",mData.get(i).getPLogo());
                Detail_Pelicula.putExtra("linkPeli",mData.get(i).getPVideo());
                Detail_Pelicula.putExtra("ImgBanner",mData.get(i).getPImagenBanner());
                viewHolder.itemView.getContext().startActivity(Detail_Pelicula);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView TvTitle;
        private ImageView imgMovie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TvTitle = itemView.findViewById(R.id.txt_Card_title);
            imgMovie= itemView.findViewById(R.id.card_img_movie);

        }
    }
}
