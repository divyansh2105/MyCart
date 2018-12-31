package com.example.dahiya.mycart2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Add_remove extends AppCompatActivity {


    View view;
    EditText company, model, price, ram, storage, screen, battery, front_camera, rear_camera,company_delete,model_delete,type;

    MyDBHandler dbHandler;
    Button addbutton, removebutton, button3;
    private String _companys;
    private String _models;
    private String _prices;
    private String _ratings;
    private String _rams;
    private String _storages;
    private String _screens;
    private String _batterys;
    private String _front_cameras;
    private String _rear_cameras;
    private String _types;

    MyDBHandlersubscribe myDBHandlersubscribe;
    SessionManager sessionManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_remove);

        company = (EditText) findViewById(R.id.company);
        model = (EditText) findViewById(R.id.model);
        price = (EditText) findViewById(R.id.price);
        ram = (EditText) findViewById(R.id.ram);
        storage = (EditText) findViewById(R.id.storage);
        battery = (EditText) findViewById(R.id.battery);
        front_camera = (EditText) findViewById(R.id.front_camera);
        rear_camera = (EditText) findViewById(R.id.rear_camera);
        screen = (EditText) findViewById(R.id.screen);
        company_delete = (EditText) findViewById(R.id.company_delete);
        model_delete = (EditText) findViewById(R.id.model_delete);
        type=(EditText) findViewById(R.id.type);

        addbutton = (Button) findViewById(R.id.add_button);
        removebutton = (Button) findViewById(R.id.delete_button);



        dbHandler = new MyDBHandler(this,null,null,1);

        sessionManager=new SessionManager(getApplicationContext());
        myDBHandlersubscribe=new MyDBHandlersubscribe(this,null,null,1);
    }





    public void addbuttonclicked(View view)
                {

                   // Log.d("mytag","it is printing2");


                    _companys=company.getText().toString();_rams=ram.getText().toString();_batterys= battery.getText().toString();
                    _models=model.getText().toString();_storages=storage.getText().toString();_front_cameras=front_camera.getText().toString();
                    _prices=price.getText().toString();_screens=screen.getText().toString();_rear_cameras=rear_camera.getText().toString();
                    _types=type.getText().toString();
                    int flag=0;
                    if(_companys.matches("")){
                        company.setError("The item can't be empty");flag=1;}
                    if(_models.matches("")){
                        model.setError("The item can't be empty");flag=1;}
                    if(_prices.matches("")){
                        price.setError("The item can't be empty");_prices="0";flag=1;}
                    if(_rams.matches("")){
                        ram.setError("The item can't be empty");_rams="0";flag=1;}
                    if(_storages.matches("")){
                        storage.setError("The item can't be empty");_storages="0";flag=1;}
                    if(_screens.matches("")){
                        screen.setError("The item can't be empty");_screens="0";flag=1;}
                    if(_batterys.matches("")){
                        battery.setError("The item can't be empty");_batterys="0";flag=1;}
                    if(_front_cameras.matches("")){
                        front_camera.setError("The item can't be empty");_front_cameras="0";flag=1;}
                    if(_rear_cameras.matches("")){
                        rear_camera.setError("The item can't be empty");_rear_cameras="0";flag=1;}
                    if(_types.matches("")){
                        type.setError("The item can't be empty");flag=1;}
                    if(flag==1)
                        return;

                    Products product = new Products(
                            _companys, _models, _prices,
                            _rams,_storages,_screens,
                            _batterys,_front_cameras,_rear_cameras,_types
                    );
                    dbHandler.addProduct(product);

                    Toast.makeText(getApplicationContext(), "Added to database",
                           Toast.LENGTH_LONG).show();
                    company.setText("");model.setText("");price.setText("");storage.setText("");screen.setText("");
                    battery.setText("");ram.setText("");front_camera.setText("");rear_camera.setText("");type.setText("");

                        myDBHandlersubscribe.addSubscribe(_companys);


                   displaycursor();
                }




        public void removebuttonclicked(View view){
            String text1 = company_delete.getText().toString();
            String text2 = model_delete.getText().toString();
            dbHandler.deleteProduct(text1,text2);

            //printDatabase();
            Toast.makeText(getApplicationContext(), "Removed from database",
                    Toast.LENGTH_LONG).show();
            displaycursor();
        }

        public void displaycursor() {

            Cursor data = dbHandler.getcursor();
            if (data.getCount() == 0) {
                display("error","no data found");
            }
            StringBuffer buffer = new StringBuffer();
            while (data.moveToNext()) {
                buffer.append(data.getString(0) + "\n" + data.getString(1) + "\n" +
                        data.getString(2) + "\n" + data.getString(3) + "\n" + data.getString(4) + "\n" +
                        data.getString(5) + "\n" + data.getString(6) + "\n" + data.getString(7) + "\n" +
                data.getString(8) + "\n" + data.getString(9) + "\n" + data.getString(10) + "\n"
                        + data.getString(11) + "\n" + data.getString(12)
                        );
                display("data stored",buffer.toString());
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
}
