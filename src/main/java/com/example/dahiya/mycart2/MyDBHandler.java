package com.example.dahiya.mycart2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productdatabase.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String _ID = "_id";
    public static final String COMPANY = "company";
    public static final String MODEL = "model";
    public static final String PRICE = "price";
    public static final String RATING = "rating";
    public static final String RAM = "ram";
    public static final String STORAGE = "storage";
    public static final String SCREEN = "screen";
    public static final String BATTERY = "battery";
    public static final String FRONT_CAMERA = "front_camera";
    public static final String REAR_CAMERA = "rear_camera";
    public static final String TYPE = "type";
    public static final String RATES = "rates";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

//    public class Structure
//    {
//        String scompany, smodel, sprice, srating, sram, sstorage, sscreen, sbattery, sfront_camera, srear_camera;
//    };


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                 "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COMPANY + " TEXT, " +
                MODEL + " TEXT, " +
                PRICE + " FLOAT(8), " +
                RATING + " FLOAT(8), " +
                RAM + " INTEGER, " +
                STORAGE + " INTEGER, " +
                SCREEN + " FLOAT(8), " +
                BATTERY + " INTEGER, " +
                FRONT_CAMERA + " INTEGER, " +
                REAR_CAMERA + " INTEGER, " +
                TYPE + " TEXT, " +
                RATES + " INTEGER" +
                ");";
        db.execSQL(query);
       // myfunc(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(Products product){
        ContentValues values = new ContentValues();
        values.put(COMPANY, product.get_company());
        values.put(MODEL, product.get_model());
        values.put(PRICE, product.get_price());
        values.put(RATING, 0);
        values.put(RAM, product.get_ram());
        values.put(STORAGE, product.get_storage());
        values.put(SCREEN, product.get_screen());
        values.put(BATTERY, product.get_battery());
        values.put(FRONT_CAMERA, product.get_front_camera());
        values.put(REAR_CAMERA, product.get_rear_camera());
        values.put(TYPE, product.get_type());
        values.put(RATES, 0);
        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();

    }

    public void deleteProduct(String company, String model){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COMPANY + "=\"" + company + "\" AND "  + MODEL + "=\""
                + model + "\";");
    }

    public Cursor getcursor()
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+ TABLE_PRODUCTS,null);
        return cur;
    }

    public Cursor getcursormobile()
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+ TABLE_PRODUCTS + " WHERE TYPE=\"Mobile\"",null);
        return cur;
    }

    public Cursor getcursortablet()
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+ TABLE_PRODUCTS + " WHERE TYPE=\"Tablet\"",null);
        return cur;
    }
    public Cursor getcursorforvalue(String field, String value)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + field + "=\"" + value+"\"",null);
        return cur;
    }
    public Cursor getcursorforrange(String field,String low, String high)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + field + " BETWEEN \"" + low+"\" AND " + "\"" + high + "\"",null);
        return cur;
    }
    public Cursor getcursorcompanymodel(String company,String model)
    {
        SQLiteDatabase db=getWritableDatabase();
       Cursor cur= db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COMPANY + "=\"" + company + "\" AND "  + MODEL + "=\""
                + model+"\"",null );
        return cur;
    }
    public void updaterating(String company,String model,String rating,String rates)
    {
        SQLiteDatabase db = getWritableDatabase();
        if(rating==null)
        {
            rating="0";
        }
//        db.execSQL("UPDATE " + TABLE_PRODUCTS + " SET " + RATING + "=\"0"   + "\", " + RATES + "=\"" + "0" + "\" " +
//                " WHERE " + COMPANY + "=\"" + "Xiaomi" + "\" AND "  + MODEL + "=\""
//                + "Redmi 4" + "\";");
        db.execSQL("UPDATE " + TABLE_PRODUCTS + " SET " + RATING + "=\"" + rating + "\", " + RATES + "=\"" + rates + "\" " +
                " WHERE " + COMPANY + "=\"" + company + "\" AND "  + MODEL + "=\""
                + model + "\";");
    }

}
