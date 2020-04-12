package com.example.enmalleapp.login;
import com.example.enmalleapp.R;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistroLoginActivity extends AppCompatActivity {
    Button btnRLSalir, btnRLRegistrar;
    EditText etRLConfirmarPassword, etRLPassword, etRLConfirmarCorreo, etRLCorreo, etRLCedula;
    String verpassword, password, cedula, correo, vercorreo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_login);
        btnRLSalir=findViewById(R.id.btnRLSalir);
        btnRLRegistrar=findViewById(R.id.btnRLRegistrar);
        etRLCedula=findViewById(R.id.etRLCedula);
        etRLCorreo=findViewById(R.id.etRLCorreo);
        etRLConfirmarCorreo=findViewById(R.id.etRLConfirmarCorreo);
        etRLPassword=findViewById(R.id.etRLPassword);
        etRLConfirmarPassword=findViewById(R.id.etRLConfirmarPassword);
        btnRLRegistrar=findViewById(R.id.btnRLRegistrar);

        btnRLSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnRLRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verpassword=etRLConfirmarPassword.getText().toString();
                password=etRLPassword.getText().toString();
                cedula=etRLCedula.getText().toString();
                correo=etRLCorreo.getText().toString();
                vercorreo=etRLConfirmarCorreo.getText().toString();
                if (!verpassword.isEmpty() && !password.isEmpty() && !correo.isEmpty() && !cedula.isEmpty() && !vercorreo.isEmpty()){
                    if (!verpassword.equals(password)){
                        Toast.makeText(RegistroLoginActivity.this, "Verifique el PASSWORD no son iguales", Toast.LENGTH_SHORT).show();
                    }else{
                        if(!verpassword.equals(password)) {
                            Toast.makeText(RegistroLoginActivity.this, "Verifique el CORREO no son iguales", Toast.LENGTH_SHORT).show();
                        }else {
                            agregarRegistro();
                                etRLConfirmarCorreo.setText("");
                                etRLCorreo.setText("");
                                etRLCedula.setText("");
                                etRLConfirmarPassword.setText("");
                                etRLPassword.setText("");
                            finish();
                        }
                    }
                }else {
                    Toast.makeText(RegistroLoginActivity.this, "No se permite campos vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void agregarRegistro(){
        try {
            PreparedStatement pst = conexionBD().prepareStatement("sp_registro_login ?,?,?");
            // in
            pst.setString(1, etRLCedula.getText().toString());
            pst.setString(2, etRLCorreo.getText().toString());
            pst.setString(3, etRLPassword.getText().toString());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                //etRLCedula.setText(rs.getString(1)); //out
                Toast.makeText(getApplicationContext(), "REGISTRO SATISFACTORIO", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "ERROR: NO ES POSIBLE REGISTRARLE EN EL SISTEMA", Toast.LENGTH_SHORT).show();
            }
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public Connection conexionBD(){
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
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }

}

