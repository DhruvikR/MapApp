package com.example.mapapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class markerClickActivity extends AppCompatActivity {

    TextView textViewForAddress=findViewById(R.id.textOfAdd);
    Button btnAlarm=findViewById(R.id.btnForAlarm);
    Button btnNoti=findViewById(R.id.btnForNotification);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_click);

        Intent intent=getIntent();

        String textForAddress = "Address : "+intent.getStringExtra("Address_massage");

        textViewForAddress.setText(textForAddress);



    }

    public void addAlarm(View view) {
        Intent intentOfSetAlarm=new Intent(markerClickActivity.this,setAlarmActivity.class);
        startActivity(intentOfSetAlarm);
    }

    public void addNotification(View view) {
        Intent intentOfSetNotification=new Intent(markerClickActivity.this,setNotificationActivity.class);
        startActivity(intentOfSetNotification);
    }
}