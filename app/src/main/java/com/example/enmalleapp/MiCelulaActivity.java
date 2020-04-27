package com.example.enmalleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    String trama = "";
    String VariableControl = "";
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_celula);

        toolbar=findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        btnSalir = findViewById(R.id.btnSalir);
        btnNuevoCreyente = findViewById(R.id.btnNuevoCreyente);
        recyclerViewCelula = findViewById(R.id.recyclerCelula);
        recyclerViewCelula.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        btnListarCreyentes = findViewById(R.id.btnListarCreyentes);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setEnabled(false);
        btnListarCreyentes.setEnabled(true);

        btnListarCreyentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGuardar.setEnabled(true);
                btnListarCreyentes.setEnabled(false);

                 validarRegistroAsistenciaCelula();

                if (VariableControl == "NO EXISTE"){
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
                btnGuardar.setEnabled(false);
                btnListarCreyentes.setEnabled(false);
                    //1.- genera la trama con los asistentes
                guardarAsistencia();
                    //2.- envia la trama a la base para que se procese en el Sp
                registrarAsistenciaCelula();
                    //3.- borro el recyclerview;
                borrarData();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.MenuNuevo:
                Toast.makeText(getApplicationContext(), "Menu Nuevo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.MenuBuscar:
                Toast.makeText(getApplicationContext(), "Menu Buscar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.MenuGuardar:
                Toast.makeText(getApplicationContext(), "Menu Guardar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.MenuSalir:
                Toast.makeText(getApplicationContext(), "Menu Salir", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void removeItem(int position) {
        listaDatos.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public String guardarAsistencia(){
        trama = "";
        claseGlobal objLectura = (claseGlobal)getApplicationContext();
        trama = trama + objLectura.getIdLider() + ";";
        trama = trama + objLectura.getIdCelula() + ":";
        for(int i = 0; i < listaDatos.size() ; i++){
            Creyente s = listaDatos.get(i);
            if(i==listaDatos.size()-1){
             trama = trama + s.getidCreyente()+",";
            }else{
             trama = trama + s.getidCreyente()+",";
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
                while (rs.next()){
                Creyente creyente = new Creyente();
                creyente.setNombreApellido(rs.getString(1));
                creyente.setFechaNacimiento(rs.getString(2));
                creyente.setidCreyente(rs.getString(3));
                listaDatos.add(creyente);
                }
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            btnGuardar.setClickable(false);
            btnListarCreyentes.setClickable(true);
        }
    }

    public void registrarAsistenciaCelula() {
        try {
            PreparedStatement pst = cc.stConsulta("exec sp_registro_asistencia_celula ?");
            pst.setString(1, trama);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Toast.makeText(getApplicationContext(), "REGISTRO SATISFACTORIO", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MiCelulaActivity.this, "ERROR EN INSERCION", Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {

            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void validarRegistroAsistenciaCelula() {
        try {
            String idLider;
            String idCelula;
            claseGlobal objLectura = (claseGlobal)getApplicationContext();
            idLider =  objLectura.getIdLider();
            idCelula =  objLectura.getIdCelula();
            PreparedStatement pst = cc.stConsulta("exec sp_validacion_registro_asistencia_celula ?,?");
            pst.setString(1, idLider);
            pst.setString(2, idCelula);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Toast.makeText(getApplicationContext(), "YA EXISTE UN REGISTRO DE ASISTENCIA EN ESTA SEMANA", Toast.LENGTH_SHORT).show();
                btnGuardar.setEnabled(false);
                btnListarCreyentes.setEnabled(false);
                VariableControl="EXISTE";
            } else {
                //Si no existe ningun registro registrado para la semana genera el listado
                 VariableControl="NO EXISTE";
            }
        } catch (SQLException e) {

            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void borrarData() {
        listaDatos.clear(); //Borras la data con la que llenas el recyclerview
        adapter.notifyDataSetChanged(); //le notifico al adaptador que no hay nada para llenar la vista
    }

}
