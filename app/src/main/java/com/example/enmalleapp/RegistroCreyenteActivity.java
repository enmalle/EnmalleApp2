package com.example.enmalleapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.enmalleapp.conexion.ConnectionClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class RegistroCreyenteActivity extends AppCompatActivity {
    private static final String TAG = "RegistroCreyenteActivity";
    ToggleButton etRCtoggleButton;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDataSetListener;

    EditText etRCCedula, etRCPNombre, etRCSNombre, etRCPApellido, etRCSApellido, etRCFechaNacimiento;
    EditText etRCSexo, etRCBarrio, etRCCallePrincipal, etRCCalleSecundaria, etRCNumeroCasa, etRCTelefonoCasa;
    EditText etRCTelefonoOficina, etRCTelefonoCelular, etRCEstadoCivil;
    Button btRCGuardar, btRCCancelar;

    String cedula, nombre, apellido, nacimiento, sexo, barrio, callep, calles, telefonocasa, telefonocelular, estadocivil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_creyente);

        etRCCedula = findViewById(R.id.etRCCedula);
        etRCPNombre = findViewById(R.id.etRCPNombre);
        etRCSNombre = findViewById(R.id.etRCSNombre);
        etRCPApellido = findViewById(R.id.etRCPApellido);
        etRCSApellido = findViewById(R.id.etRCSApellido);
        etRCFechaNacimiento = findViewById(R.id.etRCFechaNacimiento);
        etRCEstadoCivil = findViewById(R.id.etRCEstadoCivil);
        etRCSexo = findViewById(R.id.etRCSexo);
        etRCBarrio = findViewById(R.id.etRCBarrio);
        etRCCallePrincipal = findViewById(R.id.etRCCallePrincipal);
        etRCCalleSecundaria = findViewById(R.id.etRCCalleSecundaria);
        etRCNumeroCasa = findViewById(R.id.etRCNumeroCasa);
        etRCTelefonoCasa = findViewById(R.id.etRCTelefonoCasa);
        etRCTelefonoOficina = findViewById(R.id.etRCTelefonoOficina);
        etRCTelefonoCelular = findViewById(R.id.etRCTelefonoCelular);
        btRCGuardar = findViewById(R.id.btRCGuardar);
        btRCCancelar = findViewById(R.id.btRCCancelar);

        mDisplayDate = findViewById(R.id.etRCFechaNacimiento);



        btRCCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etRCCedula.setText("");
                etRCPNombre.setText("");
                etRCSNombre.setText("");
                etRCPApellido.setText("");
                etRCSApellido.setText("");
                etRCFechaNacimiento.setText("");
                etRCEstadoCivil.setText("");
                etRCSexo.setText("");
                etRCBarrio.setText("");
                etRCCallePrincipal.setText("");
                etRCCalleSecundaria.setText("");
                etRCNumeroCasa.setText("");
                etRCTelefonoCasa.setText("");
                etRCTelefonoOficina.setText("");
                etRCTelefonoCelular.setText("");
                finish();
            }
        });

        btRCGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cedula=etRCCedula.getText().toString();
                nombre=etRCPNombre.getText().toString();
                apellido=etRCPApellido.getText().toString();
                nacimiento=etRCFechaNacimiento.getText().toString();
                sexo=etRCSexo.getText().toString();
                barrio=etRCBarrio.getText().toString();
                callep=etRCCallePrincipal.getText().toString();
                calles=etRCCalleSecundaria.getText().toString();
                telefonocasa=etRCTelefonoCasa.getText().toString();
                telefonocelular=etRCTelefonoCelular.getText().toString();
                estadocivil=etRCEstadoCivil.getText().toString();

                if (cedula.isEmpty()){
                    Toast.makeText(RegistroCreyenteActivity.this, "Ingrese el numero de cedula", Toast.LENGTH_LONG).show();
                    etRCCedula.setHintTextColor(Color.RED);
                }else{
                    if (nombre.isEmpty()){
                        Toast.makeText(RegistroCreyenteActivity.this, "Ingrese el nombre", Toast.LENGTH_LONG).show();
                        etRCPNombre.setHintTextColor(Color.RED);
                    }else{
                        etRCPNombre.setHintTextColor(Color.BLUE);
                        if (apellido.isEmpty()){
                            Toast.makeText(RegistroCreyenteActivity.this, "Ingrese el apellido", Toast.LENGTH_LONG).show();
                            etRCPApellido.setHintTextColor(Color.RED);
                        }else{
                            etRCPApellido.setHintTextColor(Color.RED);
                            if (nacimiento.isEmpty()){
                                Toast.makeText(RegistroCreyenteActivity.this, "Ingese la fecha de nacimiento", Toast.LENGTH_LONG).show();
                                etRCFechaNacimiento.setHintTextColor(Color.RED);
                            }else{
                                if (sexo.isEmpty()){
                                    Toast.makeText(RegistroCreyenteActivity.this, "Por favor escoja el sexo del creyente", Toast.LENGTH_LONG).show();
                                    etRCSexo.setHintTextColor(Color.RED);
                                }else{
                                    if (barrio.isEmpty()){
                                        Toast.makeText(RegistroCreyenteActivity.this, "Ingrese el barrio", Toast.LENGTH_LONG).show();
                                        etRCBarrio.setHintTextColor(Color.RED);
                                    }else{
                                        if (callep.isEmpty()){
                                            Toast.makeText(RegistroCreyenteActivity.this, "Ingrese la calle principal", Toast.LENGTH_LONG).show();
                                            etRCCallePrincipal.setHintTextColor(Color.RED);
                                        }else{
                                            if (calles.isEmpty()){
                                                Toast.makeText(RegistroCreyenteActivity.this, "Ingrese la calle secundaria", Toast.LENGTH_LONG).show();
                                                etRCCalleSecundaria.setHintTextColor(Color.RED);
                                            }else{
                                                if (telefonocasa.isEmpty()){
                                                    Toast.makeText(RegistroCreyenteActivity.this, "Ingrese el numero de telefono de casa si no lo tiene ingrese 0222222222", Toast.LENGTH_LONG).show();
                                                    etRCTelefonoCasa.setHintTextColor(Color.RED);
                                                }else{
                                                    if (telefonocelular.isEmpty()){
                                                        Toast.makeText(RegistroCreyenteActivity.this, "Ingrese el numero de telefono celular", Toast.LENGTH_LONG).show();
                                                        etRCTelefonoCelular.setHintTextColor(Color.RED);
                                                    }else{
                                                        if (estadocivil.isEmpty()){
                                                            Toast.makeText(RegistroCreyenteActivity.this, "Por favor escoja el Estado Civil", Toast.LENGTH_LONG).show();
                                                            etRCEstadoCivil.setHintTextColor(Color.RED);
                                                        }else
                                                            //grabar los registros
                                                            //Toast.makeText(RegistroCreyenteActivity.this, "TODO OK", Toast.LENGTH_LONG).show();

                                                        agregarCreyente();
                                                        finish();


                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });



        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegistroCreyenteActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDataSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //Log.d(TAG, "onDateSet: mm/dd/aaaa: " +month + "/" + day + "/" + year);
                String date = month + "/" + day +"/" + year;
                month=month+1;
                etRCFechaNacimiento.setText(date);
            }
        };

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        etRCEstadoCivil = findViewById(R.id.etRCEstadoCivil);
        etRCSexo = findViewById(R.id.etRCSexo);


        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbCasado:
                if (checked)
                    etRCEstadoCivil.setText("C");
                break;
            case R.id.rbSoltero:
                if (checked)
                    etRCEstadoCivil.setText("S");
                break;
            case R.id.rbDivorciado:
                if (checked)
                    etRCEstadoCivil.setText("D");
                break;
            case R.id.rbViudo:
                if (checked)
                    etRCEstadoCivil.setText("V");
                break;
            case R.id.rbUnionLibre:
                if (checked)
                    etRCEstadoCivil.setText("U");
                break;
            case R.id.rbFemenino:
                if (checked)
                    etRCSexo.setText("F");
                break;
            case R.id.rbMasculino:
                if (checked)
                    etRCSexo.setText("M");
                break;

        }
    }

    // conexcionBD
    ConnectionClass cc = new ConnectionClass();

    public void agregarCreyente(){
        try {
            String parametro1;
            claseGlobal objLectura = (claseGlobal)getApplicationContext();
            parametro1 = objLectura.getIdLider();
            PreparedStatement pst = cc.stConsulta("exec sp_registrar_creyente ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
            pst.setString(1,etRCCedula.getText().toString());
            pst.setString(2,etRCPNombre.getText().toString());
            pst.setString(3,etRCSNombre.getText().toString());
            pst.setString(4,etRCPApellido.getText().toString());
            pst.setString(5,etRCSApellido.getText().toString());
            pst.setString(6,etRCFechaNacimiento.getText().toString());
            pst.setString(7,etRCEstadoCivil.getText().toString());
            pst.setString(8,etRCSexo.getText().toString());
            pst.setString(9,etRCBarrio.getText().toString());
            pst.setString(10,etRCCallePrincipal.getText().toString());
            pst.setString(11,etRCCalleSecundaria.getText().toString());
            pst.setString(12,etRCNumeroCasa.getText().toString());
            pst.setString(13,etRCTelefonoCasa.getText().toString());
            pst.setString(14,etRCTelefonoOficina.getText().toString());
            pst.setString(15,etRCTelefonoCelular.getText().toString());
            pst.setString(16,parametro1);
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


}

