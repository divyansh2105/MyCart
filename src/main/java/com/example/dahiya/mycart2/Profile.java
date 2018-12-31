package com.example.dahiya.mycart2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    View view;
    //Context context;
    TextView username,password,email;
    SessionManager sessionManager;
    String arr1,arr2,arr3;
    Button b;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        username=(TextView) findViewById(R.id.username);
        password=(TextView) findViewById(R.id.password);
        email=(TextView) findViewById(R.id.email);
        b=(Button)findViewById(R.id.add_remove);
        b.setVisibility(View.INVISIBLE);

        sessionManager=new SessionManager(getApplicationContext());

        arr1=sessionManager.getUserUsername();
        arr2=sessionManager.getUserPassword();
        arr3=sessionManager.getUserEmail();

        username.setText(arr1);
        password.setText(arr2);
        email.setText(arr3);

        if(arr1.matches("admin") && arr2.matches("admin"))
        {
            b.setVisibility(View.VISIBLE);
        }
    }
    public void func(View view)
    {
        if(arr1.matches("admin") && arr2.matches("admin"))
        {
            Intent i = new Intent(Profile.this, Add_remove.class);

            startActivity(i);
        }



    }
}
