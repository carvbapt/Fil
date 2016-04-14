package com.example.sauca.filgps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Splash extends AppCompatActivity implements View.OnClickListener {

    ImageButton ibSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ibSplash=(ImageButton)findViewById(R.id.IB_Splash);

        ibSplash.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,Main.class));
    }
}
