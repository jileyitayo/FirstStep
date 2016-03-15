package com.example.jil.firststep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
        newdata= (ItemObject)getIntent().getExtras().getSerializable("DATA");
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbarBluish);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle(newdata.getName());
        //child_Name = (TextView)findViewById(R.id.sh_first_name);
        child_Age = (TextView)findViewById(R.id.child_age);
        child_DOB = (TextView)findViewById(R.id.child_dOB);
        //if you have a TextView, for example...

        //child_Name.setText(newdata.getName());
        child_DOB.setText(newdata.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}