package com.example.minhtrucmusicmedia;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minhtrucmusicmedia.MyService.MyBinder;

public class ControlActivity extends AppCompatActivity {
    private MyService myService;
    private boolean isBound = false;
    private ServiceConnection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        ImageView btnPlay =findViewById(R.id.btnPlay);
        ImageView btnPause = findViewById(R.id.btnPause);
        ImageView btnNext = findViewById(R.id.btnNext);
        ImageView btnPrevious = findViewById(R.id.btnPrevious);
        ImageView btnBack = findViewById(R.id.btn_back);
        ImageView img_music = findViewById(R.id.img_music);
        TextView tv_songName = findViewById(R.id.tv_songName);
//
        Intent intentAdapter =getIntent();
        int mp3 = intentAdapter.getIntExtra("mp3",0);
        String name = intentAdapter.getStringExtra("name");
        tv_songName.setText(name);
        int img = intentAdapter.getIntExtra("img",0);
        img_music.setImageResource(img);
//
//        // Khởi tạo ServiceConnection
        connection = new ServiceConnection() {

            // Phương thức này được hệ thống gọi khi kết nối tới service bị lỗi
            @Override
            public void onServiceDisconnected(ComponentName name) {

                isBound = false;
            }

            // Phương thức này được hệ thống gọi khi kết nối tới service thành công
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyBinder binder = (MyBinder) service;
                myService = binder.getService(); // lấy đối tượng MyService
                isBound = true;
            }
        };

        // Khởi tạo intent
        final Intent intent =
                new Intent(ControlActivity.this,
                        MyService.class);
        intent.putExtra("mp3", mp3);
        bindService(intent, connection,
                Context.BIND_AUTO_CREATE);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.play();
                btnPlay.setVisibility(View.INVISIBLE);
                btnPause.setVisibility(View.VISIBLE);
                isBound = true;
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBound){
                    myService.pause();
                    btnPlay.setVisibility(View.VISIBLE);
                    btnPause.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBound){
                    unbindService(connection);
                    isBound = false;
                    finish();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBound){
                    myService.fastForward();
                }else{
                    Toast.makeText(ControlActivity.this,
                            "Service is not running", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBound){
                    myService.fastBack();
                }else{
                    Toast.makeText(ControlActivity.this,
                            "Service is not running", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}