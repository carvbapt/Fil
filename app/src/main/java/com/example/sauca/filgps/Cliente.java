package com.example.sauca.filgps;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

public class Cliente extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ibBack;
    private Bundle bundle;

    private TextView tvInd;
    private EditText edCliente,edNif,edMorada,edContato,edLatitude,edLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        Bundle intt=getIntent().getExtras();

//        Toast.makeText(this,""+intt.getInt("EXTRA_POSITION"),Toast.LENGTH_LONG).show();

        tvInd=(TextView)findViewById(R.id.TV_Cli);

        edCliente=(EditText)findViewById(R.id.ET_Cliente);
        edNif=(EditText)findViewById(R.id.ET_Nif);
        edContato=(EditText)findViewById(R.id.ET_Contato);
        edMorada=(EditText)findViewById(R.id.ET_Morada);
        edLatitude=(EditText)findViewById(R.id.ET_Latitude);
        edLongitude=(EditText)findViewById(R.id.ET_Longitude);

        if(intt.getInt("EXTRA_POSITION")>=0) {
            posData(intt.getInt("EXTRA_POSITION"));
            tvInd.setText("Linha - "+(intt.getInt("EXTRA_POSITION")+2));
        }else
            tvInd.setText("NOVO");

        ibBack=(ImageButton)findViewById(R.id.IB_Back);
        ibBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==findViewById(R.id.IB_Back)) {
            startActivity(new Intent(this,Main.class));
        }
    }

    private void posData(int ind) {

        try {
            AssetManager am = getAssets();
            InputStream is = am.open("Gps.xls");
            Workbook wb = Workbook.getWorkbook(is);
            final Sheet s = wb.getSheet(0);

            edCliente.setText(s.getCell(0, ind).getContents());
            edLatitude.setText(s.getCell(4, ind).getContents());
            edLongitude.setText(s.getCell(5, ind).getContents());

//            Toast.makeText(this,"TESTE XLS",Toast.LENGTH_LONG).show();


        } catch (Exception e) {
            Toast.makeText(this, "ERRO - " + e, Toast.LENGTH_LONG).show();
        }
    }
}
