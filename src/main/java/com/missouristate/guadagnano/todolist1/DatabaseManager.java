package com.missouristate.guadagnano.todolist1;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TODO = "toDo";
    private static final String ITEM = "item";
    private static final String DATE = "date";
    private static final String ID = "id";

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Build sql create statement
        String sqlCreate = "create table " + TABLE_TODO + "(" + ID;
        sqlCreate += " integer primary key autoincrement, " + ITEM;
        sqlCreate += " text, " + DATE;
        sqlCreate += " text)";

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_TODO);
        // Re-create tables
        onCreate(db);
    }

    public void insert(ToDo toDoItems){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_TODO;
        sqlInsert += " values( null, '" + toDoItems.getItem();
        sqlInsert += "', '" + toDoItems.getDate() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteById( int id ){
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_TODO;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL( sqlDelete );
        db.close( );
    }

    public ArrayList<ToDo> selectAll( ) {
        String sqlQuery = "select * from " + TABLE_TODO;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<ToDo> items = new ArrayList<ToDo>( );
        while( cursor.moveToNext( ) ) {
            ToDo currentToDo
                    = new ToDo( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ), cursor.getString( 2 ) );
            items.add(currentToDo);
        }
        db.close( );
        return items;
    }
}
