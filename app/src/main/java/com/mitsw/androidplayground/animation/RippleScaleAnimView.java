package com.mitsw.androidplayground.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import com.mitsw.androidplayground.utils.DimenUtils;

import java.util.ArrayList;

/**
 * Created by Hill on 2016/4/6.
 */
public class RippleScaleAnimView extends RelativeLayout {

    private static final String TAG = "RippleScaleAnimView";


    private boolean animationRunning = false;
    private AnimatorSet animatorSet;
    private RelativeLayout.LayoutParams mRippleParams;
    private RadarScanView mRadarScanView;

    private int centerX;
    private int centerY;

    public RippleScaleAnimView(Context context) {
        super(context);
    }

    public RippleScaleAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RippleScaleAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        if (isInEditMode())
            return;

        if (null == attrs) {
            throw new IllegalArgumentException("Attributes should be provided to this view,");
        }
        setClipChildren(false);

        mRippleParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRippleParams.addRule(CENTER_IN_PARENT, TRUE);

        mRadarScanView = new RadarScanView(getContext());
        mRadarScanView.setVisibility(INVISIBLE);
        addView(mRadarScanView, mRippleParams);

        animatorSet = new AnimatorSet();
        animatorSet.playTogether(createRippleCycles());

    }


    private class RippleView extends View {

        int radius;
        Paint fillPaint;
        Paint strokepaint;
        int strokeColor;
        int fillColor;
        int strokeWidth = DimenUtils.dp2px(2);

        public RippleView(Context context) {
            super(context);

            this.setScaleX(0.0f);
            this.setScaleY(0.0f);
        }

        public RippleView(Context context, int size, int strokeColor, int fillColor) {
            this(context);

            this.radius = size / 2;
            this.fillColor = fillColor;
            this.strokeColor = strokeColor;

            fillPaint = new Paint();
            fillPaint.setAntiAlias(true);
            fillPaint.setStyle(Paint.Style.FILL);
            fillPaint.setColor(fillColor);

            strokepaint = new Paint(fillPaint);
            strokepaint.setAntiAlias(true);
            strokepaint.setStyle(Paint.Style.STROKE);
            strokepaint.setStrokeWidth(strokeWidth);
            strokepaint.setColor(strokeColor);

        }

        public void setStrokeColor(int color) {
            strokeColor = color;
            strokepaint.setColor(strokeColor);
        }

        public void setFillColor(int color) {
            fillColor = color;
            fillPaint.setColor(fillColor);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int xPos = (canvas.getWidth() / 2);
            int yPos = (canvas.getHeight() / 2);

            canvas.drawCircle(xPos, yPos, radius, fillPaint);
            canvas.drawCircle(xPos, yPos, radius, strokepaint);
            //super.onDraw(canvas);
        }
    }

    private ValueAnimator createRippleScaleAnimator(int size, int strokeColor, int fillColor, long duration, long startDelay, AnimatorListenerAdapter listenerAdapter) {

        final RippleView ripple = new RippleView(getContext(), size, strokeColor, fillColor);
        addView(ripple, mRippleParams);

        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(ripple, scaleX, scaleY);
        animator.setInterpolator(new OvershootInterpolator(1.0f));
        animator.setStartDelay(startDelay);
        animator.setDuration(duration);
        animator.start();

        if (listenerAdapter != null) {
            animator.addListener(listenerAdapter);
        }

        return animator;
    }

    private ArrayList<Animator> createRippleCycles() {
        ArrayList<Animator> animatorList = new ArrayList<Animator>();


        AnimatorListenerAdapter radarScanListener = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (mRadarScanView != null) {
                    mRadarScanView.setVisibility(VISIBLE);
                    mRadarScanView.startScanAnimation();
                }
            }
        };

        animatorList.add(createRippleScaleAnimator(DimenUtils.dp2px(504), Color.parseColor("#73ffffff"), Color.parseColor("#33ffffff"), 1266, 0, null));
        animatorList.add(createRippleScaleAnimator(DimenUtils.dp2px(410), Color.parseColor("#73ffffff"), Color.parseColor("#1affffff"), 1300, 400, null));
        animatorList.add(createRippleScaleAnimator(DimenUtils.dp2px(268), Color.parseColor("#73ffffff"), Color.parseColor("#1affffff"), 1300, 766, null));
        animatorList.add(createRippleScaleAnimator(DimenUtils.dp2px(150), Color.parseColor("#73ffffff"), Color.parseColor("#1affffff"), 1300, 1000, null));
        animatorList.add(createRippleScaleAnimator(DimenUtils.dp2px(72), Color.parseColor("#73ffffff"), Color.parseColor("#1affffff"), 1300, 1067, radarScanListener));


        return animatorList;
    }


    public void startRippleAnimation() {
        if (!isRippleAnimationRunning()) {
            animatorSet.start();
            animationRunning = true;
        }
    }

    public void stopRippleAnimation() {
        if (isRippleAnimationRunning()) {
            animatorSet.end();
            animationRunning = false;

            if (mRadarScanView != null) {
                mRadarScanView.setVisibility(INVISIBLE);
                mRadarScanView.stopScanAnimation();
            }

        }
    }

    public boolean isRippleAnimationRunning() {
        return animationRunning;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }
}
