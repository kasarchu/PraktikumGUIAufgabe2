package com.example.androidpraktikum;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnTouchListener implements View.OnTouchListener {

    private GestureDetector gDetect;

    public OnTouchListener(GestureDetector gDetect) {
        this.gDetect = gDetect;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gDetect.onTouchEvent(event);
    }
}
