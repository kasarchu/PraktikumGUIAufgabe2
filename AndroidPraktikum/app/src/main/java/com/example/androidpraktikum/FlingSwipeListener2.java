package com.example.androidpraktikum;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class FlingSwipeListener2 extends GestureDetector.SimpleOnGestureListener {

    private FotoActivity fotoActivity;

    public FlingSwipeListener2(FotoActivity fotoActivity) {

        this.fotoActivity = fotoActivity;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        float abWann = 20.0f;
        if ((e1.getX() - e2.getX()) > abWann) {
                fotoActivity.links();
        } else if ((e2.getX() - e1.getX()) > abWann) {
                fotoActivity.rechts();
        }
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        // MUSS immer true sein sonst wird Geste nicht erkannt
        return true;
    }
}
