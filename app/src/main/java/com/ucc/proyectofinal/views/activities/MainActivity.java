package com.ucc.proyectofinal.views.activities;

import com.ucc.proyectofinal.utilities.Post;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ucc.proyectofinal.R;
import com.ucc.proyectofinal.utilities.SQLite;
import com.ucc.proyectofinal.utilities.Sync;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INTENT PARA PRUEBAS, BORRAR ANTES DE PRODUCCION
        //Intent ingresarPrueba = new Intent(MainActivity.this, ActivityMenu.class);
        //startActivity(ingresarPrueba);

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
        String email=ETemail.getText().toString();
        String password=ETpassword.getText().toString();

        Post login = new Post(){
            protected void onPostExecute(String result) {
                System.out.println(result);
                try {
                    JSONObject array=new JSONObject(result);
                    if(array.getBoolean("error")){
                        completeLogin(false);
                    }else {
                        completeLogin(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        login.execute(new String[]{Post.server, "connect",email,password});
    }

    public void completeLogin(boolean loginok){
        if(loginok) {
            Intent ingresar = new Intent(MainActivity.this, ActivityMenu.class);
            startActivity(ingresar);
        }else{
            System.out.println("USUARIO O CONTRASEÑA INVALIOS");
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
        }
    }
}





