package com.example.android.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText("Wow!");
        textView.setAllCaps(true);
        textView.setTextSize(50);
        textView.setTextColor(Color.RED);

        setContentView(textView);
    }
}
