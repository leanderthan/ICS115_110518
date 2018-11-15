package com.example.cisco.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBName = "student.db";
    final static int ver = 1;
    Cursor rs;
    final static String table = "grade";

    public DBHelper(Context context) {
        super(context, DBName, null, ver);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cTable = "CREATE TABLE grade (ID INTEGER PRIMARY KEY AUTOINCREMENT, Fname TEXT, Lname TEXT, LGrade INTEGER)";
        db.execSQL(cTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dTable = "DROP TABLE IF EXISTS grade";
        db.execSQL(dTable);
        onCreate(db);
    }

    public boolean insert(String fname, String lname, int lgrade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Fname", fname);
        cv.put("Lname", lname);
        cv.put("LGrade", lgrade);
        long inserted = db.insert(table, null, cv);
        if (inserted == -1){
            return false;
        } else return true;
    }

    public Cursor selectRecord(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM grade", null);
    }
}
