package com.example.pvtruong.demoservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import java.security.Provider;

/**
 * Created by PVTruong on 20/03/2017.
 */

public class CounterService extends Service {
    private Handler handler;
    private int counter = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        Counter();
    }

    private void Counter() {
        handler.postDelayed(doBackground, 500);
    }

    private Runnable doBackground = new Runnable() {
        @Override
        public void run() {
            // công việc ở UI  thread
            counter++;
            Log.d("count", counter + "");
            if (counter <= 10) {
                Counter();
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
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(doBackground);
    }
}
