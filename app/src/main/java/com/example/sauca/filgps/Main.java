package com.example.sauca.filgps;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Main extends AppCompatActivity implements View.OnClickListener{

    ImageButton ibBack,ibFOpen,ibFSave,ibMap,ibQuit;
    Button btQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibBack=(ImageButton)findViewById(R.id.IB_Back);
        ibFOpen=(ImageButton)findViewById(R.id.IB_FOpen);
        ibFSave=(ImageButton)findViewById(R.id.IB_FSave);
        ibMap=(ImageButton)findViewById(R.id.IB_Map);
        ibQuit=(ImageButton)findViewById(R.id.IB_Quit);

        ibBack.setOnClickListener(this);
        ibFOpen.setOnClickListener(this);
        ibFSave.setOnClickListener(this);
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
        }else if(v==findViewById(R.id.IB_Map)) {
            startActivity(new Intent(this, Maps.class));
        }else if(v==findViewById(R.id.IB_FOpen)) {
            order();
        }

    }

    private void order() {

        try{
            AssetManager am=getAssets();
            InputStream is=am.open("Gps.xls");
            Workbook wb=Workbook.getWorkbook(is);
            Sheet s=wb.getSheet(0);
            int row=s.getRows()-1;
            int col=s.getColumns();
//            Toast.makeText(this,"TESTE XLS",Toast.LENGTH_LONG).show();
            String str="Linhas - "+row+" Colunas - "+col+"\n";

            for(int r=0;r<row ; r++){
                for (int c=0;c<col;c++){
                    Cell v=s.getCell(c,r);

                    if(v.getContents()==null)
                        str=str+" "+"--c--";
                    else
                        str=str+" "+v.getContents();
                }
                str=str+"\n";
            }
            TextView tvTexto=(TextView)findViewById(R.id.TV_Texto);
            tvTexto.setText(str);
        }catch (Exception e){
            Toast.makeText(this,"ERRO - "+e,Toast.LENGTH_LONG).show();
        }
    }
}
