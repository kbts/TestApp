package com.example.kbts.testapp.view;

import android.appwidget.AppWidgetHost;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by KBTS on 2017/9/6.
 */

public class TestView extends View {

    private static final int DEFAULT_SIZE = 300;
    private int mSize = DEFAULT_SIZE;

    private SweepGradient mShader;
    private Paint mPaint;
    private int[] mColors = {Color.RED, Color.BLUE};

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                width = DEFAULT_SIZE;
                break;
            default:
                width = widthSize;
                break;
        }
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = DEFAULT_SIZE;
                break;
            default:
                height = heightSize;
                break;
        }
        mSize = width < height ? width : height;
        setMeasuredDimension(mSize, mSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mShader = new SweepGradient(mSize * 1.0f / 2, mSize * 1.0f / 2, mColors, new float[]{0, 1f});
        mPaint.setShader(mShader);
        canvas.drawCircle(mSize * 1.0f / 2, mSize * 1.0f / 2, 200f, mPaint);
    }
}
