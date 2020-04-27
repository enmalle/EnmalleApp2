package com.example.enmalleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class EscuelaActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela);
        toolbar=findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menue,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.MenuBuscare:
                Toast.makeText(getApplicationContext(), "Menu Buscar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.MenuGuardare:
                Toast.makeText(getApplicationContext(), "Menu Guardar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.MenuSalire:
                Toast.makeText(getApplicationContext(), "Menu Salir", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
