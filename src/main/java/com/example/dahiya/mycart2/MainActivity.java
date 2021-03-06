package com.example.dahiya.mycart2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessionManager=new SessionManager(getApplicationContext());
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Home");
        Home fragment= new Home();
        android.support.v4.app.FragmentTransaction fragmenttransaction=getSupportFragmentManager().beginTransaction();
        fragmenttransaction.replace(R.id.content_frame, fragment, "Home");
        fragmenttransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        if(id==R.id.cart)
        {
            Intent i=new Intent(getApplicationContext(),Cart.class);
            startActivity(i);
            return true;
        }
        if(id==R.id.search)
        {
            Intent i=new Intent(getApplicationContext(),Search.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {
            setTitle("Home");
            Home fragment= new Home();
            android.support.v4.app.FragmentTransaction fragmenttransaction=getSupportFragmentManager().beginTransaction();
            fragmenttransaction.replace(R.id.content_frame, fragment, "Home");
            fragmenttransaction.commit();
        } else if (id == R.id.nav_mobile) {
            setTitle("Mobiles");
            Product_mobile fragment= new Product_mobile();
            android.support.v4.app.FragmentTransaction fragmenttransaction=getSupportFragmentManager().beginTransaction();
            fragmenttransaction.replace(R.id.content_frame, fragment, "Mobiles");
            fragmenttransaction.commit();
        } else if (id == R.id.nav_tablet) {
            setTitle("Tablets");
            Product_tablet fragment= new Product_tablet();
            android.support.v4.app.FragmentTransaction fragmenttransaction=getSupportFragmentManager().beginTransaction();
            fragmenttransaction.replace(R.id.content_frame, fragment, "Tablets");
            fragmenttransaction.commit();
        } else if (id == R.id.nav_account) {
            setTitle("Home");
            if (sessionManager.isLoggedIn() == false) {
                Intent i = new Intent(this, Login.class);
                startActivity(i);
            } else {
                Intent i = new Intent(this, Profile.class);
                startActivity(i);
            }
        }
//            My_account fragment= new My_account();
//            android.support.v4.app.FragmentTransaction fragmenttransaction=getSupportFragmentManager().beginTransaction();
//            fragmenttransaction.replace(R.id.content_frame, fragment, "My Account");
//            fragmenttransaction.commit();

        else if (id == R.id.nav_Logout) {
            setTitle("Logout");
            Logoutnew fragment= new Logoutnew();
            android.support.v4.app.FragmentTransaction fragmenttransaction=getSupportFragmentManager().beginTransaction();
            fragmenttransaction.replace(R.id.content_frame, fragment, "Logout");
            fragmenttransaction.commit();}
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
