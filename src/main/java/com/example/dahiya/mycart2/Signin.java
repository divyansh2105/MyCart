package com.example.dahiya.mycart2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Signin extends AppCompatActivity {


    View view;
    EditText username,email,password,confirm_password,username_delete;

    MyDBHandleruser db;
    Button signinbutton,deletebutton;
    private String _username;
    private String _password;
    private String _confirm_password;
    private  String _email;
    private  String _username_delete;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);

        email=(EditText) findViewById(R.id.email);
        username_delete=(EditText) findViewById(R.id.username_delete);

        signinbutton = (Button) findViewById(R.id.signinbutton);
        deletebutton = (Button) findViewById(R.id.deletebutton);


        db = new MyDBHandleruser(this,null,null,1);
    }

    public void signinbuttonclicked(View view)
    {

        // Log.d("mytag","it is printing2");


        _username=username.getText().toString();_password=password.getText().toString();
        _confirm_password=confirm_password.getText().toString();_email=email.getText().toString();
        _username_delete=username_delete.getText().toString();
        int flag=0;
        if(_username.matches("")){
            username.setError("The item can't be empty");flag=1;}
        if(_password.matches("")){
            password.setError("The item can't be empty");flag=1;}
        if(_confirm_password.matches("")){
            confirm_password.setError("The item can't be empty");flag=1;}
        if(_email.matches("")){
            email.setError("The item can't be empty");flag=1;}
        if(!(_confirm_password.matches(_password))){
            confirm_password.setError("Confirm password should be equal to password");flag=1;}
        if(usernameused(_username)==true){
            username.setError("Username already taken");flag=1;}
        if(flag==1)
            return;

        Users user = new Users(
                _username, _email, _password
        );
        db.addUser(user);

        display("Signed In Successfully ",  _username);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {

                Intent i = new Intent(Signin.this, Login.class);

                startActivity(i);
            }

        }, 2000);
        username.setText("");password.setText("");confirm_password.setText("");email.setText("");

        //displaycursor();
    }

    public void deletebuttonclicked(View view){
        db.deleteProduct(_username_delete);

        //printDatabase();
        Toast.makeText(getApplicationContext(), "Removed"+_username_delete+" from database",
                Toast.LENGTH_LONG).show();
        displaycursor();
    }

    public boolean usernameused(String u)
    {
        Cursor data = db.getcursor();
        if (data.getCount() == 0) {
           return false;
        }
        StringBuffer buffer = new StringBuffer();
        while (data.moveToNext()) {
            if(data.getString(0).matches(u))
                return true;

        }
        return false;
    }

    public void displaycursor() {

        Cursor data = db.getcursor();
        if (data.getCount() == 0) {
            display("error","no data found");
        }
        StringBuffer buffer = new StringBuffer();
        while (data.moveToNext()) {
            buffer.append(data.getString(0) + "\n" + data.getString(1) + "\n" +
                    data.getString(2) + "\n"
            );
            display("data stored",buffer.toString());
        }
    }

    public void display(String title, String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}
