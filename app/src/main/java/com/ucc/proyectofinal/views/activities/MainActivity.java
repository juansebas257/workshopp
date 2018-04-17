package com.ucc.proyectofinal.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ucc.proyectofinal.R;
import com.ucc.proyectofinal.views.activities.IView;

public class MainActivity extends AppCompatActivity {

    private Button registro;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registro.setOnClickListener( new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                VerificarLogin(Login.getTex()toString().toLowerCase().contraseña.getTex().toString().toLowercase));
            }
                                     }
        )

        ;


    }
}
