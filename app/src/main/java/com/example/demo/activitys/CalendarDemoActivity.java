package com.example.demo.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.demo.R;
import com.example.demo.utils.CalendarUtils;

public class CalendarDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_demo);
        findViewById(R.id.addevent).setOnClickListener(v -> CalendarUtils.addEvent(this,10551));
    }
}
