package com.example.jil.JSON;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by JIL on 23/01/16.
 */
import android.util.Log;


public class JSONParser {
    static InputStream is = null;
    JSONObject jObj = null;
    StringBuilder result;
    public static final String SERVER_ADDRESS = "http://172.16.156.26:80/";

    String charset = "UTF-8";
    HttpURLConnection conn;
    DataOutputStream wr;
    URL urlObj;
    StringBuilder sbParams;
    String paramsString;

    public JSONParser(){
    }

    //this gets json from url by making HTTP POST or GET method
    public JSONObject makeHttpRequest(String url, String method, HashMap<String,String> params){

        // make HTTP request
        sbParams = new StringBuilder();
        int i =0;
        for(String key : params.keySet()){
            try{
                if(i != 0){
                    sbParams.append("&");
                }
                sbParams.append(key).append("=").append(URLEncoder.encode(params.get(key), charset));
            }
            catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            i++;
        }

        // method is POST

        if(method.equals("POST")) {
            try {
                urlObj = new URL(url);
                conn = (HttpURLConnection) urlObj.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Accept-Charset", charset);
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.connect();
                paramsString = sbParams.toString();
                wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(paramsString);
                wr.flush();
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //if method is GET

        else if(method.equals("GET")){
            if(sbParams.length() != 0){
                url += "?" + sbParams.toString();
            }
            try {
                urlObj = new URL(url);
                conn = (HttpURLConnection) urlObj.openConnection();
                conn.setDoOutput(false);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept-Charset", charset);
                conn.setConnectTimeout(15000);
                conn.connect();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        try{
            //Receive the response from the server
           is = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                result.append(line);
            }
            Log.d("JSON Parser", "result: " + result.toString());
        }
        catch (IOException e){
            e.printStackTrace();
        }

        conn.disconnect();

        //try to parse the string to a JSON object
        try {
            String json = result.toString();
            jObj = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));
        }
        catch (JSONException e){
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        //return JSON String
        return jObj;
    }

}

