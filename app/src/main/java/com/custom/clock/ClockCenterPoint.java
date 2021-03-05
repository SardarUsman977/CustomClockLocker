package com.custom.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class ClockCenterPoint extends View {
    private int mHeight;
    private int mWidth;
    private int mRadius, mPointRadius;
    private int mCentreX;
    private int mCentreY;
    private boolean mIsInit;
    private double mAngle;
    private int mPadding;
    private Paint mPaint;
    private float mHour;
    private float mMinute;
    private float mSecond;
    private int mMinimum;
    private int mHourHandSize;
    private int mTextHourSize;
    private int  mMinuteSize, mSecondSize;
    float mHourRot, mMinLocation;
    private float mFontSize = 30f;

    public ClockCenterPoint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    private void init(){
        mHeight = getHeight();
        mWidth = getWidth();
        mPadding = 50;
        mCentreX = mWidth/2;
        mCentreY = mHeight/2;
        mPointRadius = 30;
        mMinimum = Math.min(mHeight, mWidth);
        mRadius = mMinimum/2 - mPadding;
        mAngle = (float) ((Math.PI/30) - (Math.PI/2));
        mPaint = new Paint();
        mHourHandSize =  mRadius/2;
        mTextHourSize = mRadius-20;
        mMinuteSize =  mRadius - 55;
        mSecondSize =   mHeight/3;
        mIsInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!mIsInit)
        {
            mIsInit = true;
            init();
        }
        drawPoint(canvas);
//        drawHands(canvas);
//        postInvalidateDelayed(1000);
    }

    private void drawPoint(Canvas canvas)
    {
        setPaintAttribute(Color.BLACK, Paint.Style.FILL, 0);
        Log.d("TAG", "drawPoint: "+mCentreX);
        canvas.drawCircle(mCentreX,mCentreY,mPointRadius,mPaint);
    }



    private void setPaintAttribute(int color, Paint.Style style, int styleWidth){
        mPaint.reset();
        mPaint.setColor(color);
        mPaint.setStyle(style);
        mPaint.setStrokeWidth(styleWidth);
        mPaint.setAntiAlias(true);
    }
}
