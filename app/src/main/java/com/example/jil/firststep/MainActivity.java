package com.example.jil.firststep;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jil.Chat.Util;
import com.example.jil.JSON.JSONParser;
import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.SQLite.DAOHealthApp;
import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.Users.Child;
import com.example.jil.Users.MoreInformationModel;
import com.example.jil.Users.Users;

import org.json.JSONException;
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

import layout.MainActivityFragment;
import okhttp3.OkHttpClient;
import okhttp3.OkUrlFactory;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_SETTINGS = 1;
    Fragment fragment;
    DAOHealthApp userHelper;
    DAOChildApp childApp;
    DAOMoreInformation moreInformation;
    Users sqliteUsers = new Users();
    InputStream is = null;
    String result = null;
    String line = null;
    int code;
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    List<Child> childList = new ArrayList<>();
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//Fragments starts from here

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //initialize what the fragment should be;
        //MainActivityFragment mainActivityFragment = new MainActivityFragment();
        fragment = new MainActivityFragment();
        ft.replace(R.id.fragment, fragment);
        ft.commit();

        userHelper = new DAOHealthApp(this);
        childApp = new DAOChildApp(this);
        moreInformation = new DAOMoreInformation(this);

        sqliteUsers = userHelper.getExistingUsers();
        childList = childApp.getAllChildren();

    }

    @Override
    protected void onResume() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //initialize what the fragment should be;
        //MainActivityFragment mainActivityFragment = new MainActivityFragment();
        ft.replace(R.id.fragment, fragment);
        ft.commit();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //initialize what the fragment should be;
        //MainActivityFragment mainActivityFragment = new MainActivityFragment();
        ft.replace(R.id.fragment, new MainActivityFragment());
        ft.commit();
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent i = new Intent(this, Settings.class);
            startActivityForResult(i, RESULT_SETTINGS);
            return true;
        }

        if (id == R.id.profile) {
            startActivity(new Intent(MainActivity.this, Profile_Activity.class));
            return true;
        }

        if (id == R.id.Logout) {
                finish();
                startActivity(new Intent(MainActivity.this, Login_Activity.class));
            return true;
        }


        if (id == R.id.backup) {
            new BackUp().execute(sqliteUsers.getUsername(), sqliteUsers.getPassword(), sqliteUsers.getEmailAddress(), sqliteUsers.getRole());
            BackupChild();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SETTINGS:
                break;

        }

    }
/*
    private String callOkHttpRequest(URL url, OkHttpClient tempClient)
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

    private byte[] readFully(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int count; (count = in.read(buffer)) != -1; ) {
            out.write(buffer, 0, count);
        }
        return out.toByteArray();
    }*/

    class BackUp extends AsyncTask<String, String, JSONObject> {
        /*
         * Before starting background thread Show Progress Dialog
         * */
        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Backing up...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            // Check for success tag
            try {
                // Building Parameters
                //List<NameValuePair> params = new ArrayList<>();
                HashMap<String, String> params = new HashMap();
                params.put("username", sqliteUsers.getUsername());
                params.put("password", sqliteUsers.getPassword());
                params.put("email", sqliteUsers.getEmailAddress());
                params.put("role", sqliteUsers.getRole());
                Log.d("request!", "starting");
                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        Util.backupUsers, "POST", params);

                Log.d("Login result", json.toString());
                if(json != null){
                    Log.d("Login result", json.toString());

                    return  json;
                }
                else
                {
                    json.toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(JSONObject json) {
            // dismiss the dialog once product deleted

            int success = 0;
            String message = "";

            if(pDialog != null && pDialog.isShowing()){
                pDialog.dismiss();
            }

            if(json != null){
                //Toast.makeText(Register.this, json.toString(), Toast.LENGTH_LONG).show();
                try{
                    success = json.getInt("code");
                    message = json.getString("message");
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
            if(success == 1){
                Log.d("User Created!", "insert successful");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Log.d("Failure", "insert failed");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void BackupChild()
    {
        for (Child child: childList)
        {
            //Child child = childList.get(6);
            String userId = String.valueOf(child.getUserId());
            new BackUpEachChild(child).execute(child.getfirstName(), child.getLastName(), child.getGender(),
                    child.getDateOfBirth(), child.getMoreInfo(), child.getHeight(), child.getWeight(),
                    child.getImg_path(), child.getAddLocation(), child.getAllergies(),child.getParentName(),
                    userId, child.getUsername());
        }
    }

    /*
    child.getfirstName(), child.getLastName(), child.getGender(),
    child.getDateOfBirth(), child.getMoreInfo(), child.getHeight(), child.getWeight(),
            child.getImg_path(), child.getAddLocation(), child.getAllergies(),child.getParentName(),
            child.getUserId(), child.getUsername()
*/
    class BackUpEachChild extends AsyncTask<String, String, JSONObject> {
        /*
         * Before starting background thread Show Progress Dialog
         * */
        Child newChild = new Child();
        boolean failure = false;
        public BackUpEachChild(Child child)
        {
            newChild = child;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Backing up Child...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            */
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            // Check for success tag
            try {
                // Building Parameters
                //List<NameValuePair> params = new ArrayList<>();
                HashMap<String, String> params = new HashMap();
                params.put("firstname", newChild.getfirstName());
                params.put("lastname", newChild.getLastName());
                params.put("gender", newChild.getGender());
                params.put("DOB", newChild.getDateOfBirth());
                params.put("infoCount", newChild.getMoreInfo());
                params.put("height", newChild.getHeight());
                params.put("weight", newChild.getWeight());
                params.put("location", newChild.getAddLocation());
                params.put("allergies", newChild.getAllergies());
                params.put("parentName", newChild.getParentName());
                params.put("addedbyUserID", String.valueOf(newChild.getUserId()));
                params.put("addedByUsername", newChild.getUsername());
                params.put("img_path", newChild.getImg_path());
                Log.d("request!", "starting");
                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        Util.backupChild, "POST", params);

                Log.d("Login result", json.toString());
                if(json != null){
                    Log.d("Login result", json.toString());

                    return  json;
                }
                else
                {
                    json.toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(JSONObject json) {
            // dismiss the dialog once product deleted

            int success = 0;
            String message = "";

            if(pDialog != null && pDialog.isShowing()){
                pDialog.dismiss();
            }

            if(json != null){
                //Toast.makeText(Register.this, json.toString(), Toast.LENGTH_LONG).show();
                try{
                    success = json.getInt("code");
                    message = json.getString("message");
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
            if(success == 1){
                Log.d("Child Backedup!", "Child insert successful");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Log.d("Failure", "Child already exists");
                //Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }

    }



}
