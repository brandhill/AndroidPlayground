package com.mitsw.androidplayground.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.mitsw.androidplayground.utils.DimenUtils;

/**
 * Created by Hill on 2016/4/7.
 */
public class RadarScanView extends View {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    private int defaultWidth;
    private int defaultHeight;
    private int start;
    private int centerX;
    private int centerY;
    private int radarRadius;
    private int circleColor = Color.parseColor("#a2a2a2");
    private int radarColor = Color.parseColor("#99a2a2a2");
    private int tailColor = Color.parseColor("#50aaaaaa");

    private Paint mPaintCircle;
    private Paint mPaintRadar;
    private Matrix matrix;

    private Runnable mScanningRunnable = new Runnable() {
        @Override
        public void run() {
            start -= 2;
            matrix = new Matrix();
            matrix.postRotate(start, centerX, centerY);
            postInvalidate();
            RadarScanView.this.postDelayed(mScanningRunnable, 10);
        }
    };

    public RadarScanView(Context context) {
        super(context);
        init(null, context);
    }

    public RadarScanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);
    }

    public RadarScanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        radarRadius = DimenUtils.dp2px(504) / 2;
    }

    private void init(AttributeSet attrs, Context context) {
        initPaint();

        //得到当前屏幕的像素宽高
        defaultWidth = DimenUtils.dp2px(context, DEFAULT_WIDTH);
        defaultHeight = DimenUtils.dp2px(context, DEFAULT_HEIGHT);
    }

    public void startScanAnimation(){
        matrix = new Matrix();
        RadarScanView.this.post(mScanningRunnable);
    }

    public void stopScanAnimation(){
        RadarScanView.this.removeCallbacks(mScanningRunnable);
    }

    private void initPaint() {
        mPaintCircle = new Paint();
        mPaintCircle.setColor(circleColor);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setStrokeWidth(2);

        mPaintRadar = new Paint();
        mPaintRadar.setColor(radarColor);
        mPaintRadar.setAntiAlias(true);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int resultWidth = 0;
//        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
//        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
//
//        if (modeWidth == MeasureSpec.EXACTLY) {
//            resultWidth = sizeWidth;
//        } else {
//            resultWidth = defaultWidth;
//            if (modeWidth == MeasureSpec.AT_MOST) {
//                resultWidth = Math.min(resultWidth, sizeWidth);
//            }
//        }
//
//        int resultHeight = 0;
//        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
//        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
//        if (modeHeight == MeasureSpec.EXACTLY) {
//            resultHeight = sizeHeight;
//        } else {
//            resultHeight = defaultHeight;
//            if (modeHeight == MeasureSpec.AT_MOST) {
//                resultHeight = Math.min(resultHeight, sizeHeight);
//            }
//        }
//
//        setMeasuredDimension(resultWidth, resultHeight);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Shader shader = new SweepGradient(centerX, centerY, new int[]{Color.parseColor("#FFFFFFFF"), Color.parseColor("#00FFFFFF")}, new float[]{0f, 0.05f});
        mPaintRadar.setShader(shader);
        canvas.concat(matrix);
        canvas.drawCircle(centerX, centerY, radarRadius, mPaintRadar);
    }


}