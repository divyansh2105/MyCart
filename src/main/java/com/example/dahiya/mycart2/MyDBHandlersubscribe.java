package com.example.dahiya.mycart2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandlersubscribe extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "subscribedatabase.db";
    public static final String TABLE_PRODUCTS = "subscribe";
    public static final String _ID = "_id";
    public static final String USERNAME = "username";
    public static final String COMPANY = "company";
    public static final String NEWPRODUCT = "newproduct";


    public MyDBHandlersubscribe(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

        //context.deleteDatabase(DATABASE_NAME);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                        //_ID + " INTEGER AUTOINCREMENT, " +
                        USERNAME + " TEXT, " +
                        COMPANY + " TEXT, " +
                        NEWPRODUCT + " INTEGER" +
                        ");";
        db.execSQL(query);
        // myfunc(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void subscribed(String username,String company){
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(COMPANY, company);
        values.put(NEWPRODUCT, 0);

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();

    }

    public void deleteSubscribe(String username, String company){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COMPANY + "=\"" + company + "\" AND "  + USERNAME + "=\""
                + username + "\";");
//        db.execSQL("DELETE FROM " + TABLE_PRODUCTS  + ";");

    }

    public void addSubscribe(String company)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_PRODUCTS + " SET " + NEWPRODUCT + "=1"   +
                " WHERE " + COMPANY + "=\"" + company  + "\";");

    }

    public void unsubscribed(String username, String company)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_PRODUCTS + " SET " + NEWPRODUCT + "=0"   +
                " WHERE " + COMPANY + "=\"" + company + "\" AND "  + USERNAME + "=\""
                + username + "\";");

    }

    public Cursor getcursorlogin(String username)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+ TABLE_PRODUCTS + " WHERE "+ USERNAME+"=\"" +username+ "\""
                + " AND " + NEWPRODUCT + "=1",null);
        return cur;
    }

    public Cursor getcursor()
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS,null);
        return cur;
    }




}
