package com.example.enmalleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    Button bntCelula, btnPreEncuentro, btnEncuentro, btnPosEncuentro, btnEscuela;
    SeekBar seekbarSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bntCelula=findViewById(R.id.btnCelula);
        btnPreEncuentro=findViewById(R.id.btnPreEncuentro);
        btnEncuentro=findViewById(R.id.btnEncuentro);
        btnPosEncuentro=findViewById(R.id.btnPosEncuentro);
        btnEscuela=findViewById(R.id.btnEscuela);
        seekbarSalir=findViewById(R.id.seekbarSalir);




        btnPreEncuentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PreEncuentroActivity.class);
                startActivity(intent);
            }
        });

        bntCelula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MiCelulaActivity.class);
                startActivity(intent);
            }
        });

        btnEncuentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),EscuelaActivity.class);
                startActivity(intent);
            }
        });

        btnPosEncuentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PosEncuentroActivity.class);
                startActivity(intent);
            }
        });

        btnEscuela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PreEncuentroActivity.class);
                startActivity(intent);
            }
        });


        seekbarSalir.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            Toast.makeText(getApplicationContext(),"EXIT SYSTEM", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                finish();
            }
        });

    }






}
