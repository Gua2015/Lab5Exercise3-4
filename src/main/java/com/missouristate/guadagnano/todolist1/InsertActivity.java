package com.missouristate.guadagnano.todolist1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    DatabaseManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        dbManager = new DatabaseManager(this);

    }

    public void onAdd(View view) {
        //Get User input
        EditText taskEditText = findViewById(R.id.input_todo);
        String task = taskEditText.getText().toString();
        EditText dateEditText = findViewById(R.id.input_date);
        String date = dateEditText.getText().toString();


        //Put into DB
        ToDo toDoList = new ToDo(0, task, date);
        dbManager.insert(toDoList);
        Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show();

        //Clear Box
        taskEditText.setText("");
        dateEditText.setText("");
    }

    public void goBack( View v ) {
        Intent MainViewIntent = new Intent(this, MainView.class);
        this.startActivity(MainViewIntent);
    }

}

