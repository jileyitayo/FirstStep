package layout;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.jil.Chat.Util;
import com.example.jil.JSON.JSONParser;
import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.Users.Child;
import com.example.jil.Users.Users;
import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.androidrecyclerviewgridview.ListDocRecyclerViewAdapter;
import com.example.jil.androidrecyclerviewgridview.ListRecyclerViewAdapter;
import com.example.jil.androidrecyclerviewgridview.ListRecyclerViewHolders;
import com.example.jil.androidrecyclerviewgridview.RecyclerViewAdapter;
import com.example.jil.firststep.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JIL on 27/02/16.
 */
public class ManageChild extends Fragment{

    manage_R_Child.Communicator communicator;
    DAOChildApp childApp;
    Child child = new Child();
    private LinearLayoutManager linearLayout;
    public final List<Child> rowListItem = getAllItemList();
    FragmentManager newFm = getFragmentManager();
    ListRecyclerViewAdapter recyclerViewAdapterAdapter;
    SharedPreferences pref;


    private static String USERNAME = "username";
    private static String EMAIL = "email";
    private static String ROLE = "role";
    private static String CREATED_AT = "created_at";

    public ManageChild() {

    }

    @Override
    public void onAttach(Context context) {
        communicator = (manage_R_Child.Communicator) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manage_child_fragment, container, false);
        getActivity().setTitle("Manage Child");
        pref = getActivity().getSharedPreferences("loginRole", 0);
        String userRole = pref.getString("role", "missing Role");
        childApp = new DAOChildApp(this.getActivity());
        linearLayout = new LinearLayoutManager(getActivity());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayout);
        if (userRole.equals("Doctor")) {

            ListDocRecyclerViewAdapter recyclerViewAdapterAdapterDoc;
            recyclerViewAdapterAdapterDoc = new ListDocRecyclerViewAdapter(getActivity(), getUsersOnline());
            recyclerView.setAdapter(recyclerViewAdapterAdapterDoc);
        } else {
            recyclerViewAdapterAdapter = new ListRecyclerViewAdapter(getActivity(), getChildren());
            recyclerView.setAdapter(recyclerViewAdapterAdapter);
        }

        return view;
    }

    @Override
    public void onResume() {
        this.onCreate(null);
        super.onResume();
    }


    private List<Child> getAllItemList() {
        List<Child> allItems = new ArrayList<Child>();
        allItems.add(new Child("First Name", "Last Name", "Age", "Gender", null));
        return allItems;
    }

    private List<Child> getChildren() {
        childApp = new DAOChildApp(getActivity());
        List<Child> alist = childApp.getAllChildren();
        return alist;
    }

    private List<Users> getUsersOnline() {
        List<Users> usersList = new ArrayList<>();
        new getAllUsers(getActivity()).execute();


        /*
        JSONObject jsonObject = jsonParser.makeHttpRequest(Util.getAllUsers, "POST", null);
        String username = "", email = "", role = "", created_at = "";
        if (jsonObject != null) {
            //Toast.makeText(Register.this, json.toString(), Toast.LENGTH_LONG).show();
            try {
                peoples = jsonObject.getJSONArray("result");
                for (int i = 0; i < peoples.length(); i++) {
                    JSONObject c = peoples.getJSONObject(i);
                    username = c.getString(USERNAME);
                    email = c.getString(EMAIL);
                    role = c.getString(ROLE);
                    created_at = c.getString(CREATED_AT);

                    Users users = new Users();
                    users.setEmailAddress(email);
                    users.setRole(role);
                    users.setUsername(username);
                    users.setCreated_at(created_at);
                    usersList.add(users);
                    if (peoples.length() - 1 == i) {
                        Log.d("Users Retrieved!", "Users Retrieved");
                        Toast.makeText(getActivity(), "Users Retrieved", Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        */
        return usersList;
    }

    static class getAllUsers extends AsyncTask<String, List<Users>, JSONObject> {
        /*
         * Before starting background thread Show Progress Dialog
         * */
        public interface TaskCompleted {
            // Define data you like to return from AysncTask
            public List<Users> onTaskComplete(List<Users> result);
        }
        private TaskCompleted mCallback;
        ProgressDialog pDialog;
        JSONParser jsonParser = new JSONParser();
        JSONArray peoples;
        Context context;

        public getAllUsers(Context context)
        {
            this.context = context;
            this.mCallback = (TaskCompleted) context;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(context);
            pDialog.setMessage("getting All Users...");
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
                //HashMap<String, String> params = new HashMap();
                Log.d("request!", "starting");
                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(Util.getAllUsers, "POST", null);

                Log.d("Login result", json.toString());
                if (json != null) {
                    Log.d("Login result", json.toString());

                    return json;
                } else {
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
            List<Users> userslist1 = new ArrayList<>();
            String username = "", email = "", role = "", created_at = "";

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null) {
                //Toast.makeText(Register.this, json.toString(), Toast.LENGTH_LONG).show();
                try {
                    peoples = json.getJSONArray("result");
                    for (int i = 0; i < peoples.length(); i++) {
                        JSONObject c = peoples.getJSONObject(i);
                        username = c.getString(USERNAME);
                        email = c.getString(EMAIL);
                        role = c.getString(ROLE);
                        created_at = c.getString(CREATED_AT);

                        Users users = new Users();
                        users.setEmailAddress(email);
                        users.setRole(role);
                        users.setUsername(username);
                        users.setCreated_at(created_at);
                        userslist1.add(users);
                        if (peoples.length() - 1 == i) {
                            Log.d("Users Retrieved!", "Users Retrieved");
                            Toast.makeText(context, "Users Retrieved", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mCallback.onTaskComplete(userslist1);
        }
        private List<Users> retUsers(List<Users> usersAvailable) {
            return usersAvailable;
        }

    }


}
