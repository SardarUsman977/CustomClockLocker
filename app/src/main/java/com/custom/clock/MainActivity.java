package com.custom.clock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ClocksHands minuteHand, secondHand, hourHand;
    private Drawable minhand, secHand, hrHand;
    private TextView hourText, minText;
    private ClockCenterPoint clockCenterPoint;
    boolean move = false;
    private float i, j, pivx, pivy;
    String password="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minuteHand = findViewById(R.id.min1);
        secondHand = findViewById(R.id.sec);
        hourHand = findViewById(R.id.hour);
        hourText = findViewById(R.id.hourtext);
        minText = findViewById(R.id.mintext);
        clockCenterPoint = findViewById(R.id.clockcenter);
        // create clock
        createClock( );
        clockCenterPoint.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                if (!move){
                    move = true;
                    minuteHand.RotateMinHandler(minhand);
                    secondHand.setVisibility(View.GONE);
                    hourHand.RotatehourHandler(hrHand);
                    hourText.setText("12");
                    minText.setText("00");
                }else {
                    String hr=(String) hourText.getText();
                    String min= (String) minText.getText();
                    if (password.equals("")){
                         hr= (String) hourText.getText();
                         min= (String) minText.getText();
                        password = hr+min;
                        createClock();
                        move = false;
                        Toast.makeText(MainActivity.this, "Password set Successfully", Toast.LENGTH_SHORT).show( );
                    }else if (password.equals(hr+min)){
                        move = false;
                        Toast.makeText(MainActivity.this, "Password match Successfully", Toast.LENGTH_SHORT).show( );
                        createClock();
                    }
                }

            }
        });

        //Move Clock Hands Acoording Your Needs
        TouchListener( );
    }

    @SuppressLint("ClickableViewAccessibility")
    private void TouchListener() {
        hourHand.setOnTouchListener(new View.OnTouchListener( ) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                i = motionEvent.getX( );
                j = motionEvent.getY( );
                pivx = view.getPivotX( );
                pivy = view.getPivotY( );
                switch (motionEvent.getAction( )) {
                    case MotionEvent.ACTION_MOVE:
                        // one method
                        if (move) {
                            int angle = getAngle(pivx, pivy, i, j);
                            if (angle < 0)
                                angle = angle + 360;
                            view.setRotation((view.getRotation( ) + angle) % 360.0F);
                            float a1 = view.getRotation( );
                            if (a1 > 0 && a1 <= 15) {

                                hourText.setText("12");
                            }
                            if (a1 > 15 && a1 <= 30) {
                                hourText.setText("01");
                            }
                            if (a1 > 30 && a1 <= 45) {

                                hourText.setText("01");
                            }
                            if (a1 > 45 && a1 <= 60) {

                                hourText.setText("02");
                            }
                            if (a1 > 60 && a1 <= 75) {

                                hourText.setText("02");

                            }
                            if (a1 > 75 && a1 <= 90) {
                                hourText.setText("03");
                            }
                            if (a1 > 90 && a1 <= 105) {

                                hourText.setText("03");
                            }
                            if (a1 > 105 && a1 <= 120) {

                                hourText.setText("04");
                            }
                            if (a1 > 120 && a1 <= 135) {

                                hourText.setText("04");
                            }
                            if (a1 > 135 && a1 <= 150) {

                                hourText.setText("05");
                            }
                            if (a1 > 150 && a1 <= 165) {
                                hourText.setText("05");
                            }
                            if (a1 > 165 && a1 <= 180) {
                                hourText.setText("06");
                            }
                            if (a1 > 180 && a1 <= 195) {

                                hourText.setText("06");
                            }
                            if (a1 > 195 && a1 <= 210) {

                                hourText.setText("07");
                            }
                            if (a1 > 210 && a1 <= 225) {
                                hourText.setText("07");
                            }
                            if (a1 > 225 && a1 <= 240) {

                                hourText.setText("08");
                            }
                            if (a1 > 240 && a1 <= 255) {

                                hourText.setText("08");
                            }
                            if (a1 > 255 && a1 <= 270) {

                                hourText.setText("09");
                            }
                            if (a1 > 270 && a1 <= 285) {

                                hourText.setText("09");
                            }
                            if (a1 > 285 && a1 <= 300) {

                                hourText.setText("10");
                            }
                            if (a1 > 300 && a1 <= 315) {

                                hourText.setText("10");
                            }
                            if (a1 > 315 && a1 <= 330) {

                                hourText.setText("11");
                            }
                            if (a1 > 330 && a1 <= 345) {
                                hourText.setText("11");
                            }
                            if (a1 > 345 && a1 <= 360) {

                                hourText.setText("12");
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (move) {
                            float a = view.getRotation( );
                            if (a > 0 && a <= 15) {
                                hourHand.setRotation(0);

                            }
                            if (a > 15 && a <= 30) {
                                hourHand.setRotation(30);

                            }
                            if (a > 30 && a <= 45) {
                                hourHand.setRotation(30);

                            }
                            if (a > 45 && a <= 60) {
                                hourHand.setRotation(60);

                            }
                            if (a > 60 && a <= 75) {
                                hourHand.setRotation(60);


                            }
                            if (a > 75 && a <= 90) {
                                hourHand.setRotation(90);

                            }
                            if (a > 90 && a <= 105) {
                                hourHand.setRotation(90);

                            }
                            if (a > 105 && a <= 120) {
                                hourHand.setRotation(120);

                            }
                            if (a > 120 && a <= 135) {
                                hourHand.setRotation(120);

                            }
                            if (a > 135 && a <= 150) {
                                hourHand.setRotation(150);

                            }
                            if (a > 150 && a <= 165) {
                                hourHand.setRotation(150);

                            }
                            if (a > 165 && a <= 180) {
                                hourHand.setRotation(180);

                            }
                            if (a > 180 && a <= 195) {
                                hourHand.setRotation(180);

                            }
                            if (a > 195 && a <= 210) {
                                hourHand.setRotation(210);

                            }
                            if (a > 210 && a <= 225) {
                                hourHand.setRotation(210);

                            }
                            if (a > 225 && a <= 240) {
                                hourHand.setRotation(240);

                            }
                            if (a > 240 && a <= 255) {
                                hourHand.setRotation(240);

                            }
                            if (a > 255 && a <= 270) {
                                hourHand.setRotation(270);

                            }
                            if (a > 270 && a <= 285) {
                                hourHand.setRotation(270);

                            }
                            if (a > 285 && a <= 300) {
                                hourHand.setRotation(300);

                            }
                            if (a > 300 && a <= 315) {
                                hourHand.setRotation(300);

                            }
                            if (a > 315 && a <= 330) {
                                hourHand.setRotation(330);

                            }
                            if (a > 330 && a <= 345) {
                                hourHand.setRotation(330);

                            }
                            if (a > 345 && a <= 360) {
                                hourHand.setRotation(360);

                            }
                        }
                        break;
                }
                return true;
            }
        });
        minuteHand.setOnTouchListener(new View.OnTouchListener( ) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float i = motionEvent.getX( );
                float j = motionEvent.getY( );
                float pivx = view.getPivotX( );
                float pivy = view.getPivotY( );
                switch (motionEvent.getAction( )) {
                    case MotionEvent.ACTION_DOWN:
                        view.setRotation(view.getRotation( ));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (move) {
                            float angle = getAngle(pivx, pivy, i, j);
                            if (angle < 0)
                                angle = angle + 360;
                            view.setRotation((view.getRotation( ) + angle) % 360.0f);
                            float b = view.getRotation( );
                            int m = (int) (b * 60) / 360;
                            String min;
                            if (m < 10) {
                                min = "0" + m;
                            } else {
                                min = String.valueOf(m);
                            }
                            minText.setText(min);
                        }
                        break;
                }
                return true;
            }
        });
    }

    private int getAngle(float pivotX, float pivotY, float x, float y) {
        return (int) ((Math.toDegrees(Math.atan2(pivotY - y, pivotX - x)))) - 90;
    }

    public void createClock() {
        // set Image Resource for Clocks Hands
        minhand = ResourcesCompat.getDrawable(getResources( ), R.drawable.min_hand, null);
        secHand = ResourcesCompat.getDrawable(getResources( ), R.drawable.sec_hand, null);
        hrHand = ResourcesCompat.getDrawable(getResources( ), R.drawable.hr_hand, null);

        // set Clock hands to Exact Time
        secondHand.setVisibility(View.VISIBLE);
        minuteHand.MinRotate(10, minhand, minText);
        secondHand.SecRotate(10, secHand);
        hourHand.HourRotate(10, hrHand, hourText);
    }
}