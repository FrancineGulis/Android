package com.example.francine.simplebd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Francine on 30/10/2017.
 */

public class SimpleBDWrapper extends SQLiteOpenHelper {

    public static final String STUDENTS = "Students";
    public static final String STUDENTS_ID = "_id";
    public static final String STUDENTS_NAME = "_name";

    public static final String DATABASE_NAME = "Studants.db";
    public static final int DATABASE_VERSION = 1;

    //creation SQLite statement
    private static final String DATABASE_CREATE = "create table " + STUDENTS
            + "(" + STUDENTS_ID + " integer primary key autoincrement, "
            + STUDENTS_NAME + " text not null);";

    public SimpleBDWrapper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST " + STUDENTS);
        onCreate(db);
    }
}
