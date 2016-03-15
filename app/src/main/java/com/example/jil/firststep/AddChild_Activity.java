package com.example.jil.firststep;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.SQLite.DAOHealthApp;
import com.example.jil.Users.Child;
import com.example.jil.Users.Users;

import java.util.Calendar;

public class AddChild_Activity extends AppCompatActivity {

    Child child = new Child();
    Button btnShowDate, btnSubmit, btnMoreInfo;
    EditText etFirstName, etLastName, etGender;
    int year_a, month_a, day_a;
    //Spinner spinGender;
    static final int DIALOG_ID = 0;
    DAOChildApp childApp;
    DAOHealthApp healthApp;
    TextView tvDate;
    Users owner = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_);
        Toolbar toolbarGreen = (Toolbar) findViewById(R.id.toolbarGreen);
        setSupportActionBar(toolbarGreen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        childApp = new DAOChildApp(this);
        healthApp = new DAOHealthApp(this);
        owner = healthApp.getExistingUsers();
        //spinGender = (Spinner) findViewById(R.id.spinnerGender);
        tvDate = (TextView) findViewById(R.id.lblDate);
        etFirstName = (EditText) findViewById(R.id.ETFName);
        etLastName = (EditText) findViewById(R.id.ETLName);
        etGender = (EditText) findViewById(R.id.ETgender);

        this.setTitle("New Child");

        FloatingActionButton btnSubmit = (FloatingActionButton) findViewById(R.id.btnSubmit);
        final Calendar cal = Calendar.getInstance();
        year_a = cal.get(Calendar.YEAR);
        month_a = cal.get(Calendar.MONTH);
        day_a = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();

        btnMoreInfo = (Button) findViewById(R.id.btnMoreInfo);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String.valueOf(spinGender.getSelectedItem())
                child = getChildFromLayout(owner.getId(), owner.getUsername(), etFirstName.getText().toString(), etLastName.getText().toString(), etGender.getText().toString(), tvDate.getText().toString());
                if (TextUtils.isEmpty(etFirstName.getText().toString())) {
                    etFirstName.setError(getString(R.string.firstName_add));
                } else if (TextUtils.isEmpty(etLastName.getText().toString())) {
                    etLastName.setError(getString(R.string.lastName_add));
                }
                else if (TextUtils.isEmpty(etGender.getText().toString())) {
                    etGender.setError(getString(R.string.gender_add));
                }
                 /*
                else if(!spinGender.isSelected()){
                    Snackbar.make(v, getString(R.string.gender_add) + "!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                */
                else if (tvDate.getText().toString().isEmpty()) {
                    Snackbar.make(v, getString(R.string.dOB_add), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    if (!childApp.isChildPresent(child)) {
                        //unsuccessful
                        long insertedUser = childApp.InsertChild(child, owner);
                        if (insertedUser > 0) {
                            Snackbar.make(v, "Successfully Added " + child.getfirstName() + " " + child.getLastName() + "!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            clearEtValues();
                        } else {
                            Snackbar.make(v, "Not Successfull!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }


                    }
                }

            }
        });
        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddChild_Activity.this, More_Info.class);
                AddChild_Activity.this.startActivity(intent);
            }
        });

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

    public void clearEtValues()
    {
        tvDate.setText("");
        etFirstName.setText("");
        etLastName.setText("");
        etGender.setText("");
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

        return super.onOptionsItemSelected(item);
    }
}
