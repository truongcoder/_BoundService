package com.example.pvtruong.demoservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnstart, btndstop;
    Intent intent;
    TextView txt;
    private PlayAudioService boundsService;
    private Handler handlerUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndstop = (Button) findViewById(R.id.btn_stopService);
        btnstart = (Button) findViewById(R.id.btn_statService);
        txt = (TextView) findViewById(R.id.txt_result);
        btndstop.setOnClickListener(Action_OnClick);
        btnstart.setOnClickListener(Action_OnClick);
        handlerUpdate = new Handler();

    }

    private View.OnClickListener Action_OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_statService:
                    StartService();
                    break;
                case R.id.btn_stopService:
                    StopService();
                    break;
            }
        }


    };


    private void StartService() {
        // intent = new Intent(MainActivity.this, CounterService.class);
        //  startService(intent);
        intent = new Intent(MainActivity.this, PlayAudioService.class);
        bindService(intent, myConection, Context.BIND_AUTO_CREATE);

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
            txt.setText(String.valueOf(boundsService.getSencond()));
            if (boundsService.getSencond() <= 100) {
                updatedata();
            }
        }
    };

    private void StopService() {
        // intent = new Intent(MainActivity.this, CounterService.class);
        // stopService(intent);
        intent = new Intent(MainActivity.this, PlayAudioService.class);
        stopService(intent);
    }


    public void OnClick(View view) {
        Intent it=new Intent(view.getContext(),Activity_Detail.class);
        startActivity(it);
    }
}
