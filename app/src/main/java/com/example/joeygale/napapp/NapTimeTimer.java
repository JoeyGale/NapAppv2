package com.example.joeygale.napapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class NapTimeTimer extends AppCompatActivity {

    // show seconds remaining
    final Thread t = new Thread() {
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Button cancel = (Button) findViewById(R.id.btnCancel);
                            TextView view = (TextView) findViewById(R.id.textView3);
                            Button rotate = (Button) findViewById(R.id.btnRotate);

                            if (TakeANap.napTimeSeconds > 0)
                            {
                                cancel.setText("Cancel: " + TakeANap.napTimeSeconds + " seconds remaining.");
                                rotate.setRotation(rotate.getRotation() + 45);
                            }
                            else
                            {
                                cancel.setText("Shut this dang alarm off!");
                                rotate.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            } catch (InterruptedException e) {
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_time_timer);

        final MediaPlayer mpAlarmClock;

        mpAlarmClock = MediaPlayer.create(this, R.raw.alarm_clock);
        final int secondsInNap = TakeANap.napTimeSeconds;

        t.start();

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
                                                    t.interrupt();
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

    // close the threads and media players if the user hits the back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            t.interrupt();
        }

        return super.onKeyDown(keyCode, event);
    }
}
