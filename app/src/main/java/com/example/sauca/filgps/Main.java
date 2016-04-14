package com.example.sauca.filgps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Main extends AppCompatActivity implements View.OnClickListener{

    ImageButton ibBack,ibMap,ibQuit;
    Button btQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibBack=(ImageButton)findViewById(R.id.IB_Back);
        ibMap=(ImageButton)findViewById(R.id.IB_Map);
        ibQuit=(ImageButton)findViewById(R.id.IB_Quit);

        ibBack.setOnClickListener(this);
        ibMap.setOnClickListener(this);
        ibQuit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v == findViewById(R.id.IB_Back)){
            finish();
            startActivity(new Intent(this, Splash.class));
        }else if(v==findViewById(R.id.IB_Quit)){
            moveTaskToBack(true);
            finish();
            System.exit(0);
        }else if(v==findViewById(R.id.IB_Map))
            startActivity(new Intent(this,Maps.class));
    }
}
