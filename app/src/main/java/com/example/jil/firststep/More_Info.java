package com.example.jil.firststep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jil.SQLite.DAOInfoMini;
import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.androidrecyclerviewgridview.MoreInfoAdpt;

import java.util.ArrayList;
import java.util.List;

public class More_Info extends AppCompatActivity {


    EditText etitle, edetails;
    Button bViewInfo, bSave;
    ItemObject itemObject = new ItemObject();
    LinearLayoutManager linearLayout;
    DAOInfoMini infoMini;
    Spinner spinInfo;
    String etTitle;
    //SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more__info);
        this.setTitle("Add More Information");
        Toolbar toolbarBluish = (Toolbar) findViewById(R.id.toolbarBluish);
        setSupportActionBar(toolbarBluish);

        etitle = (EditText) findViewById(R.id.ETitle);
        edetails = (EditText) findViewById(R.id.ETDetails);
        bSave = (Button) findViewById(R.id.btnMoreInfoFull);
        spinInfo = (Spinner) findViewById(R.id.spinnerInformation);
        infoMini = new DAOInfoMini(this);
       // mSettings = getSharedPreferences("jil", 0);
        String[] infos = spinInfo.getResources().getStringArray(R.array.installed_information);
        spinInfo.setSelection(0);
        spinInfo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    etTitle = String.valueOf(spinInfo.getSelectedItem());
                    etitle.setText(parent.getSelectedItem().toString().trim());
                    edetails.setFocusable(true);
                }else{
                    etitle.setText("");
                    Toast.makeText(More_Info.this, "Select a title or type in the title", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etitle.getText().toString().trim())) {
                    etitle.setError(getString(R.string.error_info_title));
                } else if (TextUtils.isEmpty(edetails.getText().toString().trim())) {
                    edetails.setError(getString(R.string.error_info_details));
                }
                else
                {
                    itemObject.setName(etitle.getText().toString().trim());
                    itemObject.setDescription(edetails.getText().toString().trim());
                    if((itemObject == null)) {
                        Snackbar.make(v, "Input Title and Details of Child Information!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                    else {
                        etitle.setText("");
                        edetails.setText("");
                        /*SharedPreferences.Editor editor = mSettings.edit();
                        editor.putString("inputTitle", itemObject.getName());
                        editor.putString("inputDetails", itemObject.getDescription());
                        editor.apply();*/
                        infoMini.Insert(itemObject);
                        finish();
                    }
                }
            }
        });
    }
}
