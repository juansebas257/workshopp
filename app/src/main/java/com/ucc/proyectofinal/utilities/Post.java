package com.ucc.proyectofinal.utilities;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

// Async Task to access the web
public class Post extends AsyncTask<String, Void, String> {

    public static String server="http://workshopp.gearsis.com/api";
    public StringBuilder rs;

    @Override
    protected String doInBackground(String... params) {
        //RECIBIENDO PARAMETROS...
        String param_url=params[0];
        String param_type=params[1];
        String param_email=params[2];
        String param_password=params[3];

        rs=new StringBuilder();
        try {
            URL url = new URL(param_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();
            //jsonParam.put("timestamp", 1488873360);
            jsonParam.put("email",param_email );
            jsonParam.put("password", param_password);
            jsonParam.put("type", param_type);

            if(param_type=="create_user" || param_type=="create_course"){
                jsonParam.put("name",params[4]);
                jsonParam.put("area",params[5]);
            }



            Log.i("JSON", jsonParam.toString());
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
            os.writeBytes(jsonParam.toString());

            InputStream in = new BufferedInputStream(conn.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                return line;
            }
            os.flush();
            os.close();

            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG" , conn.getResponseMessage());

            conn.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
            rs=null;
        }
        return null;
    }

}

