package com.missouristate.guadagnano.todolist1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Reference the home screen
        Intent MainViewIntent = new Intent(this, MainView.class);
        this.startActivity(MainViewIntent);
    }
}
