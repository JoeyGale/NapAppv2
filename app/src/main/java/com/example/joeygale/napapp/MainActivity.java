package com.example.joeygale.napapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button takeANap = (Button) findViewById(R.id.btnTakeANap);
        takeANap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), TakeANap.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button seeNotes = (Button) findViewById(R.id.btnSeeNotes);
        seeNotes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SeeNotes.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
