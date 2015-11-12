package com.mitsw.androidplayground.memoryleak;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mitsw.androidplayground.R;

public class MemoryLeakActivity extends AppCompatActivity {

    public static final String TAG = "MemoryLeakActivity";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        textView = (TextView)findViewById(R.id.textview);
        Handler handler = new Handler();

        Log.d(TAG, "onCreate");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Done");
            }
        }, 800000L);

    }
}
