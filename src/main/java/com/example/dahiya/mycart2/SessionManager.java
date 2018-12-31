package com.example.dahiya.mycart2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_USERNAME = "";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "";

    public static final String KEY_PASSWORD = "";

    // Constructor

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String username, String password, String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString("KEY_USERNAME", username);
        editor.putString("KEY_PASSWORD", password);

        // Storing email in pref
        editor.putString("KEY_EMAIL", email);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }



    /**
     * Get stored session data
     * */
    public String getUserUsername()
    {
        return pref.getString("KEY_USERNAME", null);
    }
    public String getUserPassword()
    {
        return pref.getString("KEY_PASSWORD", null);
    }
    public String getUserEmail()
    {
        return pref.getString("KEY_EMAIL", null);
    }

    public String[] getUserDetails(){
        String arr[]=new String[3];
        // user name
        arr[0]=pref.getString("KEY_USERNAME", null);

        // user email id
        arr[1]=pref.getString("KEY_PASSWORD", null);
        arr[2]=pref.getString("KEY_EMAIL", null);

        // return user
        return arr;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public int addcart(String company, String model)
    {

        if(pref.getString("cart1_c", null)==null){
        editor.putString("cart1_c", company);editor.putString("cart1_m",model);
        }
        else
        {
            if((company.matches(pref.getString("cart1_c", null)) && model.matches(pref.getString("cart1_m", null))))
            {
                return 0;
            }
            if(pref.getString("cart2_c", null)==null)
            {editor.putString("cart2_c", company);editor.putString("cart2_m",model);}
                else
                {
                    if((company.matches(pref.getString("cart2_c", null)) && model.matches(pref.getString("cart2_m", null))))
                    {
                        return 0;
                    }
                if(pref.getString("cart3_c", null)==null)
                {editor.putString("cart3_c", company);editor.putString("cart3_m",model);}
                    else
                    {
                        if((company.matches(pref.getString("cart3_c", null)) && model.matches(pref.getString("cart3_m", null))))
                        {
                            return 0;
                        }
                        if(pref.getString("cart4_c", null)==null)
                        {editor.putString("cart4_c", company);editor.putString("cart4_m",model);}
                        else
                        {
                            if((company.matches(pref.getString("cart4_c", null)) && model.matches(pref.getString("cart4_m", null))))
                            {
                                return 0;
                            }
                            if(pref.getString("cart5_c", null)==null)
                            {editor.putString("cart5_c", company);editor.putString("cart5_m",model);}
                            else
                            {
                                if((company.matches(pref.getString("cart5_c", null)) && model.matches(pref.getString("cart5_m", null))))
                                {
                                    return 0;
                                }
                                return 1;

                            }
                        }
                    }
                }

        }


        editor.commit();
        return 2;
    }
    public String getcart1_c()
    {
        return pref.getString("cart1_c", null);
    }
    public String getcart1_m()
    {
        return pref.getString("cart1_m", null);
    }
    public String getcart2_c()
    {
        return pref.getString("cart2_c", null);
    }
    public String getcart2_m()
    {
        return pref.getString("cart2_m", null);
    }
    public String getcart3_c()
    {
        return pref.getString("cart3_c", null);
    }
    public String getcart3_m()
    {
        return pref.getString("cart3_m", null);
    }
    public String getcart4_c()
    {
        return pref.getString("cart4_c", null);
    }
    public String getcart4_m()
    {
        return pref.getString("cart4_m", null);
    }
    public String getcart5_c()
    {
        return pref.getString("cart5_c", null);
    }
    public String getcart5_m()
    {
        return pref.getString("cart5_m", null);
    }


    public void setcart1_c()
    {
         editor.putString("cart1_c",null);editor.commit();
    }
    public void setcart2_c()
    {
        editor.putString("cart2_c",null);editor.commit();
    }
    public void setcart3_c()
    {
        editor.putString("cart3_c",null);editor.commit();
    }
    public void setcart4_c()
    {
        editor.putString("cart4_c",null);editor.commit();
    }
    public void setcart5_c()
    {
        editor.putString("cart5_c",null);editor.commit();
    }
    public void setcart1_m()
    {
        editor.putString("cart1_m",null);editor.commit();
    }
    public void setcart2_m()
    {
        editor.putString("cart2_m",null);editor.commit();
    }
    public void setcart3_m()
    {
        editor.putString("cart3_m",null);editor.commit();
    }
    public void setcart4_m()
    {
        editor.putString("cart4_m",null);editor.commit();
    }
    public void setcart5_m()
    {
        editor.putString("cart5_m",null);editor.commit();
    }



}