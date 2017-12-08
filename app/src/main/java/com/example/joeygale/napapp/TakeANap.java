package com.example.joeygale.napapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TakeANap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_nap);

        Calendar rightNow = Calendar.getInstance();
        final int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
        final int currentMinute = (currentHour * 60) + rightNow.get(Calendar.MINUTE);

        TimePicker timePicker = (TimePicker) findViewById(R.id.tpPickTime);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener()
        {
            public void onTimeChanged(TimePicker arg0, int arg1, int arg2)
            {
                int afterNapHour = arg0.getCurrentHour();
                int afterNapMinute = (afterNapHour * 60) +  arg0.getCurrentMinute();

                TextView napQuality = (TextView)findViewById(R.id.txtNapQuality);

                if (afterNapMinute - currentMinute >= 91) // hour and a half nap and above is good?
                {
                    napQuality.setText("Quality of Nap will be: Good!");
                }
            }
        });
    }
}
