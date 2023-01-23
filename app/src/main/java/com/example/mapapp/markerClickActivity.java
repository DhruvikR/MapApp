package com.example.mapapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class markerClickActivity extends AppCompatActivity {

    TextView editText,textOfSeekBar;
    String textForAddress;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_click);

        Intent intent=getIntent();

        textForAddress = "Address : "+intent.getStringExtra("Address_massage");

//        String s = "Address h" + "as Longitude ::: " + String.valueOf(longitude) + " And Latitude ::: " + String.valueOf(latitude);
//        Toast toast=Toast.makeText(getApplicationContext(),textForAddress,Toast.LENGTH_LONG);
//        toast.setMargin(50,50);
//        toast.show();

        editText=(TextView) findViewById(R.id.textViewForAddress);
        editText.setText(textForAddress);
        seekBar=findViewById(R.id.seekBar);
        textOfSeekBar=findViewById(R.id.textViewSeekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                textOfSeekBar.setText("" + progress);
                textOfSeekBar.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


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