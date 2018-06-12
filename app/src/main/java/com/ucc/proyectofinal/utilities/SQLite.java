package com.ucc.proyectofinal.utilities;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SQLite extends SQLiteOpenHelper{

    public SQLite(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists courses");
        db.execSQL("drop table if exists documents");

        db.execSQL("CREATE TABLE users (id integer primary key autoincrement,idserver integer unique,name text, email text,password text,sincronizado integer default 0)");
        db.execSQL("CREATE TABLE courses (id integer primary key autoincrement,idserver integer unique, area integer,name text,sincronizado integer default 0)");
        db.execSQL("CREATE TABLE documents (id integer primary key autoincrement,idserver integer unique, course integer,user integer,description text,calification text,type integer,file text,teacher text,sincronizado integer default 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //not implemented yet
    }

    public void statement(String statement,SQLiteDatabase db){
        try {
            db.execSQL(statement);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public String getString(String query,SQLiteDatabase db){
        Cursor fila = db.rawQuery(query,null);
        fila.moveToFirst();
        try {
            return fila.getString(0);
        }catch (CursorIndexOutOfBoundsException ex){
            return null;
        }
    }

    public boolean getBoolean(String query,SQLiteDatabase db){
        Cursor fila = db.rawQuery(query,null);
        fila.moveToFirst();
        try {
            return fila.getInt(0)==1;
        }catch (CursorIndexOutOfBoundsException ex){
            return false;
        }
    }

    public Cursor getResultSet(String query,SQLiteDatabase db){
        Cursor filas = db.rawQuery(query,null);
        filas.moveToFirst();
        return filas;
    }
}
