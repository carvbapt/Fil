package com.example.sauca.filgps;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;

public class Cliente extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ibBack, ibClear, ibSave;

    private TextView tvInd;
    private EditText edCliente, edNif, edMorada, edContato, edLatitude, edLongitude;
    private Bundle intt;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        intt = getIntent().getExtras();

//        Toast.makeText(this,""+intt.getInt("EXTRA_POSITION"),Toast.LENGTH_LONG).show();

        tvInd = (TextView) findViewById(R.id.TV_Cli);

        edCliente = (EditText) findViewById(R.id.ET_Cliente);
        edNif = (EditText) findViewById(R.id.ET_Nif);
        edContato = (EditText) findViewById(R.id.ET_Contato);
        edMorada = (EditText) findViewById(R.id.ET_Morada);
        edLatitude = (EditText) findViewById(R.id.ET_Latitude);
        edLongitude = (EditText) findViewById(R.id.ET_Longitude);

        if (intt.getInt("EXTRA_POSITION") >= 0) {
            posData(intt.getInt("EXTRA_POSITION"));
            if (intt.getInt("EXTRA_POSITION") == 0)
                tvInd.setText("Linha - " + (intt.getInt("EXTRA_POSITION") + 2));
            else
                tvInd.setText("Linha - " + (intt.getInt("EXTRA_POSITION") + 1));
        } else
            tvInd.setText("NOVO");

        ibBack = (ImageButton) findViewById(R.id.IB_Back);
        ibClear=(ImageButton)findViewById(R.id.IB_FClear);
        ibSave =(ImageButton)findViewById(R.id.IB_FSave);

        ibBack.setOnClickListener(this);
        ibClear.setOnClickListener(this);
        ibSave.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.IB_Back)) {
            startActivity(new Intent(this, Main.class));
        }else if (v == findViewById(R.id.IB_FClear)) {
            clearData();
        } else if (v == findViewById(R.id.IB_FSave)) {
            writeData(intt.getInt("EXTRA_POSITION"));
        }
    }

    private void clearData() {
        edCliente.setText("");
        edNif.setText("");
        edContato.setText("");
        edMorada.setText("");
        edLatitude.setText("");
        edLongitude.setText("");
        Toast.makeText(this,"Dados apagados",Toast.LENGTH_LONG).show();
    }

    private void writeData(int ind) {

        try {
            AssetManager am = getAssets();
            InputStream is = am.open("Gps.xls");
            Workbook wb = Workbook.getWorkbook(is);
            WritableSheet s = (WritableSheet) wb.getSheet(0);

            s.getWritableCell(0, ind).getContents().replaceAll(s.getCell(0, ind).getContents(),edCliente.getText().toString());
            s.getWritableCell(1, ind).getContents().replaceAll(s.getCell(0, ind).getContents(),edNif.getText().toString());
            s.getWritableCell(2, ind).getContents().replaceAll(s.getCell(0, ind).getContents(),edContato.getText().toString());
            s.getWritableCell(3, ind).getContents().replaceAll(s.getCell(0, ind).getContents(),edMorada.getText().toString());
            s.getWritableCell(4, ind).getContents().replaceAll(s.getCell(0, ind).getContents(),edLatitude.getText().toString());
            s.getWritableCell(5, ind).getContents().replaceAll(s.getCell(0, ind).getContents(),edLongitude.getText().toString());
            Toast.makeText(this,"TESTE XLS",Toast.LENGTH_LONG).show();

            wb.close();

        } catch (Exception e) {
            Toast.makeText(this, "ERRO - " + e, Toast.LENGTH_LONG).show();
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Cliente Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.sauca.filgps/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Cliente Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.sauca.filgps/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
