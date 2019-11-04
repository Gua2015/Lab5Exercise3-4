package com.missouristate.guadagnano.todolist1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseManager( this );
        updateView( );
    }

    //Builds View of List
    public void updateView( ) {
        ArrayList<ToDo> candies = dbManager.selectAll( );
        RelativeLayout layout = new RelativeLayout( this );
        ScrollView scrollView = new ScrollView( this );
        RadioGroup group = new RadioGroup( this );
        for ( ToDo candy : candies ) {
            RadioButton rb = new RadioButton( this );
            rb.setId( candy.getId( ) );
            rb.setText( candy.toString( ) );
            group.addView( rb );
        }



        //Setup Event Handling
        RadioButtonHandler rbh = new RadioButtonHandler( );
        group.setOnCheckedChangeListener(rbh);

        // create a back button
        Button backButton = new Button( this );
        backButton.setText( "BACK" );

        backButton.setOnClickListener( new View.OnClickListener( ) {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainView.class);
                startActivity(i);            }
        });

        scrollView.addView(group);
        layout.addView( scrollView );

        // add back button at bottom
        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT );
        params.addRule( RelativeLayout.ALIGN_PARENT_BOTTOM );
        params.addRule( RelativeLayout.CENTER_HORIZONTAL );
        params.setMargins( 0, 0, 0, 50 );
        layout.addView( backButton, params );

        setContentView( layout );
    }


    public class RadioButtonHandler
            implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged( RadioGroup group, int checkedId ) {
            //Delete item from database
            dbManager.deleteById( checkedId );
            Toast.makeText( DeleteActivity.this, "Task Deleted",
                    Toast.LENGTH_SHORT ).show( );

            //Updates the View
            updateView();
        }
    }
}
