package com.ucc.proyectofinal.views.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ucc.proyectofinal.R;
import com.ucc.proyectofinal.utilities.SQLite;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.widget.ArrayAdapter.createFromResource;

/**
 * Created by LENOVO USER on 30/05/2018.
 */

public class ActivityVerContenido extends AppCompatActivity {

    TextView cont_title, cont_materia, cont_profesor,cont_calificacion;
    int id_document;
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_contenido);

        cont_title=(TextView)findViewById(R.id.cont_title);
        cont_profesor=(TextView)findViewById(R.id.cont_profesor);
        cont_materia=(TextView)findViewById(R.id.cont_materia);
        cont_calificacion=(TextView)findViewById(R.id.cont_calificacion);

        Intent myIntent = getIntent(); // gets the previously created intent
        id_document = myIntent.getIntExtra("document",0);

        Button boton = (Button) findViewById(R.id.cont_descarga);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download();
            }
        });

        readDocument();
    }

    public void download(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private void readDocument(){
        final SQLiteDatabase db;
        final SQLite conn=new SQLite(this,"workshopp",null,1);
        db=conn.getWritableDatabase();
        Cursor cursos=conn.getResultSet("select documents.description,ifnull(documents.calification,''),courses.name,documents.type,documents.file,ifnull(documents.teacher,'') from documents left join courses on courses.id=documents.course where documents.id="+id_document+"",db);

        for(cursos.moveToFirst(); !cursos.isAfterLast(); cursos.moveToNext()){
            cont_title.setText(cursos.getString(0));
            cont_calificacion.setText("Calificación: "+ cursos.getString(1));
            cont_materia.setText("Materia: "+cursos.getString(2));
            cont_profesor.setText("Profesor: "+cursos.getString(5));
            url="http://workshopp.gearsis.com/storage/files/"+cursos.getString(4);
        }
    }
}