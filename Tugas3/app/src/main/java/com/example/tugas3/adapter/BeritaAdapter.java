package com.example.berita_restapi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.tugas3.R;
import com.example.tugas3.model.Berita;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {
    Context context;
    ArrayList<Berita> beritaArr;

    public BeritaAdapter(Context context, ArrayList<Berita> newsArr) {
        this.context = context;
        this.beritaArr = newsArr;
    }

    @NonNull
    @Override
    public BeritaAdapter.BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_berita, parent, false);
        return new BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaAdapter.BeritaViewHolder holder, int position) {
        System.out.println("Title onBindViewHolder: " + beritaArr.get(position).getTitle());
        holder.titleTV.setText(beritaArr.get(position).getTitle());
        holder.categoryTV.setText(beritaArr.get(position).getCategory());
        Picasso.get().load(beritaArr.get(position).getUrl()).into(holder.imgIV);
    }

    @Override
    public int getItemCount() {
        return beritaArr.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV;
        TextView categoryTV;
        ImageView imgIV;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTextView);
            categoryTV = itemView.findViewById(R.id.categoryTextView);
            imgIV = itemView.findViewById(R.id.imageImageView);
        }
    }
}
