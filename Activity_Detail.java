package com.example.pvtruong.demoservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_Detail extends AppCompatActivity {
    private    TextView txt;
    private PlayAudioService boundsService;
    private Handler handlerUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__detail);
        txt= (TextView) findViewById(R.id.txt);
        handlerUpdate=new Handler();

    }
    ServiceConnection myConection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // nhận dữ liệu từ service
            PlayAudioService.AudioBinder binder = (PlayAudioService.AudioBinder) service;
            boundsService = binder.getInstant_PlayAudion();
            updatedata();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void updatedata() {

        handlerUpdate.postDelayed(display, 500);

    }

    private Runnable display = new Runnable() {
        @Override
        public void run() {
            txt.setText(boundsService.getSencond());
            if (boundsService.getSencond() <= 100) {
                updatedata();
            }
        }
    };
}
