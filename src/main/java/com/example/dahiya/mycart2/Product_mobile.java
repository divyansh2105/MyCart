package com.example.dahiya.mycart2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Product_mobile extends Fragment implements View.OnClickListener{


    public Product_mobile() {
        // Required empty public constructor
    }

    SessionManager sessionManager;
    String arr;
    MyDBHandler db;
    Button button;
    TextView t;
    Cursor cur;
    int n;
    public Products p[];

    ListView ls;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db=new MyDBHandler(getActivity(),null,null,1);
        cur=db.getcursormobile();
        n=cur.getCount();
        p=new Products[n];
        for(int j=0;j<n;j++)
        {
            p[j]=new Products();
        }
        getinput();

        sessionManager=new SessionManager(getContext());

        arr=sessionManager.getUserUsername();


        View view=inflater.inflate(R.layout.fragment_product_mobile, container, false);
        TextView username= view.findViewById(R.id.username);
        username.setText(arr);
        ls=view.findViewById(R.id.myListviewmobile);
        Customadapter customadapter2=new Customadapter(getActivity(),p);
        ls.setAdapter(customadapter2);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity().getBaseContext(),
                        Fullscreen.class);

                i.putExtra("companyk",p[position].get_companys());
                i.putExtra("modelk",p[position].get_models());
                i.putExtra("pricek",p[position].get_prices());
                i.putExtra("ramk",p[position].get_rams());
                i.putExtra("storagek",p[position].get_storages());
                i.putExtra("batteryk",p[position].get_batterys());
                i.putExtra("screenk",p[position].get_screens());
                i.putExtra("front_camerak",p[position].get_front_cameras());
                i.putExtra("rear_camerak",p[position].get_rear_cameras());
                i.putExtra("ratingk",p[position].get_ratings());

                getActivity().startActivity(i);            }
        });

        return view;
    }






    public void getinput()
    {
        int i=0;

        while(cur.moveToNext()) {

            p[i].set_companys(cur.getString(1));
            p[i].set_models(cur.getString(2));
            p[i].set_prices(cur.getString(3));
            p[i].set_ratings(cur.getString(4));
            p[i].set_rams(cur.getString(5));
            p[i].set_storages(cur.getString(6));
            p[i].set_screens(cur.getString(7));
            p[i].set_batterys(cur.getString(8));
            p[i].set_front_cameras(cur.getString(9));
            p[i].set_rear_cameras(cur.getString(10));
            p[i].set_types(cur.getString(11));
            p[i].set_ratess(cur.getString(12));
            ++i;
        }
    }


    @Override
    public void onClick(View v) {
        Intent i=new Intent(getActivity(),Add_remove.class);
        startActivity(i);
    }
}
