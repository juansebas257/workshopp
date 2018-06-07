package com.ucc.proyectofinal.utilities;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

// Async Task to access the web
public class Post extends AsyncTask<String, Void, String> {

    public StringBuilder rs;

    @Override
    protected String doInBackground(String... params) {
        System.out.println("POST recibido");
        //RECIBIENDO PARAMETROS...
        String param_url=params[0];
        String param_type=params[1];
        String param_email=params[2];
        String param_password=params[3];

        rs=new StringBuilder();
        try {
            String type = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(param_type, "UTF-8");
            String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(param_email, "UTF-8");
            String password = URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(param_password, "UTF-8");

            URL url = new URL(param_url);
            System.out.println(param_url);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(60000);
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(type);
            wr.write(email);
            wr.write(password);
            wr.flush();

            System.out.println("POST");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                rs.append(line);
                System.out.println(line);
            }
            wr.close();
            rd.close();
        }catch(Exception ex){
            ex.printStackTrace();
            rs=null;
        }
        return null;
    }

}

