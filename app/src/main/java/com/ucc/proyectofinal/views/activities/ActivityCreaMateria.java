package com.ucc.proyectofinal.views.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ucc.proyectofinal.R;
import com.ucc.proyectofinal.utilities.SQLite;

import static android.widget.ArrayAdapter.createFromResource;

/**
 * Created by LENOVO USER on 30/05/2018.
 */

public class ActivityCreaMateria extends AppCompatActivity {

    Spinner opciones;
    private FloatingActionButton guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_materia);

        opciones = (Spinner) findViewById(R.id.spinnerCreaMateria);
        ArrayAdapter<CharSequence> adapter = createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

        guardar = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crear();
            }
        });
    }


    public void crear(){
        EditText ETDescripcion=(EditText)findViewById(R.id.editText);
        String descripcion=ETDescripcion.getText().toString();

        Spinner spinnerCreaMateria=(Spinner)findViewById(R.id.spinnerCreaMateria);
        int area=spinnerCreaMateria.getSelectedItemPosition();

        final SQLiteDatabase db;
        final SQLite conn=new SQLite(this,"workshopp",null,1);
        db=conn.getWritableDatabase();
        conn.statement("insert into courses (idserver,area,name) values (null,"+area+",'"+descripcion+"')",db);
        finish();
    }


}

