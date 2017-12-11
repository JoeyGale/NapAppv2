package com.example.joeygale.napapp;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SeeNotes extends ListActivity
{
    //list items
    ArrayList<String> listItems=new ArrayList<String>();

    //adapter to handle the listview
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_see_notes);
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        EditText noteTitle = (EditText) findViewById(R.id.txtNoteTitle);
        EditText noteContent = (EditText) findViewById(R.id.txtNoteContent);

        listItems.add(noteTitle.getText() + "\n" + noteContent.getText());
        adapter.notifyDataSetChanged();

        // Show toast message
        Toast toast = Toast.makeText(getApplicationContext(),"\"" + noteTitle.getText() + "\" added",
                Toast.LENGTH_SHORT);
        toast.show();
    }
}