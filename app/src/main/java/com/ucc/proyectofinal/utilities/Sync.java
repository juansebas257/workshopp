package com.ucc.proyectofinal.utilities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Sync {

    public static void syncAll(final SQLite conn, final SQLiteDatabase db){

        //subir
        Cursor selectcursos=conn.getResultSet("select id,name,area from courses where sincronizado=0",db);

        for(selectcursos.moveToFirst(); !selectcursos.isAfterLast(); selectcursos.moveToNext()){
            selectcursos.getString(1);
            //sincronizando cursos
            Post subircursos = new Post(){
                protected void onPostExecute(String result) {

                }
            };
            subircursos.execute(new String[]{Post.server, "create_course","juansebas257@gmail.com","Passw0rdStian",selectcursos.getString(1),selectcursos.getString(2)});
        }

        //sincronizando cursos
        Post cursos = new Post(){
            protected void onPostExecute(String result) {
                try {
                    JSONObject array=new JSONObject(result);
                    if(array.getBoolean("error")){
                    }else {
                        JSONArray content=array.getJSONArray("content");
                        for(int i=0;i<content.length();i++){
                            JSONObject object=content.getJSONObject(i);
                            conn.statement("insert or ignore into courses (idserver,area,name,sincronizado) values ("+object.getInt("id")+","+object.getInt("area")+",'"+object.getString("name")+"',1)",db);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        cursos.execute(new String[]{Post.server, "read_courses","juansebas257@gmail.com","Passw0rdStian"});

        //sincronizando documentos
        Post documentos = new Post(){
            protected void onPostExecute(String result) {
                try {
                    JSONObject array=new JSONObject(result);
                    if(array.getBoolean("error")){
                        System.out.println("error");
                    }else {
                        JSONArray content=array.getJSONArray("content");
                        for(int i=0;i<content.length();i++){
                            JSONObject object=content.getJSONObject(i);
                            conn.statement("insert or ignore into documents (idserver,course,user,description,calification,type,file,teacher,sincronizado) values ("+object.getInt("id")+","+object.getInt("course")+","+object.getInt("user")+",'"+object.getString("description")+"',"+object.getString("calification")+","+object.getInt("type")+",'"+object.getString("file")+"','"+object.getString("teacher")+"',1)",db);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        documentos.execute(new String[]{Post.server, "read_documents","juansebas257@gmail.com","Passw0rdStian"});
    }

}
