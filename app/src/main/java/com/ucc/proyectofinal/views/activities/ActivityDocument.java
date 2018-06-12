package com.ucc.proyectofinal.views.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    ArrayList<Integer> id_documentos;
    int materia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documento);

        Intent myIntent = getIntent(); // gets the previously created intent
        materia = myIntent.getIntExtra("materia",0);

        ListView moviesList=(ListView)findViewById(R.id.myList);
        documentos = new ArrayList<String>();
        id_documentos = new ArrayList<Integer>();
        getDocumentos();

        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, documentos);
        // Set The Adapter
        moviesList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3) {

                int selected= id_documentos.get(position);
                Intent intent= new Intent( ActivityDocument.this, ActivityVerContenido.class);
                intent.putExtra("document",selected);
                startActivity(intent);
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
        id_documentos.clear();
        final SQLiteDatabase db;
        final SQLite conn=new SQLite(this,"workshopp",null,1);
        db=conn.getWritableDatabase();
        Cursor cursos=conn.getResultSet("select id,description from documents where course="+materia+";",db);

        Log.i("DEB","select id,description from documents where course="+materia+";");
        for(cursos.moveToFirst(); !cursos.isAfterLast(); cursos.moveToNext()){
            Log.i("DEB",cursos.getString(1));
            documentos.add(cursos.getString(1));
            id_documentos.add(cursos.getInt(0));
        }
    }
}
