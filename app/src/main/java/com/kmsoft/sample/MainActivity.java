package com.kmsoft.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.kmsoft.feedbacktask.BaseActivity;
import com.kmsoft.feedbacktask.CaptureScreenShots;

public class MainActivity extends BaseActivity {

    RelativeLayout mainLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainLayout,new BlankFragment());
        transaction.addToBackStack(null);
        transaction.commit();

    }


}