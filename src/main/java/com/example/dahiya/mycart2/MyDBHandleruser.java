package com.example.dahiya.mycart2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandleruser extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userdatabase.db";
    public static final String TABLE_PRODUCTS = "users";
    public static final String _ID = "_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";


    public MyDBHandleruser(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

        //context.deleteDatabase(DATABASE_NAME);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                        //_ID + " INTEGER AUTOINCREMENT, " +
                        USERNAME + " TEXT PRIMARY KEY, " +
                        PASSWORD + " TEXT, " +
                        EMAIL + " TEXT" +
                        ");";
        db.execSQL(query);
        // myfunc(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }


    public void addUser(Users user){
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(EMAIL, user.getEmail());
        values.put(PASSWORD, user.getPassword());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();

    }

    public void deleteProduct(String username){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + USERNAME + "=\"" + username + "\";");

    }





    public Cursor getcursor()
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+ TABLE_PRODUCTS,null);
        return cur;
    }

}
