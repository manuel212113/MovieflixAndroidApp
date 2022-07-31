package com.spicode.movieflix.adaptador;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class MovieAdapterRecycleView extends RecyclerView.Adapter<MovieAdapterRecycleView.MyViewHolder> {

     private List<UltimasPeliculas> mData;

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
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {

        viewHolder.TvTitle.setText(mData.get(i).getPTitulo());
        Glide.with(viewHolder.itemView).load(mData.get(i).getPImagen()).into(viewHolder.imgMovie);
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
