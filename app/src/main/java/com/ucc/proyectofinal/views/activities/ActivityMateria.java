package com.ucc.proyectofinal.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ucc.proyectofinal.R;

/**
 * Created by LENOVO USER on 30/05/2018.
 */

public class ActivityMateria extends AppCompatActivity {
    private Button btcontenido;
    private FloatingActionButton btmateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);

        btcontenido =(Button) findViewById(R.id.btcreacontenido);
        btcontenido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btcontenido= new Intent( ActivityMateria.this, ActivityCreaContenido.class);
                startActivity(btcontenido);
            }
        });

        btmateria =(FloatingActionButton) findViewById(R.id.btcreamateria);
        btmateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btmateria= new Intent( ActivityMateria.this, ActivityCreaMateria.class);
                startActivity(btmateria);
            }
        });






    }
}
