package com.mitsw.androidplayground.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mitsw.androidplayground.R;

public class RippleActivity extends AppCompatActivity {
    RippleScaleAnimView mRippleBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);

        mRippleBackground = (RippleScaleAnimView)findViewById(R.id.ripple_background);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRippleBackground.startRippleAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRippleBackground.stopRippleAnimation();
    }
}
