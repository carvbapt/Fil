package com.example.sauca.filgps;

/**
 * Created by Sauca on 17-04-2016.
 */
public class DataLV {

    private String dCliente;
    private String dLatitude;
    private String dLongitude;


    public DataLV() {
    }

    public DataLV(String dCliente, String dLatitude, String dLongitude) {
        this.dCliente = dCliente;
        this.dLatitude = dLatitude;
        this.dLongitude = dLongitude;
    }

    public String getdCliente() {
        return dCliente;
    }

    public void setdCliente(String dCliente) {
        this.dCliente = dCliente;
    }

    public String getdLatitude() {
        return dLatitude;
    }

    public void setdLatitude(String dLatitude) {
        this.dLatitude = dLatitude;
    }

    public String getdLongitude() {
        return dLongitude;
    }

    public void setdLongitude(String dLongitude) {
        this.dLongitude = dLongitude;
    }
}
