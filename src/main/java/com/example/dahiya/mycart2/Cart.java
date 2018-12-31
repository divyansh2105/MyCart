package com.example.dahiya.mycart2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Cart extends Activity {

    SessionManager sessionManager;
    Context context;
    TextView cart1_c,cart2_c,cart3_c,cart4_c,cart5_c,cart1_m,cart2_m,cart3_m,cart4_m,cart5_m;
    Button button1,button2,button3,button4,button5;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

         cart1_c = (TextView) findViewById(R.id.cart1_c);
         cart1_m = (TextView) findViewById(R.id.cart1_m);
          cart2_c = (TextView) findViewById(R.id.cart2_c);
          cart2_m = (TextView) findViewById(R.id.cart2_m);
          cart3_c = (TextView) findViewById(R.id.cart3_c);
            cart3_m = (TextView) findViewById(R.id.cart3_m);
          cart4_c = (TextView) findViewById(R.id.cart4_c);
          cart4_m = (TextView) findViewById(R.id.cart4_m);
          cart5_c = (TextView) findViewById(R.id.cart5_c);
          cart5_m = (TextView) findViewById(R.id.cart5_m);

        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);


        sessionManager = new SessionManager(getApplicationContext());


        if(cart1_c!=null) {
            button1.setVisibility(View.VISIBLE);
            cart1_c.setText(sessionManager.getcart1_c());
            cart1_m.setText(sessionManager.getcart1_m());
        }
        if(cart2_c!=null) {
            button2.setVisibility(View.VISIBLE);
            cart2_c.setText(sessionManager.getcart2_c());
            cart2_m.setText(sessionManager.getcart2_m());
        }
        if(cart3_c!=null) {
            button3.setVisibility(View.VISIBLE);
            cart3_c.setText(sessionManager.getcart3_c());
            cart3_m.setText(sessionManager.getcart3_m());
        }
        if(cart4_c!=null) {
            button4.setVisibility(View.VISIBLE);
            cart4_c.setText(sessionManager.getcart4_c());
            cart4_m.setText(sessionManager.getcart4_m());
        }
        if(cart5_c!=null) {
            button5.setVisibility(View.VISIBLE);
            cart5_c.setText(sessionManager.getcart5_c());
            cart5_m.setText(sessionManager.getcart5_m());
        }
    }

    public void button1clicked(View view)
    {
        if(sessionManager.getcart1_c()==null)
        {
            Toast.makeText(getApplicationContext(),"Nothing presesnt",Toast.LENGTH_LONG).show();return;
        }

        sessionManager.setcart1_c();sessionManager.setcart1_m();
        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
    public void button2clicked(View view)
    {
        if(sessionManager.getcart2_c()==null)
        {
            Toast.makeText(getApplicationContext(),"Nothing presesnt",Toast.LENGTH_LONG).show();return;
        }
        sessionManager.setcart2_c();sessionManager.setcart2_m();
        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
    public void button3clicked(View view)
    {
        if(sessionManager.getcart3_c()==null)
        {
            Toast.makeText(getApplicationContext(),"Nothing presesnt",Toast.LENGTH_LONG).show();return;
        }
        sessionManager.setcart3_c();sessionManager.setcart3_m();
        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
    public void button4clicked(View view)
    {
        if(sessionManager.getcart4_c()==null)
        {
            Toast.makeText(getApplicationContext(),"Nothing presesnt",Toast.LENGTH_LONG).show();return;
        }
        sessionManager.setcart4_c();sessionManager.setcart4_m();
        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
    public void button5clicked(View view)
    {
        if(sessionManager.getcart5_c()==null)
        {
            Toast.makeText(getApplicationContext(),"Nothing presesnt",Toast.LENGTH_LONG).show();return;
        }
        sessionManager.setcart5_c();sessionManager.setcart5_m();
        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}