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

public class ActivityMateria extends AppCompatActivity {
    private Button btcontenido;
    private FloatingActionButton btmateria;
    ArrayList<String> courses;
    ArrayList<Integer> id_courses;
    String area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);

        Intent myIntent = getIntent(); // gets the previously created intent
        area = myIntent.getStringExtra("area");

        ListView coursesList=(ListView)findViewById(R.id.myList);

        courses = new ArrayList<String>();
        id_courses = new ArrayList<Integer>();
        getCourses();

        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, courses);
        // Set The Adapter
        coursesList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        coursesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3) {
                int selected=id_courses.get(position);
                Intent btmateria= new Intent( ActivityMateria.this, ActivityDocument.class);
                Log.i("dhfds",selected+"");
                btmateria.putExtra("materia",selected);
                startActivity(btmateria);
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
    public void getCourses() {
        courses.clear();
        id_courses.clear();
        final SQLiteDatabase db;
        final SQLite conn=new SQLite(this,"workshopp",null,1);
        db=conn.getWritableDatabase();
        Cursor cursos=conn.getResultSet("select id,name from courses where area="+area,db);

        for(cursos.moveToFirst(); !cursos.isAfterLast(); cursos.moveToNext()){
            courses.add(cursos.getString(1));
            id_courses.add(cursos.getInt(0));
            Log.i("READING",cursos.getString(0)+"");
        }
    }
}