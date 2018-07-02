package com.example.karthik.spider4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by karthik on 01-07-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String table_name = "FavTracks";
    private static final String dbName = "Tracks";
    private static final  String Col1 = "_id";
    private static final  String Col2 = "trackName";
    private static final String Col3 = "artist";
    private static final String Col4 = "year";


    public DatabaseHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + table_name + " ( " + Col1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + Col2 + " TEXT," + Col3 + " TEXT, " + Col4 + " TEXT );";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addTrackData(String track, String artist, String year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2, track);
        contentValues.put(Col3, artist);
        contentValues.put(Col4, year);
        db.insert(table_name, null, contentValues);
    }

    public Cursor getFavTrackData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_name + "", null);
        return cursor;
    }

    public void deleteData(String name) {
    SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Checking column",Col2);

       db.delete(table_name,Col2+ " = '" + name + "'",null);

    }
}
