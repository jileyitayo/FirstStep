package com.example.jil.firststep;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.jil.Chat.Util;
import com.example.jil.Dialog.MyMoreInfoDialog;
import com.example.jil.JSON.JSONParser;
import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.SQLite.DAOHealthApp;
import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.Users.Users;
import com.example.jil.androidrecyclerviewgridview.ItemObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import layout.ManageChild;
import okhttp3.OkHttpClient;
import okhttp3.OkUrlFactory;

public class activity_manage extends AppCompatActivity{

    JSONParser jsonParser = new JSONParser();
    private ProgressDialog pDialog;

    DAOChildApp childHelper;
    DAOMoreInformation childInfoHelper;
    private static final String TAG_SUCCESS = "success";
    InputStream is=null;
    String result=null;
    String line=null;
    int code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_manage);
        Toolbar toolbarBrown = (Toolbar) findViewById(R.id.toolbarBrown);
        setSupportActionBar(toolbarBrown);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //initialize what the fragment should be;
        //MainActivityFragment mainActivityFragment = new MainActivityFragment();
        ft.replace(R.id.fragmentActManage, new ManageChild());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.profile) {
            startActivity(new Intent(activity_manage.this, Profile_Activity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new MyMoreInfoDialog();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    private class sendUsersDataToDB extends AsyncTask<String, Void, String> {
        //Users sqliteUsers = new Users();
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(activity_manage.this);
           //sqliteUsers = userHelper.getUsers();
            pDialog.setMessage("Backing up...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            //String url = Util.backupUsers + "?username=" + sqliteUsers.getUsername() + "&password=" + sqliteUsers.getPassword() + "&email=" + sqliteUsers.getEmailAddress() + "&role=" + sqliteUsers.getRole();
            String url = Util.backupUsers + "?username=hilhjl&password=kiljk&email=kljkh&role=knjhk";
            Log.i("pavan", "url" + url);
            OkHttpClient client_for_getMyFriends = new OkHttpClient();
            String response = null;
// String response=Utility.callhttpRequest(url);
            try {
                url = url.replace(" ", "%20");
                response = callOkHttpRequest(new URL(url),
                        client_for_getMyFriends);
            } catch (MalformedURLException e) { // TODO Auto-generated catch block e.printStackTrace();
            } catch (IOException e) {
// TODO Auto-generated catch block e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
//Toast.makeText(context,"response "+result,Toast.LENGTH_LONG).show();
        }
    }

    // Http request using OkHttpClient
    String callOkHttpRequest(URL url, OkHttpClient tempClient)
            throws IOException {
        HttpURLConnection connection = new OkUrlFactory(tempClient).open(url);
        connection.setConnectTimeout(40000);
        InputStream in = null;
        try {
// Read the response.
            in = connection.getInputStream();
            byte[] response = readFully(in);
            return new String(response, "UTF-8");
        } finally {
            if (in != null)
                in.close();
        }
    }

    byte[] readFully(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int count; (count = in.read(buffer)) != -1; ) {
            out.write(buffer, 0, count);
        }
        return out.toByteArray();
    }

}
