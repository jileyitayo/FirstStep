package com.example.jil.firststep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class Vaccinations extends AppCompatActivity implements View.OnClickListener{

    private CheckBox vac1, vac2,vac3,vac4,vac5,vac6,vac7,vac8,vac9,vac10;
    String va1, va2 ,va3,va4,va5,va6,va7,va8,va9,va10;
    private Button btnSave1;
    public static ArrayList<String> vaccines = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccinations);

        vac1 = (CheckBox) findViewById(R.id.vaccination1);
        vac2 = (CheckBox) findViewById(R.id.vaccination2);
        vac3 = (CheckBox) findViewById(R.id.vaccination3);
        vac4 = (CheckBox) findViewById(R.id.vaccination4);
        vac5 = (CheckBox) findViewById(R.id.vaccination5);
        vac6 = (CheckBox) findViewById(R.id.vaccination6);
        vac7 = (CheckBox) findViewById(R.id.vaccination7);
        vac8 = (CheckBox) findViewById(R.id.vaccination8);
        vac9 = (CheckBox) findViewById(R.id.vaccination9);
        vac10 = (CheckBox) findViewById(R.id.vaccination10);
        vac1.setOnClickListener(this);
        vac2.setOnClickListener(this);
        vac3.setOnClickListener(this);
        vac4.setOnClickListener(this);
        vac5.setOnClickListener(this);
        vac6.setOnClickListener(this);
        vac7.setOnClickListener(this);
        vac8.setOnClickListener(this);
        vac9.setOnClickListener(this);
        vac10.setOnClickListener(this);
        btnSave1 = (Button) findViewById(R.id.btnSave);
        btnSave1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.vaccination1:
                va1 = getResources().getString(R.string.vaccination1);
                break;
            case R.id.vaccination2:
                va2 = getResources().getString(R.string.vaccination2);
                break;
            case R.id.vaccination3:
                va3 = getResources().getString(R.string.vaccination3);
                break;
            case R.id.vaccination4:
                va4 = getResources().getString(R.string.vaccination4);
                break;
            case R.id.vaccination5:
                va5 = getResources().getString(R.string.vaccination5);
                break;
            case R.id.vaccination6:
                va6 = getResources().getString(R.string.vaccination6);
                break;
            case R.id.vaccination7:
                va7 = getResources().getString(R.string.vaccination7);
                break;
            case R.id.vaccination8:
                va8 = getResources().getString(R.string.vaccination8);
                break;
            case R.id.vaccination9:
                va9 = getResources().getString(R.string.vaccination9);
                break;
            case R.id.vaccination10:
                va10 = getResources().getString(R.string.vaccination10);
                break;
            case R.id.btnSave:
                if (vac1.isChecked()) {
                    vaccines.add(va1);
                }
                if (vac2.isChecked()) {
                    vaccines.add(va2);
                }
                if (vac3.isChecked()) {
                    vaccines.add(va3);
                }
                if (vac4.isChecked()) {
                    vaccines.add(va4);
                }
                if (vac5.isChecked()) {
                    vaccines.add(va5);
                }
                if (vac6.isChecked()) {
                    vaccines.add(va6);
                }
                if (vac7.isChecked()) {
                    vaccines.add(va7);
                }
                if (vac8.isChecked()) {
                    vaccines.add(va8);
                }
                if (vac9.isChecked()) {
                    vaccines.add(va9);
                }
                if (vac10.isChecked()) {
                    vaccines.add(va10);
                }

               // btnSave1.setText(String.valueOf(vaccines.size()));
                finish();
                break;

        }
    }
}
