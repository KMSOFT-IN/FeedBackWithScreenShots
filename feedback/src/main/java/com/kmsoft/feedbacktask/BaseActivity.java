package com.kmsoft.feedbacktask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        new CaptureScreenShots().handleTouch(this,ev,getWindow().getDecorView().getRootView());
        return true;

    }
}