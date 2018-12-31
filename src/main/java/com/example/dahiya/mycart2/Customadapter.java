package com.example.dahiya.mycart2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Customadapter extends ArrayAdapter<Products> {


    Context context;
    Products[] p;
    LayoutInflater inflater;
    public Customadapter(@NonNull Context context, @NonNull Products[] p) {
        super(context, R.layout.custom_row, p);

        this.context=context;
        this.p=p;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.custom_row,null);

        TextView company=(TextView)convertView.findViewById(R.id.company);
        TextView model=(TextView)convertView.findViewById(R.id.model);
        TextView ram=(TextView)convertView.findViewById(R.id.price);
        TextView storage=(TextView)convertView.findViewById(R.id.storage);
        TextView rating=(TextView)convertView.findViewById(R.id.rating);
        TextView battery=(TextView)convertView.findViewById(R.id.battery);
        TextView screen=(TextView)convertView.findViewById(R.id.screen);


        String companyk=p[position].get_companys();
        String modelk=p[position].get_models();
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

        if(context.getResources().getIdentifier(imgname, "drawable", context.getPackageName())==0)
        {
            imgname="drawable/xiaomiredmi4";
        }

        int imageResource = context.getResources().getIdentifier(imgname, null, context.getPackageName());


        Drawable image = context.getResources().getDrawable(imageResource);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView7);

        imageView.setImageDrawable(image);




//        int img=R.drawable.xiaomiredmi4;
//        ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView7);

        company.setText(p[position].get_companys());
        model.setText(p[position].get_models());
        ram.setText(p[position].get_rams()+" GB RAM");
        storage.setText(p[position].get_storages()+ " GB Storage");
        rating.setText(p[position].get_ratings());
        battery.setText(p[position].get_batterys()+" mAh Battery");
        screen.setText(p[position].get_screens()+" inch screen");
        //imageView.setImageResource(imgname);


        return convertView;
    }
}
