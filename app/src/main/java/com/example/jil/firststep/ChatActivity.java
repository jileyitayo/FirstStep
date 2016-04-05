package com.example.jil.firststep;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.jil.Chat.ChatAdapter;
import com.example.jil.Chat.ChatObject;
import com.example.jil.Chat.Util;
import com.example.jil.JSON.JSONParser;
import com.example.jil.Users.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.callback.CallbackHandler;

import okhttp3.OkHttpClient;
import okhttp3.OkUrlFactory;

public class ChatActivity extends AppCompatActivity {
    String editText_mail_id;
    EditText editText_chat_message;
    ListView listView_chat_messages;
    Button button_send_chat;
    Spinner spReceiver;
    List<ChatObject> chat_list;
    BroadcastReceiver recieve_chat;
    String message;
    String usersEmail;
    //JSON parser class
    JSONParser jsonParser = new JSONParser();
    private ProgressDialog pDialog;
    SharedPreferences pref;
    String username, email, role;
    private static final String TAG_STATUS = "status";
    private static final String TAG_INFO = "info";
    ArrayList<String> userEmail = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        pref = getSharedPreferences("loginRole", 0);
        username = pref.getString("username", "missing username");
        role = pref.getString("role", "missing role");
        email = pref.getString("email", "missing email");
        setTitle(username.toUpperCase());
        //editText_mail_id = (EditText) findViewById(R.id.editText_mail_id);
        editText_chat_message = (EditText) findViewById(R.id.editText_chat_message);
        listView_chat_messages = (ListView) findViewById(R.id.listView_chat_messages);
        spReceiver = (Spinner) findViewById(R.id.spinnerRecievers);
        spReceiver.setPrompt("Select contact");
        new GenerateUsers().execute();
        spReceiver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                editText_mail_id = userEmail.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button_send_chat = (Button) findViewById(R.id.button_send_chat);
        button_send_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// send chat message to server
                message = editText_chat_message.getText().toString();
                showChat("sent", message);
                new SendMessage().execute();
                editText_chat_message.setText("");
            }
        });
        recieve_chat = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String message = intent.getStringExtra("message");
                Log.d("pavan", "in local broad " + message);
                showChat("recieve", message);
            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(recieve_chat, new IntentFilter("message_recieved"));
    }

    private void showChat(String type, String message) {
        if (chat_list == null || chat_list.size() == 0) {
            chat_list = new ArrayList<ChatObject>();
        }
        chat_list.add(new ChatObject(message, type));
        ChatAdapter chatAdapter = new ChatAdapter(ChatActivity.this, R.layout.chat_view, chat_list);
        listView_chat_messages.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class SendMessage extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
// TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
// TODO Auto-generated method stub
            String url = Util.send_chat_url + "?email_id=" + editText_mail_id + "&message=" + message;
            Log.i("pavan", "url" + url);
            OkHttpClient client_for_getMyFriends = new OkHttpClient();
            String response = null;
// String response=Utility.callhttpRequest(url);
            try {
                url = url.replace(" ", "%20");
                response = callOkHttpRequest(new URL(url),
                        client_for_getMyFriends);
                for (String subString : response.split("<script", 2)) {
                    response = subString;
                    break;
                }
            } catch (MalformedURLException e) { // TODO Auto-generated catch block e.printStackTrace();
            } catch (IOException e) {
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


    private class GenerateUsers extends AsyncTask<Void, Void, ArrayList<Users>>{
        String myRole = role;
        //we want the progress dialog to show before it fetches data
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(ChatActivity.this);
            pDialog.setMessage("loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Users> doInBackground(Void... args) {
            //check for success tag
            ArrayList<Users> usersList = new ArrayList<Users>();
            Users getUsers = new Users();
            int status;
            try{
                //Building parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("role", myRole);

                Log.d("request!", "starting");
                //get product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(Util.fetchUsers, "GET", params);
                //check your log for json response
                //Log.d("Login attempt", json.toString());

                try {
                /* Checking for SUCCESS TAG */
                    status = json.getInt(TAG_STATUS);
                    if (status == 1) {
                        JSONArray JAStuff = json.getJSONArray(TAG_INFO);
                        usersList = getUsers.fromJson(JAStuff);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return usersList;
        }

        //after completing background task Dismiss the progess dialog
        @Override
        protected void onPostExecute(ArrayList<Users> lists) {
            if(pDialog != null && pDialog.isShowing()){
                pDialog.dismiss();
            }
            ArrayAdapter<String> my_adapter = new  ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,getUsersDetails(lists));
            my_adapter.setDropDownViewResource(R.layout.spinner_item);
            spReceiver.setAdapter(my_adapter);
            userEmail = getUsersEmail(lists);
            myMethod(lists);
        }
    }

    private ArrayList<String> getUsersDetails(ArrayList<Users> listsOfUsers)
    {
        ArrayList<String> adaper12 = new ArrayList<>();
        for(Users user : listsOfUsers )
        {
            if(user.getRole().equals("Doctor") )
            {
                adaper12.add("Dr " + user.getUsername());
            }
            else
            {
                adaper12.add(user.getUsername());
            }


        }
        return adaper12;
    }
    private ArrayList<String> getUsersEmail(ArrayList<Users> listsOfUsers)
    {
        ArrayList<String> adapter13 = new ArrayList<>();
        for(Users user : listsOfUsers )
        {
                adapter13.add(user.getEmailAddress());
        }
        return adapter13;
    }


    private ArrayList<Users> myMethod(ArrayList<Users> myValue) {
        //handle value
        return myValue;
    }

}
