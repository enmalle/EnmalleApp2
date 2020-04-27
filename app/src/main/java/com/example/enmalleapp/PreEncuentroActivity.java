package com.example.enmalleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.example.enmalleapp.adaptadores.AdaptadorRecyclerPre;
import com.example.enmalleapp.conexion.ConnectionClass;
import com.example.enmalleapp.login.LoginClass;
import com.example.enmalleapp.modelos.CreyentePre;

public class PreEncuentroActivity extends AppCompatActivity {
    private Toolbar toolbar;

    ConnectionClass cc = new ConnectionClass();
    LoginClass lc = new LoginClass();
    Date fecha = new Date();
    RecyclerView recyclerViewPre;
    ArrayList <CreyentePre> listaDatosPre;
    AdaptadorRecyclerPre adapter;
    private Toast mToast;
    String trama = "";
    String VariableControl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_encuentro);

        toolbar=findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        recyclerViewPre = findViewById(R.id.recyclerPre);
        recyclerViewPre.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menue,menu); //Se cambia aqui "menue"
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.MenuBuscare:
                //validarRegistroAsistenciaCelula();
                VariableControl="NO EXISTE"; //borrar si se habilita la linea anterior
                if (VariableControl == "NO EXISTE"){
                    menuItem.setEnabled(false);
                    llenarListaCandidatos();
                    adapter = new AdaptadorRecyclerPre(listaDatosPre);
                    recyclerViewPre.setAdapter(adapter);
                    adapter.setOnItemClickListener(new AdaptadorRecyclerPre.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                        }
                        @Override
                        public void onDeleteClick(int position) {
                            removeItem(position);
                        }
                    });
                }
                break;
            case R.id.MenuGuardare:
                if (VariableControl == "NO EXISTE")
                {
                guardarCandidatos();
                registrarCandidatosPreEncuentro();
                borrarData();
                menuItem.setEnabled(false);
                }else
                {
                    Toast.makeText(getApplicationContext(), "NO EXISTEN DATOS A GUARDAR", Toast.LENGTH_SHORT).show();
                    menuItem.setEnabled(false);
                }
                break;
            case R.id.MenuSalire:
                finish();
                break;
        }
        return true;
    }

    public void removeItem(int position) {
        listaDatosPre.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public String guardarCandidatos(){
        trama = "";
        claseGlobal objLectura = (claseGlobal)getApplicationContext();
        trama = trama + objLectura.getIdLider() + ";";
        trama = trama + objLectura.getIdCelula() + ":";
        for(int i = 0; i < listaDatosPre.size() ; i++){
            CreyentePre s = listaDatosPre.get(i);
            if(i==listaDatosPre.size()-1){
                trama = trama + s.getIdCreyentePre()+",";
            }else{
                trama = trama + s.getIdCreyentePre()+",";
            }
        }
        return trama;
    }

    public void llenarListaCandidatos(){
        try {
            String parametro1;
            claseGlobal objLectura = (claseGlobal)getApplicationContext();
            parametro1 = objLectura.getIdLider();
            PreparedStatement pst = cc.stConsulta("exec sp_consulta_candidatos_pre_encuentro ?");
            //variable Global del id lider
            pst.setString(1, parametro1);
            ResultSet rs = pst.executeQuery();
            listaDatosPre = new ArrayList<CreyentePre>();
            while (rs.next()){
                CreyentePre creyentepre = new CreyentePre();
                creyentepre.setIdCreyentePre(rs.getString(1));
                creyentepre.setNombresPre(rs.getString(2));
                creyentepre.setApellidosPre(rs.getString(3));
                creyentepre.setEdadPre(rs.getString(4));

                listaDatosPre.add(creyentepre);
            }
            if (listaDatosPre.size()==0){
                VariableControl="EXISTE"; //borrar si se habilita la linea anterior
            }
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void registrarCandidatosPreEncuentro() {
        try {
            PreparedStatement pst = cc.stConsulta("exec sp_registro_candidatos_pre_encuentro ?");
            pst.setString(1, trama);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Toast.makeText(getApplicationContext(), "REGISTRO SATISFACTORIO", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PreEncuentroActivity.this, "ERROR EN INSERCION", Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
/*
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
                VariableControl="EXISTE";
            } else {
                VariableControl="NO EXISTE";
            }
        } catch (SQLException e) {

            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
*/
    public void borrarData() {
        listaDatosPre.clear(); //Borras la data con la que llenas el recyclerview
        adapter.notifyDataSetChanged(); //le notifico al adaptador que no hay nada para llenar la vista
    }


}