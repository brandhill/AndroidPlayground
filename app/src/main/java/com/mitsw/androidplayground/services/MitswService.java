package com.mitsw.androidplayground.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MitswService extends Service {

    private final static String TAG = "MitswService";


    public MitswService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: ");
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.d(TAG, "onBind: ");

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand: ");

        return START_STICKY;
    }
}
