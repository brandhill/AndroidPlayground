package com.mitsw.androidplayground.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.mitsw.androidplayground.utils.log.RxSubjectHelper;

public class MitswService extends Service {

    private final static String TAG = "MitswService";
    public final static String FROM = "from";


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


        if(intent != null) {
            int from = intent.getIntExtra(FROM, -1);
            if (from != -1) {
                Log.d(TAG, "from: " + from);
            }
        }

        RxSubjectHelper usernameModel = RxSubjectHelper.instanceOf();
        usernameModel.getStringObservable()
                .subscribe(s -> {
                    System.out.println("received in service : " + s);
                }, throwable -> {
                    // Normally no error will happen here based on this example.
                });


        return START_STICKY;
    }
}
