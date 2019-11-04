package com.missouristate.guadagnano.todolist1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainView extends AppCompatActivity {
    private DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    //Menu Method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Menu Method
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                Log.w("MainActivity", "Add selected");
                Intent addIntent = new Intent(this, InsertActivity.class);
                this.startActivity(addIntent);
                return true;
            case R.id.action_delete:
                Log.w("MainActivity", "Delete selected");
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Creates the view to show List
    public void updateView() {
        ArrayList<ToDo> list = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);
        for (ToDo toDoList : list) {
            RadioButton rb = new RadioButton(this);
            rb.setId(toDoList.getId());
            rb.setText(toDoList.toString());
            group.addView(rb);
        }

        scrollView.addView(group);
        layout.addView(scrollView);
        setContentView(layout);
    }
}