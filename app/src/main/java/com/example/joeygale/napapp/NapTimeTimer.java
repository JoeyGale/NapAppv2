package com.example.joeygale.napapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class NapTimeTimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_time_timer);

        final MediaPlayer mpAlarmClock;

        mpAlarmClock = MediaPlayer.create(this, R.raw.alarm_clock);
        int secondsInNap = TakeANap.napTimeSeconds;

        //Declare the timer
        final Timer myTimer = new Timer();
        //Set the schedule function and rate
        myTimer.scheduleAtFixedRate(new TimerTask() {
                                        @Override
                                        public void run()
                                        {
                                            //Called at every 1000 milliseconds (1 second)
                                            Log.i("MainActivity", "Repeated task");

                                            TakeANap.napTimeSeconds--;

                                            if (TakeANap.napTimeSeconds == 0)
                                            {
                                                mpAlarmClock.start();
                                                mpAlarmClock.setLooping(true);
                                            }
                                            Button cancel = (Button) findViewById(R.id.btnCancel);
                                            cancel.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v)
                                                {
                                                    mpAlarmClock.pause();
                                                    myTimer.cancel();
                                                    Intent myIntent = new Intent(NapTimeTimer.this, MainActivity.class);
                                                    startActivityForResult(myIntent, 0);
                                                }
                                            });


                                        }
                                    },
                //set the amount of time in milliseconds before first execution
                0,
                //Set the amount of time between each execution (in milliseconds)
                1000);

    }
}
