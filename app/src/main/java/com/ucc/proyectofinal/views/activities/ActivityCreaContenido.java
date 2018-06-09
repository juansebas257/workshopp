package com.ucc.proyectofinal.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.ucc.proyectofinal.R;

import static android.widget.ArrayAdapter.createFromResource;

/**
 * Created by LENOVO USER on 30/05/2018.
 */

public class ActivityCreaContenido extends AppCompatActivity {

    Spinner tipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_contenido);


        tipo=(Spinner) findViewById(R.id.spinnerSubirContenido);
        ArrayAdapter<CharSequence> adapter = createFromResource(this, R.array.tipo, android.R.layout.simple_spinner_item);
        tipo.setAdapter(adapter);

    }

}
