package com.example.jil.firststep;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jil.Dialog.DialogControl;
import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.Users.Child;
import com.example.jil.Users.MoreInformationModel;
import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.androidrecyclerviewgridview.ListRecyclerViewAdapter;
import com.example.jil.androidrecyclerviewgridview.MoreInfoAdptChilFull;

import java.util.ArrayList;
import java.util.List;

public class ChildInfoFull extends AppCompatActivity {
    TextView child_Name;
    TextView child_Age;
    TextView child_gender;
    ItemObject newdata = new ItemObject();
    DAOMoreInformation daoMoreInformation;
    List<MoreInformationModel> model = new ArrayList<>();
    String[] childnames;
    Child child = new Child();
    LinearLayoutManager linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_manage_child_full);
        newdata= (ItemObject)getIntent().getExtras().getSerializable("DATA");
        daoMoreInformation = new DAOMoreInformation(this);
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbarBluish);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle(newdata.getName());
        childnames = splitString(newdata.getName());
        child.setFirstName(childnames[0]);
        child.setLastName(childnames[1]);
        model = daoMoreInformation.getSingleChildInfo(child);
        //child_Name = (TextView)findViewById(R.id.sh_first_name);
        child_Age = (TextView)findViewById(R.id.child_age);
        child_gender = (TextView)findViewById(R.id.child_gender);
        //if you have a TextView, for example...

        //child_Name.setText(newdata.getName());
        String gender = newdata.getDescription().substring(newdata.getDescription().indexOf("Gender: ") + 8, newdata.getDescription().lastIndexOf("e") + 1);
        String Age = newdata.getDescription().substring(newdata.getDescription().indexOf("Age: ") + 5, newdata.getDescription().lastIndexOf(","));
        child_gender.setText(gender);
        child_Age.setText(Age);


        linearLayout = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mChild_info_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayout);
        MoreInfoAdptChilFull recyclerViewAdapterAdapter = new MoreInfoAdptChilFull(this, model);
        recyclerView.setAdapter(recyclerViewAdapterAdapter);
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

    public String[] splitString(String word)
    {
        String[] mystring = word.split(" ");
        return mystring;
    }

}
