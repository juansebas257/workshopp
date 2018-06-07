package com.ucc.proyectofinal.utilities;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper{

    public SQLite(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("drop table if exists users");
        //db.execSQL("drop table if exists courses");
        //db.execSQL("drop table if exists documents");

        db.execSQL("CREATE TABLE users (id integer,name text, email text,password text)");
        db.execSQL("insert into users values(1,'Sebastián Jiménez','juansebas257@gmail.com','Passw0rdStian')");
        db.execSQL("insert into users values(2,'Nayibi Silva','nayiby.silva@gmail.com','1204')");
        db.execSQL("CREATE TABLE courses (id integer, area integer,name text)");
        db.execSQL("CREATE TABLE documents (id integer, course integer,user integer,description text,calification integer,type integer,file text,teacher text)");
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
