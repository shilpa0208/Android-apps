package com.example.user.a1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declare the buttons
    protected Button chicago=null;
    protected Button indianapolis =null;

    //Executed when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //obtain references of the buttons
        chicago=(Button)findViewById(R.id.cButton);
        indianapolis =(Button)findViewById(R.id.indiButton);

        //set listener to perform action when the respective button is clicked
        chicago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"In a moment you will be shown the places of interest in Chicago",Toast.LENGTH_LONG).show();
                Intent chicagoIntent=new Intent("ChicagoMessage");
                sendBroadcast(chicagoIntent);
            }
        });

        indianapolis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "In a moment you will be shown the places of interest in Indianapolis", Toast.LENGTH_LONG).show();
                Intent indiIntent = new Intent("IndiMessage");
                sendBroadcast(indiIntent);
            }
        });


    }
}
