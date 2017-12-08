package com.example.joeygale.napapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TakeANap extends AppCompatActivity {

    public static int napTimeSeconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_nap);

        Button next = (Button) findViewById(R.id.btnStartNap);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), NapTimeTimer.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Calendar rightNow = Calendar.getInstance();
        final int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
        final int currentMinute = (currentHour * 60) + rightNow.get(Calendar.MINUTE);

        TimePicker timePicker = (TimePicker) findViewById(R.id.tpPickTime);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker arg0, int arg1, int arg2) {
                int afterNapHour = arg0.getCurrentHour();
                int afterNapMinute = (afterNapHour * 60) + arg0.getCurrentMinute();
                int napTimeMinutes = afterNapMinute - currentMinute;
                napTimeSeconds = (napTimeMinutes * 60);

                TextView napQuality = (TextView) findViewById(R.id.txtNapQuality);

                if (napTimeMinutes <= 9) //
                {
                    napQuality.setText("Really no point, but go ahead, we won't judge");
                } else if (napTimeMinutes >= 10 && napTimeMinutes <= 20) {
                    napQuality.setText("The very definition of a POWER NAP. If you don't take it we will.");
                } else if (napTimeMinutes >= 21 && napTimeMinutes <= 28 && napTimeMinutes != 26) {
                    napQuality.setText("Great quality nap for quantity of time");
                } else if (napTimeMinutes == 26) {
                    napQuality.setText("The greatest amount of time for a nap. NASA says so.");
                } else if (napTimeMinutes >= 29 && napTimeMinutes <= 50) {
                    napQuality.setText("Wouldn't recommend, sleep-inertia is a strong possibility ='(");
                } else if (napTimeMinutes >= 51 && napTimeMinutes <= 84) {
                    napQuality.setText("Will notice great effects from nap, however may feel groggy");
                } else if (napTimeMinutes >= 85) // hour and a half nap go through a full sleep schedule.
                {
                    napQuality.setText("Oh this nap will be fantastic");
                } else {
                    napQuality.setText("Longer you take to decide, the shorter the nap!");
                }
                    }
                });
            }
        }
