package com.example.minhtrucmusicmedia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private ArrayList<Song> arrayList;
    private Context context;

    public SongAdapter(ArrayList<Song> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item,parent,false);
        return new SongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song item = arrayList.get(position);
        //    holder.txtPrice.setText(String.valueOf(item.getPrice()));
        holder.txtName.setText(String.valueOf(item.getTenBaiHat()));
        holder.imageView.setImageResource(item.getImageSong());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,ControlActivity.class);
                intent.putExtra("name",item.getTenBaiHat());
                intent.putExtra("img",item.getImageSong());
                intent.putExtra("mp3",item.getMp3());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtName;
        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.img_item_song);
            this.txtName = itemView.findViewById(R.id.tv_item_name);
        }
    }
}
