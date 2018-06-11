package com.ucc.proyectofinal.views.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ucc.proyectofinal.R;
import com.ucc.proyectofinal.utilities.SQLite;
import com.ucc.proyectofinal.utilities.Sync;

import java.io.File;

/**
 * Created by LENOVO USER on 30/05/2018.
 */

public class ActivityMenu extends AppCompatActivity {

    private Button bt1agro;
    private Button bt2bellas;
    private Button bt3cienciaseduca;
    private Button bt4cienciasalud;
    private Button bt5cienciassocial;
    private Button bt6ecoadmincnt;
    private Button bt7ingenier;
    private Button bt8mate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bt1agro =(Button) findViewById(R.id.bt1agro);
        bt1agro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bt1agro= new Intent( ActivityMenu.this, ActivityMateria.class);
                bt1agro.putExtra("area","1");
                startActivity(bt1agro);
            }
        });




        bt2bellas = (Button) findViewById(R.id.bt2bellas);

        bt2bellas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent bt2bellas= new Intent( ActivityMenu.this, ActivityMateria.class );
                bt2bellas.putExtra("area","2");
                startActivity(bt2bellas);
            }
        });

        bt3cienciaseduca= (Button) findViewById(R.id. bt3cienciaedu);

        bt3cienciaseduca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent  bt3cienciaseduca= new Intent( ActivityMenu.this, ActivityMateria.class );
                bt3cienciaseduca.putExtra("area","3");
                startActivity(bt3cienciaseduca);
            }
        });


        bt4cienciasalud= (Button) findViewById(R.id. bt4cienciasalud);

        bt4cienciasalud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent  bt4cienciasalud= new Intent( ActivityMenu.this, ActivityMateria.class );
                bt4cienciasalud.putExtra("area","4");
                startActivity(bt4cienciasalud);
            }
        });

        bt5cienciassocial= (Button) findViewById(R.id. bt5sociales);

        bt5cienciassocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent  bt5cienciassocial= new Intent( ActivityMenu.this, ActivityMateria.class );
                bt5cienciassocial.putExtra("area","5");
                startActivity(bt5cienciassocial);
            }
        });


        bt6ecoadmincnt= (Button) findViewById(R.id. bt6economia);

        bt6ecoadmincnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent bt6ecoadmincnt= new Intent( ActivityMenu.this, ActivityMateria.class );
                bt6ecoadmincnt.putExtra("area","6");
                startActivity(bt6ecoadmincnt);
            }
        });

        bt7ingenier= (Button) findViewById(R.id. bt7ingenieria);

        bt7ingenier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent bt7ingenier= new Intent( ActivityMenu.this, ActivityMateria.class );
                bt7ingenier.putExtra("area","7");
                startActivity(bt7ingenier);
            }
        });

        bt8mate= (Button) findViewById(R.id. bt8matematica);

        bt8mate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent bt8mate= new Intent( ActivityMenu.this, ActivityMateria.class );
                bt8mate.putExtra("area","8");
                startActivity(bt8mate);
            }
        });


        sincronizar();


    }

    /*@Override
    protected void onResume() {
        super.onResume();
        sincronizar();
    }*/

    private void sincronizar() {
        //conectando con sqlite
        final SQLiteDatabase db;
        final SQLite conn=new SQLite(this,"workshopp",null,1);
        db=conn.getWritableDatabase();

        //creando la base de datos
        File dbFile = this.getDatabasePath("workshopp");
        conn.onCreate(db);
        if(!dbFile.exists()){
            conn.onCreate(db);
        }

        //sincronizar la base de datos en la nube
        Sync.syncAll(conn,db);
    }
}
