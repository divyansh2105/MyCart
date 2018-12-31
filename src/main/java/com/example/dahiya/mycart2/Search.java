package com.example.dahiya.mycart2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends Activity implements
        AdapterView.OnItemSelectedListener {

    View view;
    RadioGroup choice;
    Button okbutton,valuebutton,rangebutton;
    RadioButton choiceselected;
    EditText value,low,high;
    Spinner spin,spinvalue;
    String[] country = { "price", "screen", "battery", "ram", "storage","front_camera","rear_camera"};
    String[] country2 = { "company","model","price","rating","screen", "battery", "ram", "storage","front_camera","rear_camera"};
    String fields,values,highs,lows,fieldsr;
    ListView ls;
    public Products p[];
    MyDBHandler db;
    Cursor cur;
    int n;
    Customadapter customadapter;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);


        choice = (RadioGroup) findViewById(R.id.choice);
        okbutton = (Button) findViewById(R.id.okbutton);
        valuebutton = (Button) findViewById(R.id.valuebutton);
        rangebutton = (Button) findViewById(R.id.rangebutton);


        value=(EditText) findViewById(R.id.value);
        low=(EditText) findViewById(R.id.low);
        high=(EditText) findViewById(R.id.high);

        values=value.getText().toString();

        db=new MyDBHandler(getApplicationContext(),null,null,1);



         spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        spinvalue = (Spinner) findViewById(R.id.spinnervalue);
        spinvalue.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country2);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinvalue.setAdapter(aa2);

        low.setVisibility(View.INVISIBLE);
        high.setVisibility(View.INVISIBLE);
        spin.setVisibility(View.INVISIBLE);
        rangebutton.setVisibility(View.INVISIBLE);
        spinvalue.setVisibility(View.INVISIBLE);
        value.setVisibility(View.INVISIBLE);
        valuebutton.setVisibility(View.INVISIBLE);
    }


    public void okbuttonclicked(View view)
    {
        int selected=choice.getCheckedRadioButtonId();
        choiceselected=(RadioButton)findViewById(selected);
        String str=choiceselected.getText().toString();
        if(str.matches("Search By Value"))
        {
            low.setVisibility(View.INVISIBLE);
            high.setVisibility(View.INVISIBLE);
            spin.setVisibility(View.INVISIBLE);
            rangebutton.setVisibility(View.INVISIBLE);
            value.setVisibility(View.VISIBLE);
            valuebutton.setVisibility(View.VISIBLE);
            spinvalue.setVisibility(View.VISIBLE);


            values=value.getText().toString();
        }
        else
            if(str.matches("Search By Range"))
        {
            value.setVisibility(View.INVISIBLE);
            spinvalue.setVisibility(View.INVISIBLE);
            valuebutton.setVisibility(View.INVISIBLE);
            low.setVisibility(View.VISIBLE);
            high.setVisibility(View.VISIBLE);
            spin.setVisibility(View.VISIBLE);
            rangebutton.setVisibility(View.VISIBLE);

            spin = (Spinner) findViewById(R.id.spinner);
            spin.setOnItemSelectedListener(this);

            //Creating the ArrayAdapter instance having the country list
            ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            spin.setAdapter(aa);


        }

    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
        if(arg0.getId()==R.id.spinner)
        {
            fieldsr=country[position];
        }
        else if(arg0.getId()==R.id.spinnervalue)
        {
            fields=country2[position];

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void valuebuttonclicked(View view)
    {

        values=value.getText().toString();


        cur=db.getcursorforvalue(fields,values);
        n=cur.getCount();
        p=new Products[n];
        for(int j=0;j<n;j++)
        {
            p[j]=new Products();
        }
        getinput();

            ls= findViewById(R.id.myListview);
            customadapter=new Customadapter(getApplicationContext(),p);
            ls.setAdapter(customadapter);
//        t1.setText(p[0].get_companys());
//        t2.setText(p[0].get_models());


            ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getActivity(),p[position].get_companys(),Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Search.this,
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

                    startActivity(i);
                }
            });

    }

    public void rangebuttonclicked(View view)
    {
        lows=low.getText().toString();
        highs=high.getText().toString();

        cur=db.getcursorforrange(fieldsr,lows,highs);
        n=cur.getCount();
        p=new Products[n];
        for(int j=0;j<n;j++)
        {
            p[j]=new Products();
        }
        getinput();

        ls= findViewById(R.id.myListview);
        customadapter=new Customadapter(getApplicationContext(),p);
        ls.setAdapter(customadapter);
//        t1.setText(p[0].get_companys());
//        t2.setText(p[0].get_models());

            ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getActivity(),p[position].get_companys(),Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Search.this,
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

                    startActivity(i);
                }
            });




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

}
