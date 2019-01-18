package com.example.akazad.sqliteproject1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDBhelper extends SQLiteOpenHelper {

    //database create
    private static final String DB_NAME="student_db";
    private  static final int DB_VERSION=7;
    private  static  final  String TABLE_NAME="student_info";

    private  static final String TBL_ID="_id";
    private  static  final  String TBL_COL_NAME="name";
    private  static  final  String TBL_COL_AGE="age";
    private  static  final  String TBL_COL_MALE="male";

    private  static  final  String DROP_TABLE=" DROP TABLE IF exists "+ TABLE_NAME;
    private  static  final  String SELECT_TABLE=" SELECT * FROM "+ TABLE_NAME;
    //table create


    private  static final String DB_TABLE_CREATE=
            " CREATE TABLE " + TABLE_NAME +
                    "(" + TBL_ID + " INTEGER PRIMARY KEY, "
                        + TBL_COL_NAME + " TEXT, "
                        + TBL_COL_AGE + " TEXT, "
                        + TBL_COL_MALE + " TEXT )";

    private Context context;

    public MyDBhelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    try {
        Toast.makeText(context, "Database Create successfully", Toast.LENGTH_LONG).show();
        db.execSQL(DB_TABLE_CREATE);
    }catch (Exception e){
        Toast.makeText(context, "Exception"+e, Toast.LENGTH_LONG).show();

    }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context, "Database Upgreate successfully", Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);

        }catch (Exception e){

            Toast.makeText(context, "Exception"+e, Toast.LENGTH_LONG).show();
        }

    }

    //insert data
    public  long  insertData(String name, String age, String gender){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TBL_COL_NAME, name);
        contentValues.put(TBL_COL_AGE, age);
        contentValues.put(TBL_COL_MALE, gender);

        long rowId=sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return  rowId;

    }

 public Cursor displayAllData(){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
       Cursor cursor =sqLiteDatabase.rawQuery(SELECT_TABLE, null);
        return cursor;

    }


    public  boolean updateData(String name, String age, String gender, String id){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TBL_COL_NAME, name);
        contentValues.put(TBL_COL_AGE, age);
        contentValues.put(TBL_COL_MALE, gender);
        contentValues.put(TBL_ID, id);

        sqLiteDatabase.update(TABLE_NAME, contentValues, TBL_ID+"=?", new String[]{id});

        return true;
    }

    public Integer deletedata(String id){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, TBL_ID+"=?", new String[]{id});



    }
}
