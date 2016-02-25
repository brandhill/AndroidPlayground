package com.mitsw.androidplayground.memoryleak;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mitsw.androidplayground.R;

import java.lang.ref.WeakReference;

public class MemoryLeakActivity extends AppCompatActivity {

    public static final String TAG = "MemoryLeakActivity";
    private TextView textView;
    private static Handler myHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        Log.d(TAG, "onCreate: ");

        textView = (TextView)findViewById(R.id.textview);

//        myHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 800000L);

        myHandler.postDelayed(new AnimRunnable(textView), 30000L);

    }


    private static class AnimRunnable implements Runnable {

        private final WeakReference<TextView> textViewRef;

        protected AnimRunnable(TextView tv){
            textViewRef = new WeakReference<TextView>(tv);
        }

        @Override
        public void run() {
            final TextView tv = textViewRef.get();
            if(tv != null) {
                tv.setText("Done");
                Log.d(TAG, ""+this.getClass().getSimpleName() + " : "+this.getClass().hashCode());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");

        myHandler.removeCallbacksAndMessages(null);

    }
}
