package com.example.enmalleapp.conexion;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionClass {
    String url ;
    String driver ;
    String userName ;
    String password ;

    public ConnectionClass() {
        this.url = "jdbc:jtds:sqlserver://208.118.63.123:1433;DatabaseName=DB_A56303_enmalle";
        this.driver = "net.sourceforge.jtds.jdbc.Driver";
        this.userName = "DB_A56303_enmalle_admin";
        this.password = "johannsa2521";
    }

    private Connection conexionBD(){
        Connection conexion = null;
        String url = "jdbc:jtds:sqlserver://208.118.63.123:1433;DatabaseName=DB_A56303_enmalle";
        String driver = "net.sourceforge.jtds.jdbc.Driver";
        String userName = "DB_A56303_enmalle_admin";
        String password = "johannsa2521";
        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(url,userName,password);

        }catch (Exception e){
            //Toast.makeText(getClass(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }

    public PreparedStatement stConsulta(String consulta){
        PreparedStatement st = null;
        try {
            st = conexionBD().prepareStatement(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }
}



