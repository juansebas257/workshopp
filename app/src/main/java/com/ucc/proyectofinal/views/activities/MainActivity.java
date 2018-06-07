package com.ucc.proyectofinal.views.activities;

import com.ucc.proyectofinal.utilities.Post;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.ucc.proyectofinal.R;
import com.ucc.proyectofinal.utilities.SQLite;

import java.io.File;
import java.lang.reflect.Array;

import static android.widget.ArrayAdapter.createFromResource;

public class MainActivity extends AppCompatActivity {


    private Button registro;
    private Button ingresar ;
    private Button creamateria;
    private Button creacontenido;
    private EditText ETemail;
    private EditText ETpassword;

    private String server="http://192.168.0.5/workshopp_web/public/api";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETemail=(EditText)findViewById(R.id.Login);
        ETpassword=(EditText)findViewById(R.id.contrasena);

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
                login();
            }

        });

    }

    private void login(){
        //conectando con sqlite
        SQLiteDatabase db;
        SQLite conn=new SQLite(this,"workshopp",null,1);
        db=conn.getWritableDatabase();

        File dbFile = this.getDatabasePath("workshopp");
        System.out.println("existe la base:"+dbFile.exists());
        if(!dbFile.exists()){
            conn.onCreate(db);
        }


        /*Post tarea = new Post(){
            protected void onPostExecute(String result) {
                System.out.println("ON POST EXECUTE");
                System.out.println(this.rs);
            }
        };
        tarea.execute(new String[]{server, "read_courses","juansebas257@gmail.com","Passw0rdStian"});
        System.out.println("POST ejecutado");*/

        String email=ETemail.getText().toString();
        String password=ETpassword.getText().toString();

        boolean loginok=conn.getBoolean("select count(*)>0 from users where email='"+email+"' and password='"+password+"'",db);


        if(loginok) {
            Intent ingresar = new Intent(MainActivity.this, ActivityMenu.class);
            startActivity(ingresar);
        }else{
            System.out.println("USUARIO O CONTRASEÑA INVALIOS");
        }
    }
}





