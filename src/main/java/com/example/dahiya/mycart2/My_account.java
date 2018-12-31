package com.example.dahiya.mycart2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class My_account extends Fragment implements View.OnClickListener {


View view;

    public My_account() {
        // Required empty public constructor
    }
Button button,button2,button4;
SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view=inflater.inflate(R.layout.fragment_my_account, container, false);
//
//        button=(Button) view.findViewById(R.id.add_remove);
//        button2=(Button) view.findViewById(R.id.signin);
//        button4=(Button) view.findViewById(R.id.login);

        sessionManager=new SessionManager(getContext());
        if(sessionManager.isLoggedIn()==false)
        {
            Intent i=new Intent(getActivity(),Login.class);
            startActivity(i);
        }
        else
        {
            Intent i=new Intent(getActivity(),Profile.class);
            startActivity(i);
        }

//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent i=new Intent(getActivity(),Add_remove.class);
//                startActivity(i);            }
//        });
//
//        button2.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent i=new Intent(getActivity(),Signin.class);
//                startActivity(i);            }
//        });
//        button4.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent i=new Intent(getActivity(),Login.class);
//                startActivity(i);            }
//        });

        return view;
    }

    @Override
    public void onClick(View v) {

    }

//    public void onClick(View v) {
//        Intent i=new Intent(getActivity(),Add_remove.class);
//        startActivity(i);
//
//    }
//
//    public void onClick(View view)
//    {
//        Intent i=new Intent(getActivity(),Fullscreen.class);
//        startActivity(i);
//    }

}
