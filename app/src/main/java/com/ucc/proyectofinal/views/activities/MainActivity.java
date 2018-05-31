package com.ucc.proyectofinal.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ucc.proyectofinal.R;

public class MainActivity extends AppCompatActivity {

    private Button registro;
    private Button ingresar ;
    private Button creamateria;
    private Button creacontenido;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registro = (Button) findViewById(R.id.registrar);


        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registro = new Intent(MainActivity.this, ActivityRegistro.class);
                startActivity(registro);
            }
        });

       ingresar = (Button) findViewById(R.id.ingresar);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ingresar= new Intent( MainActivity.this, ActivityMenu.class );
                startActivity(ingresar);

            }


        });




    }




}
