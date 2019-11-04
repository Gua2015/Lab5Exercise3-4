package com.missouristate.guadagnano.todolist1;

import android.content.Intent;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ToDo {
    private int id;
    private String item;
    private String date;

    //Constructor
    public ToDo(int newID, String newItem, String newDate) {
        setID(newID);
        setItem(newItem);
        setDate(newDate);
    }

    //Set Methods
    private void setDate(String newDate) {
        date = newDate;
    }

    private void setItem(String newItem) {
        item = newItem;
    }

    private void setID(int newID) {
        id = newID;
    }

    //Get Methods
    public int getId(){
        return id;
    }

    public String getItem(){
        return item;
    }

    //Gets Current Date
    Calendar cal = Calendar.getInstance();
    String curDate = DateFormat.getDateInstance().format(cal.getTime());


    public String getDate(){
        return date;
    }

    public String toString()
    {
        return id + "; " + item + "; DUE: " + date + " | CUR: " + curDate;
    }
}
