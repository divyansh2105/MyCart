package com.example.dahiya.mycart2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class Logoutnew extends Fragment {
    SessionManager sessionManager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logoutnew, container, false);

        sessionManager = new SessionManager(getActivity());
        if (sessionManager.isLoggedIn() == false) {
            display("Not logged In", "Login in first");

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                public void run() {

                    Intent i = new Intent(getActivity(), Login.class);
                    startActivity(i);
                }

            }, 2000);
        } else {
            display("Logging out", "");

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                public void run() {
                    sessionManager.logoutUser();

                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                }

            }, 2000);
        }
        return view;
    }
    public void display(String title, String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
