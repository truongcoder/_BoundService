package com.example.pvtruong.demoservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by PVTruong on 20/03/2017.
 */

public class PlayAudioService extends Service {

    private Handler handler;
    private int sencond = 0;
    // cấu nối giữa Playservice với Activity
    private IBinder myIBinder=new AudioBinder();
    public int getSencond() {
        return sencond;
    }

    public void setSencond(int sencond) {
        this.sencond = sencond;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        PlayAudio();
    }

    private void PlayAudio() {
        handler.postDelayed(onPlay, 500);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(onPlay);
    }

    private Runnable onPlay = new Runnable() {
        @Override
        public void run() {
            // công việc ở UI  thread
           sencond++;
            if(sencond<=100){
                PlayAudio();
            }

        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myIBinder;
    }

    class AudioBinder extends Binder {
        public PlayAudioService getInstant_PlayAudion(){
            return  PlayAudioService.this;
        }
    }
}
