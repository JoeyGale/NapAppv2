package com.example.joeygale.napapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Hawkins on 12/11/17.
 */

public class Splash extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		TimerTask task = new TimerTask()
		{
			@Override
			public void run()
			{
				finish();
				startActivity(new Intent(Splash.this, MainActivity.class));
			}
		};
		Timer opening = new Timer();
		opening.schedule(task, 5000);
	}
}
