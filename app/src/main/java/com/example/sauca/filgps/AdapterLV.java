package com.example.sauca.filgps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.example.sauca.filgps.DataLV;

/**
 * Created by Sauca on 17-04-2016.
 */
public class AdapterLV extends ArrayAdapter {

    private ArrayList list = new ArrayList();

    public AdapterLV(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        View row;
        row=convertView;
        DataHandler handler;

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row=inflater.inflate(R.layout.activity_main_row,parent,false);
            handler=new DataHandler();

            handler.aCliente=(TextView)row.findViewById(R.id.TV_Cliente);
            handler.aLatitude=(TextView)row.findViewById(R.id.TV_Lat);
            handler.aLongitude=(TextView)row.findViewById(R.id.TV_Long);
            row.setTag(handler);
        }else{
            handler=(DataHandler)row.getTag();
        }

        DataLV dataLV;
        dataLV=(DataLV)this.getItem(position);

        handler.aCliente.setText(dataLV.getdCliente());
        handler.aLatitude.setText(dataLV.getdLatitude());
        handler.aLongitude.setText(dataLV.getdLongitude());
        return row;
    }


    private class DataHandler {

        TextView aCliente;
        TextView aLatitude;
        TextView aLongitude;

    }
}
