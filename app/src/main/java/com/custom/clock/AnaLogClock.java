package com.custom.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class AnaLogClock extends View{
    private int mHeight;
    private int mWidth;
    private int mRadius, mPointRadius;
    private double mAngle;
    private int mCentreX;
    private int mCentreY;
    private int mPadding;
    private boolean mIsInit;
    private Paint mPaint;
    private Rect mRect;
    private int[] mNumbers;
    private int mMinimum;
    private float mHour;
    private float mMinute;
    private float mSecond;
    private int mHourHandSize;
    private int mTextHourSize;
    private int  mMinuteSize, mSecondSize;
    private float mFontSize = 30f;
    float mHourRot, mMinLocation;

    public AnaLogClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(){
        mHeight = getHeight();
        mWidth = getWidth();
        mPadding = 50;
        mCentreX = mWidth/2;
        mCentreY = mHeight/2;

        mPointRadius = 40;
        mMinimum = Math.min(mHeight, mWidth);
        mRadius = mMinimum/2 - mPadding;

        mAngle = (float) ((Math.PI/30) - (Math.PI/2));

        mPaint = new Paint();
        mRect = new Rect();

        mHourHandSize =  mRadius/2;
        mTextHourSize = mRadius-20;
        mMinuteSize =  mRadius - 55;
        mSecondSize =   mHeight/3;

        mNumbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
//        mNumbers = new int[]{1, 2, 3, 4, 5, 6,12,14};
        mIsInit = true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!mIsInit) {
            mIsInit = true;
            init();
        }
        drawCircle(canvas);
//        drawPoint(canvas);
//        drawHands(canvas);
        drawNumerals(canvas);
//        postInvalidateDelayed(1000);
    }

    private void drawCircle(Canvas canvas)
    {
        setPaintAttribute(Color.BLACK, Paint.Style.STROKE, 8);
        canvas.drawCircle(mCentreX,mCentreY,mRadius+40,mPaint);


    }
//    private void drawPoint(Canvas canvas)
//    {
//        setPaintAttribute(Color.BLACK, Paint.Style.FILL, 0);
//        Log.d("TAG", "drawPoint: "+mCentreX);
//        canvas.drawCircle(mCentreX,mCentreY,mPointRadius,mPaint);
//    }
//
//    private void drawHands(Canvas canvas) {
//        Calendar calendar = Calendar.getInstance();
//        mHour = calendar.get(Calendar.HOUR_OF_DAY);
//        mHour = mHour > 12 ? mHour - 12 : mHour; //convert to 12hour format from 24 hour format
//        mMinute = calendar.get(Calendar.MINUTE);
//        mSecond = calendar.get(Calendar.SECOND);
////        mHourRot = ((12 + mHour) / 12.0f * 360) % 360 + (mMinute / 60.0f) * 360 / 12.0f;
//        mMinLocation = (mMinute / 60.0f) * 360 ;
//        drawHourHand(canvas, (mHour + mMinute / 60.0) * 5f);
//
//        drawMinuteHand(canvas, mMinLocation);
//        drawSecondsHand(canvas, mSecond);
//
//    }
//
//    private void drawHourHand(Canvas canvas, double location) {
//        mPaint.reset();
//        setPaintAttribute(Color.GRAY, Paint.Style.STROKE,10);
//        mAngle = Math.PI * location / 30 - Math.PI / 2;
//        canvas.drawLine(mCentreX,mCentreY,(float) (mCentreX+Math.cos(mAngle)*mHourHandSize)
//                , (float) (mCentreY+Math.sin(mAngle)*mHourHandSize),mPaint);
////        final Drawable hourHand = ResourcesCompat.getDrawable(getResources(), R.drawable.hour_hand, null);
////        int w = hourHand.getIntrinsicWidth();
////        int y = hourHand.getIntrinsicHeight();
////        hourHand.setBounds(mCentreX - (w / 2), mCentreY - (y / 2), mCentreX + (w / 2),
////                mCentreY + (y / 2));
////        hourHand.draw(canvas);
//    }
//
//    private void drawMinuteHand(Canvas canvas, float mMinLocation) {
//        //for Draw Minute Line
////        mPaint.reset();
////        setPaintAttribute(Color.RED, Paint.Style.STROKE,8);
////        mAngle = Math.PI * location / 30 - Math.PI / 2;
////        canvas.drawLine(mCentreX,mCentreY,(float) (mCentreX+Math.cos(mAngle)* mMinuteSize)
////                , (float) (mCentreY+Math.sin(mAngle)* mMinuteSize),mPaint);
//
//        //for use  Minute Image
//        final Drawable hourHand = ResourcesCompat.getDrawable(getResources(), R.drawable.hour_hand, null);
//        int w = hourHand.getIntrinsicWidth();
//        int y = hourHand.getIntrinsicHeight();
//
//        hourHand.setBounds(mCentreX - (w / 2), mCentreY - (y / 2), mCentreX + (w / 2),
//                mCentreY + (y / 2));
//        canvas.save();
//        canvas.rotate(mMinLocation, mCentreX, mCentreY);
//        hourHand.draw(canvas);
//        canvas.restore();
//    }
//
//    private void drawSecondsHand(Canvas canvas, float location) {
//        String second = String.valueOf((int) location);
//        TextPaint textPaint = new TextPaint();
//        textPaint.setTextSize(mFontSize);
//        textPaint.setColor(Color.BLACK);
//        textPaint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
//        canvas.drawText(second, mCentreX, mSecondSize, textPaint);
////        mAngle = Math.PI * location / 30 - Math.PI / 2;
////        canvas.drawLine(mCentreX,mCentreY,(float) (mCentreX+Math.cos(mAngle)*mSecondSize)
////                , (float) (mCentreY+Math.sin(mAngle)*mSecondSize),mPaint);
//    }

        private void drawNumerals(Canvas canvas) {
            TextPaint textPaint = new TextPaint();
            textPaint.setTextSize(mFontSize);
            textPaint.setColor(Color.BLUE);
            textPaint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
            for (int number : mNumbers) {
                String tmp = String.valueOf(number);
                textPaint.getTextBounds(tmp, 0, tmp.length(), mRect);
                double angle = Math.PI / 6 * (number - 3);
                int x = (int) (mCentreX + Math.cos(angle) * mRadius - mRect.width() / 2);
                int y = (int) (mCentreY + Math.sin(angle) * mRadius + mRect.height() / 2);
                canvas.drawText(tmp, x, y, textPaint);
            }
    }

    private void setPaintAttribute(int color, Paint.Style style, int styleWidth){
        mPaint.reset();
        mPaint.setColor(color);
        mPaint.setStyle(style);
        mPaint.setStrokeWidth(styleWidth);
        mPaint.setAntiAlias(true);
    }

}
