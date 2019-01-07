package com.ct.mukul.weatherforcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tv1 = (TextView) findViewById(R.id.textView);
        tv1.setText(getIntent().getStringExtra("Date"));
    }
}
