package com.example.sauca.filgps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Main extends AppCompatActivity implements View.OnClickListener{

    ImageButton ibBack,ibFOpen,ibFAdd,ibMap,ibQuit;
    Intent intt;

    ListView lvMain;
    AdapterLV adapterLV;

    private AlertDialog alerta;
    public Long[][] coord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibBack=(ImageButton)findViewById(R.id.IB_Back);
        ibMap=(ImageButton)findViewById(R.id.IB_Map);
        ibQuit=(ImageButton)findViewById(R.id.IB_Quit);

        ibFOpen=(ImageButton)findViewById(R.id.IB_FOpen);
        ibFAdd=(ImageButton)findViewById(R.id.IB_FAdd);

        lvMain=(ListView)findViewById(R.id.LV_Main);

        ibBack.setOnClickListener(this);
        ibMap.setOnClickListener(this);
        ibQuit.setOnClickListener(this);

        ibFOpen.setOnClickListener(this);
        ibFAdd.setOnClickListener(this);

        order();
    }

    @Override
    public void onClick(View v) {

        if (v == findViewById(R.id.IB_Back)){
            finish();
            startActivity(new Intent(this, Splash.class));
        }else if(v==findViewById(R.id.IB_Map)) {
            startActivity(new Intent(this, Maps.class));
        }else if(v==findViewById(R.id.IB_Quit)){
            moveTaskToBack(true);
            finish();
            System.exit(0);
        }else if(v==findViewById(R.id.IB_FAdd)) {
            intt = new Intent(getBaseContext(), Cliente.class);
            int ind=-1;
            intt.putExtra("EXTRA_POSITION",ind);
            startActivity(intt);
        }else if(v==findViewById(R.id.IB_FOpen)) {
            dlgSimple();
            //order();
        }
    }

    private void order() {

        try{
            AssetManager am=getAssets();
            InputStream is=am.open("Gps.xls");
            Workbook wb=Workbook.getWorkbook(is);
            final Sheet s=wb.getSheet(0);
            int row=s.getRows();
            int col=s.getColumns();
            coord= new Long[2][row];

//            Toast.makeText(this,"TESTE XLS",Toast.LENGTH_LONG).show();

            String str="Destinos "+(row-1);
            TextView tvTexto=(TextView)findViewById(R.id.TV_Texto);
            tvTexto.setText(str);

            adapterLV=new AdapterLV(getApplicationContext(),R.layout.activity_main_row);
            lvMain.setAdapter(adapterLV);

            for(int r=1;r<row ; r++){
                  DataLV dtlv= new DataLV();
                  dtlv.setdCliente(s.getCell(0,r).getContents());
                  dtlv.setdLatitude(s.getCell(4,r).getContents());
                  dtlv.setdLongitude(s.getCell(5,r).getContents());
                  adapterLV.add(dtlv);
            }

            lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos=position+1;
//                    Toast.makeText(getBaseContext(),"Linha "+pos+"\nCL-"+s.getCell(0,pos).getContents()+" Lat-"+s.getCell(4,pos).getContents()+" Lon-"+s.getCell(5,pos).getContents(),Toast.LENGTH_LONG).show();
                    intt = new Intent(getBaseContext(), Cliente.class);
                    intt.putExtra("EXTRA_POSITION",position);
                    startActivity(intt);
                }
            });

        }catch (Exception e){
            Toast.makeText(this,"ERRO - "+e,Toast.LENGTH_LONG).show();
        }
    }

    private void dlgSimple() {

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // define o titulo
        builder.setTitle("Titulo");
        // define a mensagem
        builder.setMessage("Qualifique este software");
        // define um botão como positivo
        builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface arg0, int arg1) {
         Toast.makeText(getBaseContext(), "positivo=" + arg1, Toast.LENGTH_SHORT).show(); } });

        // define um botão como negativo.
        builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface arg0, int arg1) {
         Toast.makeText(getBaseContext(), "negativo=" + arg1, Toast.LENGTH_SHORT).show(); } });
        // cria o AlertDialog
         alerta = builder.create();
        // Exibe
        alerta.show();
    }
}
