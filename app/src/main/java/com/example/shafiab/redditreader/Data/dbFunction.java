package com.example.shafiab.redditreader.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shafiab.redditreader.Data.redditContract.redditEntry;

/**
 * Created by shafiab on 9/20/14.
 */
public class dbFunction extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myData.db";


    public dbFunction(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String DATABASE_CREATE_REDDIT = "create table "
                + redditEntry.TABLE_NAME + " ( " + redditEntry.COLUMN_ID
                + " integer primary key autoincrement, "
                +  redditEntry.COLUMN_TITLE + " text not null, "
                + redditEntry.COLUMN_BITMAP_URL + " text not null ); ";

        sqLiteDatabase.execSQL(DATABASE_CREATE_REDDIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + redditEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
