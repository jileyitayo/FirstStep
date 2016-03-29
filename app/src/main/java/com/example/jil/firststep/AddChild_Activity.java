package com.example.jil.firststep;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jil.Dialog.MyMoreInfoDialog;
import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.SQLite.DAOHealthApp;
import com.example.jil.SQLite.DAOInfoMini;
import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.Users.Child;
import com.example.jil.Users.ChildVaccination;
import com.example.jil.Users.MoreInformationModel;
import com.example.jil.Users.Users;
import com.example.jil.androidrecyclerviewgridview.ItemObject;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import layout.MainActivityFragment;

public class AddChild_Activity extends AppCompatActivity {
    Child child = new Child();
    Button btnShowDate, btnMoreInfo, btnMoreInfoFull, btnVaccination;
    EditText etFirstName, etLastName, etWeight;
    ImageButton btnProfile;
    String etGender;
    int year_a, month_a, day_a;
    RadioGroup spinGender;
    private RadioButton radioSexButton;
    static final int DIALOG_ID = 0;
    DAOChildApp childApp;
    DAOHealthApp healthApp;
    DAOInfoMini mini;
    MoreInformationModel infoModel = new MoreInformationModel();
    DAOMoreInformation moreInformation;
    List<ItemObject> myList = new ArrayList<>();
    ItemObject object = new ItemObject();
    TextView tvDate;
    Users owner = new Users();
    //ArrayList<String> newData = new ArrayList<>();
    //SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_);
        Toolbar toolbarGreen = (Toolbar) findViewById(R.id.toolbarGreen);
        setSupportActionBar(toolbarGreen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        childApp = new DAOChildApp(this);
        healthApp = new DAOHealthApp(this);
        moreInformation = new DAOMoreInformation(this);
        mini = new DAOInfoMini(this);
        owner = healthApp.getExistingUsers();
        spinGender = (RadioGroup) findViewById(R.id.radioSex);
        tvDate = (TextView) findViewById(R.id.lblDate);
        etFirstName = (EditText) findViewById(R.id.ETFName);
        etLastName = (EditText) findViewById(R.id.ETLName);
        btnProfile = (ImageButton) findViewById(R.id.profilePic);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView myTExt = (TextView) findViewById(R.id.lblDate);
                myTExt.setText(String.valueOf(Vaccinations.vaccines.size()));
            }
        });
        //etWeight = (EditText) findViewById(R.id.ETWeight);






        this.setTitle("New Child");
        FloatingActionButton btnSubmit = (FloatingActionButton) findViewById(R.id.btnSubmit);
        final Calendar cal = Calendar.getInstance();
        year_a = cal.get(Calendar.YEAR);
        month_a = cal.get(Calendar.MONTH);
        day_a = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();

        //btnMoreInfo = (Button) findViewById(R.id.btnMoreInfo);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = spinGender.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(selectedId);
                //String.valueOf(spinGender.getSelectedItem())
                etGender = radioSexButton.getText().toString();
                child = getChildFromLayout(owner.getId(), owner.getUsername(), etFirstName.getText().toString().trim(), etLastName.getText().toString().trim(), etGender, tvDate.getText().toString().trim());
                if (TextUtils.isEmpty(etFirstName.getText().toString().trim())) {
                    etFirstName.setError(getString(R.string.firstName_add));
                } else if (TextUtils.isEmpty(etLastName.getText().toString().trim())) {
                    etLastName.setError(getString(R.string.lastName_add));
                } else if (tvDate.getText().toString().isEmpty()) {
                    Snackbar.make(v, getString(R.string.dOB_add), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    final View view = v;
                    new AsyncTask<Void, Void, Long>()
                    {
                        long insertedUser;
                        @Override
                        protected Long doInBackground(Void... params) {

                            if (!childApp.isChildPresent(child)) {
                                myList = mini.getAll();
                                for (ItemObject object: myList)
                                {
                                    if(!(object == null)) {
                                        infoModel.setInfo_title(object.getName());
                                        infoModel.setInfo_details(object.getDescription());
                                        moreInformation.InsertInfo(infoModel, child, owner);
                                    }
                                }
                                //unsuccessful
                                insertedUser = childApp.InsertChild(child, owner);

                            }
                            return insertedUser;
                        }

                        @Override
                        protected void onPostExecute(Long message) {
                            if (insertedUser > 0) {
                                //message = "Successfully Added " + child.getfirstName() + " " + child.getLastName() + "!";
                                Snackbar.make(view, "Successfully Added " + child.getfirstName() + " " + child.getLastName() + "!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                clearEtValues();
                                mini.delete();
                            } else {
                                Snackbar.make(view, "Not Successfull!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            }
                            super.onPostExecute(message);
                        }
                    }.execute(null,null,null);

                }
                Vaccinations.vaccines.clear();
            }
        });

        btnVaccination = (Button) findViewById(R.id.vaccination);
        btnVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddChild_Activity.this, Vaccinations.class));
            }
        });

    }

    @Override
    protected void onDestroy() {
        mini.delete();
        super.onDestroy();
    }

    public void showDialogOnButtonClick() {
        btnShowDate = (Button) findViewById(R.id.btnShowDate);
        btnShowDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    public void clearEtValues() {
        tvDate.setText("");
        etFirstName.setText("");
        etLastName.setText("");
        //spinGender.setSelected(false);
    }


    private Child getChildFromLayout(long userid, String username, String firstName, String lastName, String gender, String DOB) {
        Child child1 = new Child();
        child1.setFirstName(firstName);
        child1.setLastName(lastName);
        child1.setGender(gender);
        child1.setDateOfBirth(DOB);
        child1.setUserId(userid);
        child1.setUsername(username);
        return child1;
    }
/*
    private ChildVaccination getVaccinations(ArrayList<String> vaccinations )
    {
        ChildVaccination vaccination = new ChildVaccination();

    }
*/
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_a, month_a, day_a);
        return null;
    }


    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_a = year;
            month_a = monthOfYear + 1;
            day_a = dayOfMonth;
            String dateSet = day_a + "/" + month_a + "/" + year_a;
            tvDate.setText(dateSet);
        }
    };

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
            return true;
        }
        if (id == R.id.profile) {
            startActivity(new Intent(AddChild_Activity.this, Profile_Activity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String[] getChildInfoTitle() {
        moreInformation = new DAOMoreInformation(this);
        List<MoreInformationModel> alist = moreInformation.getAllChildInfo();
        String[] list = new String[alist.size()];

        for (int i = 0; i < alist.size(); i++) {
            String title = alist.get(i).getInfo_title();
            list[i] = title;
        }
        moreInformation.close();
        return list;
    }

    private String[] getChildInfoDetails() {
        moreInformation = new DAOMoreInformation(this);
        List<MoreInformationModel> alist = moreInformation.getAllChildInfo();
        String[] list = new String[alist.size()];

        for (int i = 0; i < alist.size(); i++) {
            String details = alist.get(i).getInfo_details();
            list[i] = details;
        }
        moreInformation.close();
        return list;
    }


    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new MyMoreInfoDialog();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    public void showInfoDialog(android.app.DialogFragment dialog1) {
        // Create an instance of the dialog fragment and show it
        //android.app.DialogFragment dialog1 = new AddedInfoList();
        dialog1.show(getFragmentManager(), "D_DialogFragment");
    }

    /*
        private class AsyncTaskRunner extends AsyncTask<String, String, String> {

            private String resp;

            @Override
            protected String doInBackground(String... params) {
                try {
                    // Do your long operations here and return the result
                    myList = mini.getAll();

                    //unsuccessful
                    long insertedUser = childApp.InsertChild(child, owner);
                    if (insertedUser > 0) {
                        for (ItemObject object: myList)
                        {
                            if(!(object == null)) {
                                infoModel.setInfo_title(object.getName());
                                infoModel.setInfo_details(object.getDescription());
                                moreInformation.InsertInfo(infoModel, child, owner);
                            }
                        }
                        Snackbar.make(, "Successfully Added " + child.getfirstName() + " " + child.getLastName() + "!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        clearEtValues();
                        mini.delete();
                    } else {
                        Snackbar.make(v, "Not Successfull!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    resp = e.getMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                    resp = e.getMessage();
                }
                return resp;
            }

            /*
             * (non-Javadoc)
             *
             * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
             */
    //  @Override
    protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation
        //   finalResult.setText(result);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#onPreExecute()
     */
    // @Override
    protected void onPreExecute() {
        // Things to be done before execution of long running operation. For
        // example showing ProgessDialog
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#onProgressUpdate(Progress[])
     */
    // @Override
    protected void onProgressUpdate(String... text) {
        // finalResult.setText(text[0]);
        // Things to be done while execution of long running operation is in
        // progress. For example updating ProgessDialog
    }
}

