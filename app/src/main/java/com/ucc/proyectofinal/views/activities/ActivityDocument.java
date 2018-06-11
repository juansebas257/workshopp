package com.ucc.proyectofinal.views.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ucc.proyectofinal.R;
import com.ucc.proyectofinal.utilities.SQLite;

import java.util.ArrayList;

/**
 * Created by LENOVO USER on 30/05/2018.
 */

public class ActivityDocument extends AppCompatActivity {
    private Button btcontenido;
    private FloatingActionButton btmateria;
    ArrayList<String> documentos;
    String materia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documento);

        Intent myIntent = getIntent(); // gets the previously created intent
        materia = myIntent.getStringExtra("materia");

        ListView moviesList=(ListView)findViewById(R.id.myList);
        documentos = new ArrayList<String>();
        getDocumentos();

        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, documentos);
        // Set The Adapter
        moviesList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3) {

                String selected= documentos.get(position);
                Toast.makeText(getApplicationContext(), "DOcumento seleccionado : "+selected,   Toast.LENGTH_LONG).show();
            }
        });



        btmateria =(FloatingActionButton) findViewById(R.id.btcreamateria);
        btmateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btmateria= new Intent( ActivityDocument.this, ActivityCreaContenido.class);
                startActivity(btmateria);
            }
        });

    }
    public void getDocumentos() {
        documentos.clear();
        final SQLiteDatabase db;
        final SQLite conn=new SQLite(this,"workshopp",null,1);
        db=conn.getWritableDatabase();
        Cursor cursos=conn.getResultSet("select id,name from documentos;",db);

        for(cursos.moveToFirst(); !cursos.isAfterLast(); cursos.moveToNext()){
            documentos.add(cursos.getString(1));
        }
    }
}
