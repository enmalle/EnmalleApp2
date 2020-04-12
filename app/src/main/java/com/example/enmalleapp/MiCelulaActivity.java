package com.example.enmalleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.example.enmalleapp.adaptadores.AdaptadorRecyclerCelula;
import com.example.enmalleapp.conexion.ConnectionClass;
import com.example.enmalleapp.login.LoginClass;
import com.example.enmalleapp.modelos.Creyente;


public class MiCelulaActivity extends AppCompatActivity {
    ConnectionClass cc = new ConnectionClass();
    LoginClass lc = new LoginClass();
    Date fecha = new Date();
    Button btnSalir, btnNuevoCreyente, btnListarCreyentes, btnGuardar;
    RecyclerView recyclerViewCelula;
    ArrayList <Creyente>  listaDatos ;
    AdaptadorRecyclerCelula adapter;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_celula);

        btnSalir = findViewById(R.id.btnSalir);
        btnNuevoCreyente = findViewById(R.id.btnNuevoCreyente);
        recyclerViewCelula = findViewById(R.id.recyclerCelula);
        recyclerViewCelula.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        btnListarCreyentes = findViewById(R.id.btnListarCreyentes);
        btnGuardar = findViewById(R.id.btnGuardar);



        btnListarCreyentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llenarListaCreyentes();
                adapter = new AdaptadorRecyclerCelula(listaDatos);
                recyclerViewCelula.setAdapter(adapter);

                adapter.setOnItemClickListener(new AdaptadorRecyclerCelula.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @Override
                    public void onDeleteClick(int position) {
                        removeItem(position);
                    }
                });

            }
        });



        btnNuevoCreyente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegistroCreyenteActivity.class);
                startActivity(intent);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MiCelulaActivity.this, guardarAsistencia(), Toast.LENGTH_LONG).show();
            }
        });


        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void removeItem(int position) {
        listaDatos.remove(position);
        adapter.notifyItemRemoved(position);
    }




    public String guardarAsistencia(){
        String trama = "";
        claseGlobal objLectura = (claseGlobal)getApplicationContext();
        trama = trama + objLectura.getIdLider() + ";";
        for(int i = 0; i < listaDatos.size() ; i++){
            Creyente s = listaDatos.get(i);
            if(i==listaDatos.size()-1){
             //trama = trama + s.getidCreyente();
            }else{
                //trama = trama + s.getidCreyente()+",";
            }
        }

        return trama;
    }

    public void llenarListaCreyentes(){
        try {
            String parametro1;
            claseGlobal objLectura = (claseGlobal)getApplicationContext();
            parametro1 = objLectura.getIdLider();
            PreparedStatement pst = cc.stConsulta("exec sp_listado_celula ?");
            //variable Global del id lider
            pst.setString(1, parametro1);
            ResultSet rs = pst.executeQuery();
            listaDatos = new ArrayList<Creyente>();
//            if(rs.next()){
                while (rs.next()){
                Creyente creyente = new Creyente();
                creyente.setNombreApellido(rs.getString(1));
                creyente.setFechaNacimiento(rs.getString(2));
                creyente.setidCreyente(rs.getString(3));
                listaDatos.add(creyente);}
//            }else{
//                Toast.makeText(MiCelulaActivity.this, "Base de datos error", Toast.LENGTH_LONG).show();
//            }
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}
