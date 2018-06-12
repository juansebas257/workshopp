package com.ucc.proyectofinal.views.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ucc.proyectofinal.R;

import java.io.File;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.widget.ArrayAdapter.createFromResource;

/**
 * Created by LENOVO USER on 30/05/2018.
 */

public class ActivityCreaContenido extends AppCompatActivity {

    Spinner tipo;

    private String APP_DIRECTORY ="myPictureApp/";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jgp";


    private  final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE =200;

    private ImageView imageView;
    private FloatingActionButton bt;
    private static final int MY_PERMISSION = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_contenido);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        tipo=(Spinner) findViewById(R.id.spinnerSubirContenido);
        ArrayAdapter<CharSequence> adapter = createFromResource(this, R.array.tipo, android.R.layout.simple_spinner_item);
        tipo.setAdapter(adapter);


        imageView = (ImageView) findViewById(R.id.setPicture);
        bt =(FloatingActionButton) findViewById(R.id.btnTakePhoto);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(requestPermissions()) {
                    bt.setEnabled(true);


                    final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
                    final AlertDialog.Builder builder = new AlertDialog.Builder(ActivityCreaContenido.this);
                    builder.setTitle("Elige una opcion");
                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int seleccion) {

                            if (options[seleccion] == "Tomar foto") {
                                openCamera();

                            } else if (options[seleccion] == "Elegir de galeria") {
                                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/*");
                                startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                            } else if (options[seleccion] == "Cancelar") {
                                dialog.dismiss();
                            }
                        }

                    });

                    builder.show();
                }
            }
        });
    }

    private boolean requestPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        if (checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        if (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE) ||
                shouldShowRequestPermissionRationale(CAMERA)) {
            Toast.makeText(this, "contenido", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSION);
        } else {
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSION);
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSION) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                bt.setEnabled(true);
            }
        }
    }



    private void openCamera() {
        File file =new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();

        String path = Environment.getExternalStorageDirectory() + File.separator
                + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;

        File newFile = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
        startActivityForResult(intent, PHOTO_CODE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PHOTO_CODE:
                if(resultCode == RESULT_OK){
                    String dir = Environment.getExternalStorageDirectory() + File.separator
                            + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
                    decodeBitmap(dir);
                }
                break;

            case SELECT_PICTURE:
                if(resultCode== RESULT_OK){
                    Uri path = data.getData();
                    imageView.setImageURI(path);
                }
                break;
        }
    }

    private void decodeBitmap (String dir){
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);
        imageView.setImageBitmap(bitmap);
    }
}