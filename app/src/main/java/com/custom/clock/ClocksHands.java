package com.custom.clock;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import java.util.Calendar;


public class ClocksHands extends AppCompatImageView {
    private float mMinute, mMinLocation, mSecond, mSecondLocation, hourlocation;
    int mHour;
    boolean first_time = true;
    private Handler mHandler;
    private Runnable runnable;

    public ClocksHands(@NonNull Context context) {
        super(context);
    }

    public ClocksHands(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void MinRotate( final long delay, final Drawable drawable, final TextView minText ) {

            mHandler = new Handler(Looper.getMainLooper());
             runnable = new Runnable() {
                @Override
                public void run() {
                    long delaytime;
                    if (first_time)
                    {
                        delaytime = delay;
                        first_time = false;
                    }else
                    {
                        delaytime = 1000;
                    }
                 //get the Time Of the Day
                    Calendar calendar = Calendar.getInstance();
                    mMinute = calendar.get(Calendar.MINUTE);
                    String min = String.valueOf((int)mMinute);
                    if (mMinute<= 9)
                    {
                        minText.setText("0"+min);
                    }else
                        {
                            minText.setText(min);
                        }
                    mMinLocation = (mMinute / 60.0f) * 360;
                    drawHand(mMinLocation, drawable);
                    mHandler.postDelayed(this, delaytime);

                }
            };
            mHandler.post(runnable);
    }
    public void SecRotate( final long delay, final Drawable drawable ) {

             mHandler = new Handler(Looper.getMainLooper());
             runnable = new Runnable() {
                @Override
                public void run() {
                    long delaytime;
                    if (first_time)
                    {
                        delaytime = delay;
                        first_time = false;
                    }else
                        {
                            delaytime = 1000;
                        }
                    //get the Time Of the Day
                    Calendar calendar = Calendar.getInstance();
                    mSecond = calendar.get(Calendar.SECOND);
                    mSecondLocation = (mSecond / 60.0f) * 360;
                    drawHand(mSecondLocation, drawable);
                    mHandler.postDelayed(this, delaytime);
                }
            };
            mHandler.post(runnable);
    }
    public void HourRotate(final long delay, final Drawable drawable, final TextView hourText) {

            mHandler = new Handler(Looper.getMainLooper());
             runnable = new Runnable() {
                @Override
                public void run() {
                    long delaytime;
                    if (first_time)
                    {
                        delaytime = delay;
                        first_time = false;
                    }else
                    {
                        delaytime = 1000;
                    }
                    //get the Time Of the Day
                    Calendar calendar = Calendar.getInstance();
                    mHour = calendar.get(Calendar.HOUR_OF_DAY);
                    mHour = mHour > 12 ? mHour - 12 : mHour; //convert to 12hour format from 24 hour format
                    if (mHour<= 9)
                    {
                        hourText.setText("0"+mHour);
                    }else
                        {
                            hourText.setText(String.valueOf(mHour));
                        }

                    mMinute = calendar.get(Calendar.MINUTE);
                    float h = calendar.get(Calendar.HOUR);
                    hourlocation = ((12 + h) / 12.0f * 360) % 360 + (mMinute / 60.0f) * 360 / 12.0f;
                    drawHand(hourlocation, drawable);
                    mHandler.postDelayed(this, delaytime);
                    Log.d("TAG", "stop: ");
                }
            };
            mHandler.post(runnable);
    }

    private void drawHand(float mMinLocation, Drawable hourHand) {

          setPivotX(getWidth()/2);
          setPivotY(getHeight()/1.5f);
          setImageDrawable(hourHand);
          setRotation(mMinLocation);
    }
    //Apply Animation to set Time 12:00 when press Center Point
    public void RotateMinHandler(Drawable drawable)
    {
         mHandler.removeCallbacks(runnable);
        AnimateMin(drawable);
    }
    public void RotatehourHandler(Drawable drawable)
    {
        mHandler.removeCallbacks(runnable);
        AnimateHour(drawable);
    }
    private void AnimateMin(final Drawable drawable)
    {
        Calendar calendar = Calendar.getInstance();
        mMinute = calendar.get(Calendar.MINUTE);
        mMinLocation = (mMinute / 60.0f) * 360;
        float pivx = getWidth()/2f;
        float pivy = getHeight()/1.5f;
        Log.d("TAG", "AnimateMin: "+mMinLocation);
        RotateAnimation rotateAnimation = new RotateAnimation(0,  360-mMinLocation, pivx, pivy);
        rotateAnimation.setDuration(1200);
        rotateAnimation.setFillAfter(true);
        setImageDrawable(drawable);
        setAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                setRotation(360);
                clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
    private void AnimateHour(final Drawable drawable)
    {
        Calendar calendar = Calendar.getInstance();
        float h = calendar.get(Calendar.HOUR);
        mMinute = calendar.get(Calendar.MINUTE);
        hourlocation = ((12 + h) / 12.0f * 360) % 360 + (mMinute / 60.0f) * 360 / 12.0f;
        float pivx = getWidth()/2f;
        float pivy = getHeight()/1.5f;
        float frompos, topostion;
        Log.d("TAG", "AnimateHour: "+getRotationX());
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360-hourlocation, pivx, pivy);
        rotateAnimation.setDuration(1200);
        rotateAnimation.setFillAfter(true);
        setImageDrawable(drawable);
        setAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                setRotation(360);
              clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
