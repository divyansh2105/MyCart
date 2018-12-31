package com.example.dahiya.mycart2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Login extends AppCompatActivity {

    View view;
    EditText username,email,password;
    MyDBHandleruser db;
    Button loginbutton;
    public String _usernamestr;
    public String _passwordstr;
    public  String _emailstr;
    MyDBHandlersubscribe ds;

    NotificationCompat.Builder notification;
    private static int uniqueID=0;

    public SessionManager sessionManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sessionManager=new SessionManager(getApplicationContext());
        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);


        loginbutton = (Button) findViewById(R.id.loginbutton);


        db = new MyDBHandleruser(this,null,null,1);
        ds = new MyDBHandlersubscribe(this,null,null,1);

        notification = new NotificationCompat.Builder(this,"M_CH_ID");
        notification.setAutoCancel(true);

    }

    public void loginbuttonclicked(View view)
    {

        _usernamestr=username.getText().toString();
        _emailstr=getemailfromdatabase(_usernamestr);
        _passwordstr=password.getText().toString();

        sessionManager.createLoginSession(_usernamestr,_passwordstr,_emailstr);

//        Toast.makeText(getApplicationContext(), sessionManager.getUserUsername()+" "+sessionManager.getUserPassword()+" "+
//                        sessionManager.getUserEmail(),
//                Toast.LENGTH_LONG).show();
        confirmlogin(_usernamestr,_passwordstr);
    }

    public void confirmlogin(String _username, String _password)
    {

        int flag=1;
        Cursor data = db.getcursor();


        if (data.getCount() == 0) {
            display("error database empty","Sign up first");

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                public void run() {

                    Intent i = new Intent(Login.this, Signin.class);
                    startActivity(i);
                }

            }, 2000);
        }
        else {
            while (data.moveToNext()) {
                    if (_username.matches("admin") && _password.matches("admin")) {
                    display("Welcome Admin", " ");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        public void run() {

                            Intent i = new Intent(Login.this, Add_remove.class);

                            startActivity(i);
                        }

                    }, 2000);
                    flag = 1;
                    break;
                }
               else if (data.getString(0).matches(_username) && data.getString(1).matches(_password)) {
                    display("Logged In Successfully ", "as " + _username);
                    flag = 1;
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        public void run() {

                            Intent i = new Intent(Login.this, MainActivity.class);

                            startActivity(i);
                        }

                    }, 2000);

                    Cursor data2=ds.getcursorlogin(_username);
                    while(data2.moveToNext())
                    {
                        uniqueID++;
                        if(data2.getString(0).matches(_username) && data2.getString(2).matches("1"))
                        {


                            notification.setSmallIcon(R.drawable.ic_launcher_background);
                            notification.setTicker("This is the ticker");
                            notification.setWhen(System.currentTimeMillis());
                            notification.setContentTitle(data2.getString(1)+ " has added a new product");
                            notification.setContentText("Check out exciting new products now");

                            Intent intent = new Intent(this, MainActivity.class);
                            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            notification.setContentIntent(pendingIntent);

                            //Builds notification and issues it
                            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            nm.notify(uniqueID, notification.build());


                            ds.unsubscribed(_username,data2.getString(1));
                        }
                    }

                    break;
                } else if (data.getString(0).matches(_username) && !data.getString(1).matches(_password)) {
                    password.setError("The password is incorrect");
                    flag = 1;
                    break;
                }

                else {
                    flag = 0;
                }
            }
            if (flag == 0) {
                display("error", "Sign up first ");
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {

                    public void run() {

                        Intent i = new Intent(Login.this, Signin.class);

                        startActivity(i);
                    }

                }, 2000);
            }
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

    public String getemailfromdatabase(String _usernamestr) {

        Cursor data = db.getcursor();
        String retstr="";
        while (data.moveToNext()) {
           if(_usernamestr.matches(data.getString(0)))
           {
                retstr=data.getString(2);
                break;
           }
        }
        return retstr;
    }

}
