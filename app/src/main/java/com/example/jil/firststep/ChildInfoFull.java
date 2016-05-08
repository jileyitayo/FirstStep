package com.example.jil.firststep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jil.Dialog.DialogControl;
import com.example.jil.Dialog.SummaryEditDControl;
import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.SQLite.DAOInfoMini;
import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.SQLite.DAOVaccination;
import com.example.jil.SQLite.DBHelper;
import com.example.jil.SQLite.DBTables;
import com.example.jil.Users.Child;
import com.example.jil.Users.ChildVaccination;
import com.example.jil.Users.MoreInformationModel;
import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.androidrecyclerviewgridview.ListRecyclerViewAdapter;
import com.example.jil.androidrecyclerviewgridview.MoreInfoAdpt;
import com.example.jil.androidrecyclerviewgridview.MoreInfoAdptChilFull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChildInfoFull extends AppCompatActivity implements View.OnClickListener, SummaryEditDControl.TDialogListener{
    TextView tFname, tLname, tDOB, tHeight, tWeight,tlocation,tAllergies,tParents,tGender,ltVText;
    String uriPath, tGender1;
    ListView lv;
    int TAG = 0;
    private DBHelper dbHelper;
    ArrayList<String> listV = new ArrayList<>();
    ImageButton img;
    DAOChildApp childApp;
    ArrayAdapter<String> itemsAdapter;
    List<ItemObject> myList = new ArrayList<>();
    Child newdata = new Child();
    DAOMoreInformation daoMoreInformation;
    DAOVaccination daoVaccination;
    List<MoreInformationModel> model = new ArrayList<>();
    String[] childnames;
    SharedPreferences preferences;
    Child child = new Child();
    LinearLayoutManager linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_view);
        newdata= (Child)getIntent().getExtras().getSerializable("DATA");
        daoMoreInformation = new DAOMoreInformation(this);
        this.setTitle(newdata.getfirstName());
        daoVaccination = new DAOVaccination(this);
        dbHelper = new DBHelper(this);
        preferences = getSharedPreferences("dialogg", 0);
        tGender = (TextView) findViewById(R.id.radioSex);
        tFname  =(TextView) findViewById(R.id.TVFName);
        tLname = (TextView) findViewById(R.id.TVLName);
        tDOB = (TextView) findViewById(R.id.date);
        tHeight = (TextView) findViewById(R.id.TVHeight);
        tWeight = (TextView) findViewById(R.id.TVWeight);
        tlocation = (TextView) findViewById(R.id.TVAddLocation);
        tAllergies = (TextView) findViewById(R.id.TVAllergies);
        tParents = (TextView) findViewById(R.id.TVParents);
        //lv = (ListView) findViewById(R.id.listVIewVaccination);
        img = (ImageButton) findViewById(R.id.profilePic);
        ltVText = (TextView) findViewById(R.id.list_viewtext);
        //listV =  getIntent().getExtras().getStringArrayList("lists");
        FloatingActionButton btnSubmit = (FloatingActionButton) findViewById(R.id.btnSubmit2);
        btnSubmit.setVisibility(View.GONE);
        tFname.setText(newdata.getfirstName().toUpperCase());
        tLname.setText(newdata.getLastName().toUpperCase());
        tGender.setText(newdata.getGender().toUpperCase());
        tDOB.setText(newdata.getDateOfBirth().toUpperCase());
        tHeight.setText(newdata.getHeight().toUpperCase());
        tWeight.setText(newdata.getWeight().toUpperCase());
        tlocation.setText(newdata.getAddLocation().toUpperCase());
        tAllergies.setText(newdata.getAllergies().toUpperCase());
        tParents.setText(newdata.getParentName().toUpperCase());
        uriPath = newdata.getImg_path();

        /*
        itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Vaccinations.vaccines);
        lv.setAdapter(itemsAdapter);
        */
        if(uriPath != null)
        {
            retrievePath(uriPath);
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.profilePic)
                {
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("uriPath", uriPath.trim());
                    Intent intent = new Intent(ChildInfoFull.this, ViewProfPic.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }
            }
        });

        daoMoreInformation = new DAOMoreInformation(this);
        myList = daoMoreInformation.getAllChildInfoPat(newdata);
        if(myList == null)
        {
            myList = new ArrayList<>();
            ltVText.setText("");

        }
        else if(daoMoreInformation.getAllChildInfoPat(newdata).isEmpty())
        {
            myList = new ArrayList<>();
            ltVText.setText("");
        }
        else
        {
            ltVText.setText("More Information");
        }
        linearLayout = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_list_view_info);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayout);
        MoreInfoAdpt recyclerViewAdapterAdapter = new MoreInfoAdpt(this, myList);
        recyclerView.setAdapter(recyclerViewAdapterAdapter);
        //model = daoMoreInformation.getSingleChildInfo(child);
        //child_Name = (TextView)findViewById(R.id.sh_first_name);
        //if you have a TextView, for example...


        /*
        BitmapDrawable background = new BitmapDrawable (BitmapFactory.decodeResource(getResources(), R.raw.actionbar_background));
 background.setTileModeX(android.graphics.Shader.TileMode.REPEAT);
 actionBar.setBackgroundDrawable(background);

 Bitmap bMap = BitmapFactory.decodeResource(res, R.drawable.action_bar_bg);
BitmapDrawable actionBarBackground = new BitmapDrawable(res, bMap);
ActionBar bar = getActionBar();
bar.setBackgroundDrawable(actionBarBackground);
        */
        /*
        linearLayout = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mChild_info_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayout);
        MoreInfoAdptChilFull recyclerViewAdapterAdapter = new MoreInfoAdptChilFull(this, model);
        recyclerView.setAdapter(recyclerViewAdapterAdapter);
        */
        tFname.setOnClickListener(this);


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
        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private ChildVaccination inputVaccinations(ArrayList<String> vaccination) {
        ChildVaccination childVaccination = new ChildVaccination();
        for (int i = 0; i< vaccination.size(); i++)
        {
            if(i == 0)
            {
                childVaccination.setVaccination1(vaccination.get(i));
            }
            if(i == 1)
            {
                childVaccination.setVaccination2(vaccination.get(i));
            }
            if(i == 2)
            {
                childVaccination.setVaccination3(vaccination.get(i));
            }
            if(i == 3)
            {
                childVaccination.setVaccination4(vaccination.get(i));
            }
            if(i == 4)
            {
                childVaccination.setVaccination5(vaccination.get(i));
            }
            if(i == 5)
            {
                childVaccination.setVaccination6(vaccination.get(i));
            }
            if(i == 6)
            {
                childVaccination.setVaccination7(vaccination.get(i));
            }
            if(i == 7)
            {
                childVaccination.setVaccination8(vaccination.get(i));
            }
            if(i == 8)
            {
                childVaccination.setVaccination9(vaccination.get(i));
            }
            if(i == 9)
            {
                childVaccination.setVaccination10(vaccination.get(i));
            }

        }
        return childVaccination;
    }
    public void retrievePath(String path) {
        File imgFile = new File(path);
        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        final int REQUIRED_SIZE = 200;//200
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);
        img.setImageBitmap(bm);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("childFN", tFname.getText().toString().trim());
        editor.putString("childLN", tLname.getText().toString().trim());
        editor.putString("childDOB", tDOB.getText().toString().trim());
        switch (v.getId())
        {
            case R.id.TVFName:
                TAG = 1;
                editor.putString("name", DBTables.Children.FIRST_NAME);
                editor.putString("info", "First Name");
                editor.apply();
                Toast.makeText(this, "just selected this text", Toast.LENGTH_SHORT).show();
                break;

            case R.id.TVLName:
                TAG = 2;
                editor.putString("name", DBTables.Children.FIRST_NAME);
                editor.putString("info", "Last Name");
                editor.apply();
                Toast.makeText(this, "just selected this text", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        showNoticeDialog();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String data) {
        switch (TAG)
        {
            case 1:
                tFname.setText(data);
                break;
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new SummaryEditDControl();
        dialog.show(getSupportFragmentManager(), "DialogFragment12");
    }
}
