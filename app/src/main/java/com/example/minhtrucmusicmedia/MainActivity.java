package com.example.minhtrucmusicmedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SongAdapter listSongAdapter;
    private ArrayList<Song> arrayList;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle);

        arrayList = new ArrayList<>();
        arrayList.add(new Song("Cô gái m52", R.raw.supun1m52, R.drawable.m52));
        arrayList.add(new Song("Cô gái vàng", R.raw.cogaivang, R.drawable.cogaivang));
        arrayList.add(new Song("Anh thanh niên", R.raw.anhthanhnien, R.drawable.anhthanhnien));
        arrayList.add(new Song("Chẳng thể tìm được em", R.raw.changthetimduocem, R.drawable.changthetimduocem));
        arrayList.add(new Song("Đánh mất em", R.raw.danhmatem, R.drawable.danhmatem));

        listSongAdapter = new SongAdapter(arrayList,this);

        recyclerView.setAdapter(listSongAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}