package com.ucc.proyectofinal.views.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ucc.proyectofinal.R;
import com.ucc.proyectofinal.utilities.Post;
import com.ucc.proyectofinal.utilities.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LENOVO USER on 20/04/2018.
 */

public class ActivityRegistro extends AppCompatActivity {

    Button btregistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btregistrar =(Button) findViewById(R.id.btregistrar);
        btregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    public void save(){
        System.out.println("saving");
        EditText ETName=(EditText)findViewById(R.id.name);
        String name=ETName.getText().toString();

        EditText ETEmail=(EditText)findViewById(R.id.email);
        String email=ETEmail.getText().toString();

        EditText ETPassword=(EditText)findViewById(R.id.password);
        String password=ETPassword.getText().toString();

        EditText ETPassword2=(EditText)findViewById(R.id.password2);
        String password2=ETPassword2.getText().toString();

        if(!password.equals(password2)){
            Toast.makeText(this, "La contraseña y la confirmación no coinciden", Toast.LENGTH_LONG).show();
        }else{
            final SQLiteDatabase db;
            final SQLite conn=new SQLite(this,"workshopp",null,1);
            db=conn.getWritableDatabase();

            //sincronizando cursos
            Post cursos = new Post(){
                protected void onPostExecute(String result) {
                    finish();
                }
            };
            cursos.execute(new String[]{Post.server, "create_user",email,password,name});
        }
    }
}
