package com.example.dahiya.mycart2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.String.format;

public class Fullscreen extends AppCompatActivity {
String companyk,modelk;
RatingBar ratingBar;
    MyDBHandler db;
    Button button,subscribebutton;
    TextView ratingtextview,textView14;
    Cursor cur,cur2,cur3;
    String ratingnew,ratesnew,ratinginput,ratingold,ratesold,ratingnewest;
    int ratesint;
    float ratingint,ratinginputint;
    MyDBHandlersubscribe ds;

    Button rate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen);

        db=new MyDBHandler(getApplicationContext(),null,null,1);
        ds=new MyDBHandlersubscribe(getApplicationContext(),null,null,1);


        TextView company=(TextView)findViewById(R.id.company);
        TextView model=(TextView)findViewById(R.id.model);
        ImageView imageView=(ImageView) findViewById(R.id.imageView);
        ImageButton imageButton=(ImageButton) findViewById(R.id.imageButton);
        subscribebutton=(Button)findViewById(R.id.subscribe);
        textView14=(TextView) findViewById(R.id.textView14);

        Intent i = getIntent();
         companyk = i.getStringExtra("companyk");
         modelk = i.getStringExtra("modelk");

         textView14.setText("Subscribe to "+ companyk);

        String newmodelstr="";
        String newcompanystr="";
        for(int j=0;j<companyk.length();j++)
        {
            if(companyk.charAt(j)!=' ')
            {
                if(companyk.charAt(j)>=65 && companyk.charAt(j)<=90) {
                    int x=companyk.charAt(j)-65;
                    x=97+x;
                    newcompanystr = newcompanystr + (char)x;
                }
                else{
                    newcompanystr=newcompanystr + companyk.charAt(j);
                }

            }
        }
        for(int j=0;j<modelk.length();j++)
        {
            if(modelk.charAt(j)!=' ')
            {
                if(modelk.charAt(j)>=65 && modelk.charAt(j)<=90) {
                    int x=modelk.charAt(j)-65;
                    x=97+x;
                    newmodelstr = newmodelstr + (char)x;
                }
                else{
                    newmodelstr=newmodelstr + modelk.charAt(j);
                }

            }
        }


        String imgname="drawable/"+newcompanystr+newmodelstr;

       //String imgname = "drawable/"+"xiaomiredmi4";

        if(getResources().getIdentifier(imgname, "drawable", getPackageName())==0)
        {
            imgname="drawable/xiaomiredmi4";
        }

        int imageResource = getResources().getIdentifier(imgname, null, getPackageName());


        Drawable image = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(image);


        TextView price=(TextView)findViewById(R.id.price);
        TextView ram=(TextView)findViewById(R.id.ram);
        TextView storage=(TextView)findViewById(R.id.storage);
        TextView battery=(TextView)findViewById(R.id.battery);
        TextView screen=(TextView)findViewById(R.id.screen);
        TextView front_camera=(TextView)findViewById(R.id.front_camera);
        TextView rear_camera=(TextView)findViewById(R.id.rear_camera);
       // TextView rating=(TextView)findViewById(R.id.rating);


        String ramk = i.getStringExtra("ramk");
        String storagek = i.getStringExtra("storagek");
        String pricek = i.getStringExtra("pricek");
        String batteryk = i.getStringExtra("batteryk");
        String screenk = i.getStringExtra("screenk");
        String front_camerak = i.getStringExtra("front_camerak");
        String rear_camerak = i.getStringExtra("rear_camerak");
       // String ratingk = i.getStringExtra("ratingk");

        company.setText(companyk);
        model.setText(modelk);

        cur=db.getcursorcompanymodel(companyk,modelk);
//        getCompanyModelinput();


        price.setText("Rs. "+pricek);
        ram.setText(ramk+" GB");
        storage.setText(storagek+" GB");
        battery.setText(batteryk+" mAh");
        screen.setText(screenk+" inch");
        front_camera.setText(front_camerak+" MP");
        rear_camera.setText(rear_camerak+" MP");
        //rating.setText(ratingk);

        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
         rate=(Button)findViewById(R.id.rate);
         rate.setVisibility(View.VISIBLE);
        ratingBar.setVisibility(View.INVISIBLE);

        SessionManager sessionManager=new SessionManager(getApplicationContext());
        if(sessionManager.isLoggedIn()==true) {
            cur3 = ds.getcursor();
            cur3.moveToFirst();
            while (cur3.moveToNext()) {
                if (cur3.getString(1).matches(companyk) && cur3.getString(0).matches(sessionManager.getUserUsername())) {
                    subscribebutton.setText("Unsubscribe");
                    textView14.setText("Unsubscibe to " + companyk);
                }
            }
        }
    }

    public void rateclicked(View view)
    {
        SessionManager sessionManager=new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn() == false) {
            Toast.makeText(getApplicationContext(), "Not Logged In",
                    Toast.LENGTH_LONG).show();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                public void run() {

                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }

            }, 1000);
        }
        else {
            if(ratingBar.getVisibility()==View.INVISIBLE)
            {
                ratingBar.setVisibility(View.VISIBLE);
                rate.setText("SUBMIT");
                rate.setWidth(100);
                return;
            }
            else if(ratingBar.getVisibility()==View.VISIBLE) {
                 ratinginput = String.valueOf(ratingBar.getRating());
                //Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
                cur.moveToFirst();
                ratingold=cur.getString(4);
                ratesold=cur.getString(12);
              //  ratingtextview.setText(ratingold);
                ratingint=Float.valueOf(ratingold);
                ratesint=Integer.parseInt(ratesold);
                ratingint=ratingint*ratesint;
                ratesint+=1;
                ratinginputint=Float.valueOf(ratinginput);
                ratingint+=ratinginputint;
                ratingint=ratingint/ratesint;
               ratingnew =String.format("%.1f", ratingint);
                //ratingnew=String.valueOf(ratingint);
                ratesnew=String.valueOf(ratesint);
                db.updaterating(companyk,modelk,ratingnew,ratesnew);
                cur2=db.getcursorcompanymodel(companyk,modelk);
                newgetCompanyModelinput();
                cur.close();
                rate.setVisibility(View.INVISIBLE);
            }
        }

    }
    public void imagebuttonclicked(View view)
    {
        SessionManager sessionManager=new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn() == false) {
            Toast.makeText(getApplicationContext(), "Not Logged In",
                    Toast.LENGTH_LONG).show();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                public void run() {

                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }

            }, 1000);
        }
            else
            {
                int r = sessionManager.addcart(companyk, modelk);


                if (r == 0)
                    Toast.makeText(getApplicationContext(), "Already present in cart", Toast.LENGTH_LONG).show();
                else if (r == 1) {
                    //display("Cart is full ", "delete items to create space");
                    Toast.makeText(getApplicationContext(), "Cart is full", Toast.LENGTH_LONG).show();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        public void run() {

                            Intent i = new Intent(Fullscreen.this, Cart.class);

                            startActivity(i);
                        }

                    }, 2000);
                } else if ((r == 2)) {
                    Intent i = new Intent(this, Cart.class);
                    startActivity(i);
                }
            }
    }



    public void newgetCompanyModelinput()
    {
        cur2.moveToFirst();
        ratingnewest=cur2.getString(4);
        ratingtextview=(TextView)findViewById(R.id.ratingtextview);
        ratingtextview.setText(ratingnewest);
        cur2.close();
    }

    public void subscribeclicked(View view)
    {
        SessionManager sessionManager=new SessionManager(getApplicationContext());

        if (sessionManager.isLoggedIn() == false) {
            Toast.makeText(getApplicationContext(), "Not Logged In",
                    Toast.LENGTH_LONG).show();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                public void run() {

                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }

            }, 1000);
        }
        else {
            if (subscribebutton.getText().toString().matches("Subscribe")) {
                String arr1 = sessionManager.getUserUsername();
                String arr2 = companyk;
                ds.subscribed(arr1, arr2);
                Toast.makeText(getApplicationContext(), "Subscribed",
                        Toast.LENGTH_LONG).show();
                subscribebutton.setText("Unsubscribe");
                textView14.setText("Unsubscibe to "+companyk);
            }
            else if(subscribebutton.getText().toString().matches("Unsubscribe"))
            {
                String arr1 = sessionManager.getUserUsername();
                String arr2 = companyk;
                ds.deleteSubscribe(arr1, arr2);
                Toast.makeText(getApplicationContext(), "Unsubscribed",
                        Toast.LENGTH_LONG).show();
                subscribebutton.setText("Subscribe");
                textView14.setText("Subscribe to "+companyk);
            }
        }
    }

    private void display(String title, String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
