package com.example.jil.firststep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jil.Users.Child;
import com.example.jil.androidrecyclerviewgridview.ItemObject;

public class ChildInfoFull extends AppCompatActivity {
    TextView child_Name;
    TextView child_Age;
    TextView child_DOB;
    ItemObject newdata = new ItemObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_manage_child_full);
        this.setTitle("First Name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        child_Name = (TextView)findViewById(R.id.sh_first_name);
        child_Age = (TextView)findViewById(R.id.child_age);
        child_DOB = (TextView)findViewById(R.id.child_dOB);
        newdata= (ItemObject)getIntent().getExtras().getSerializable("DATA");
        //if you have a TextView, for example...
        child_Name.setText(newdata.getName());
        child_Age.setText(newdata.getDescription());
    }
}
