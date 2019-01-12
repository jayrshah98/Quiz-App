package com.example.jay.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.jay.quiz.QuizContainer.*;

public class SQLiteHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME = "Quiz";




    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + User.TABLE_NAME + " (" + User.Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + User.Table_Column_1_Name + " VARCHAR, " + User.Table_Column_2_Email + " VARCHAR, " + User.Table_Column_3_Password + " VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        onCreate(db);

    }

    public long insert(String table, ContentValues cv, String whereclm) {
        SQLiteDatabase dataBase = getWritableDatabase();
        long a = dataBase.insert(table, whereclm, cv);
        return a;
    }



}
