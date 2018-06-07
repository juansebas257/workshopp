package com.ucc.proyectofinal.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.ucc.proyectofinal.R;

import static android.widget.ArrayAdapter.createFromResource;

/**
 * Created by LENOVO USER on 30/05/2018.
 */

public class ActivityCreaMateria extends AppCompatActivity {

    Spinner opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_materia);

         opciones = (Spinner) findViewById(R.id.spinnerCreaMateria);
        ArrayAdapter<CharSequence> adapter = createFromResource
                (this, R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
    }




    }

