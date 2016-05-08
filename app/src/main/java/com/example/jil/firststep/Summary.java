package com.example.jil.firststep;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.SQLite.DAOHealthApp;
import com.example.jil.SQLite.DAOInfoMini;
import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.SQLite.DAOVaccination;
import com.example.jil.Users.Child;
import com.example.jil.Users.ChildVaccination;
import com.example.jil.Users.MoreInformationModel;
import com.example.jil.Users.Users;
import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.androidrecyclerviewgridview.MoreInfoAdpt;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Summary extends AppCompatActivity {
    String uriPath, tGender1;
    ListView lv;
    ArrayList<String> listV = new ArrayList<>();
    ImageButton img;
    Child child = new Child();
    DAOChildApp childApp;
    DAOHealthApp healthApp;
    DAOInfoMini mini;
    LinearLayoutManager linearLayout;
    List<ItemObject> myList = new ArrayList<ItemObject>();
    Users owner = new Users();
    MoreInformationModel infoModel = new MoreInformationModel();
    DAOMoreInformation moreInformation;
    DAOVaccination daoVaccination;
    TextView tFname, tLname, tDOB, tHeight, tWeight,tlocation,tAllergies,tParents,tGender, ltVText;
    ArrayAdapter<MoreInformationModel> itemsAdapter;
    int drawable = R.drawable.ic_person_black_24dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_view);
        childApp = new DAOChildApp(this);
        healthApp = new DAOHealthApp(this);
        daoVaccination = new DAOVaccination(this);
        moreInformation = new DAOMoreInformation(this);
        mini = new DAOInfoMini(this);
        owner = healthApp.getExistingUsers();
        tFname  =(TextView) findViewById(R.id.TVFName);
        tGender = (TextView) findViewById(R.id.radioSex);
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
        tFname.setText(getIntent().getExtras().getString("tFName").toUpperCase());
        tLname.setText(getIntent().getExtras().getString("tLName").toUpperCase());
        tGender.setText(getIntent().getExtras().getString("tGender").toUpperCase());
        tDOB.setText(getIntent().getExtras().getString("tDOB").toUpperCase());
        tHeight.setText(getIntent().getExtras().getString("tHeight").toUpperCase());
        tWeight.setText(getIntent().getExtras().getString("tWeight").toUpperCase());
        tlocation.setText(getIntent().getExtras().getString("tlocation").toUpperCase());
        tAllergies.setText(getIntent().getExtras().getString("tAllergies").toUpperCase());
        tParents.setText(getIntent().getExtras().getString("tParents").toUpperCase());
        uriPath = getIntent().getExtras().getString("uriPath");
        //itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Vaccinations.vaccines);
        //lv.setAdapter(itemsAdapter);
        if(uriPath != null)
        {
            retrievePath(uriPath);
        }
        else
        {
            img.setImageResource(drawable);
            uriPath = String.valueOf(drawable);
        }

        mini = new DAOInfoMini(this);
        myList = mini.getAll();
        if(myList == null)
        {
            myList = new ArrayList<>();
            ltVText.setText("");
        }
        else if(mini.getAll().isEmpty())
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

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.profilePic)
                {
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("uriPath", uriPath.trim());
                    Intent intent = new Intent(Summary.this, ViewProfPic.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tHeight.getText().toString().trim().isEmpty())
                {
                    child = getChildFromLayout(owner.getId(), owner.getUsername(), tFname.getText().toString().trim(), tLname.getText().toString().trim(),
                            tGender.getText().toString().trim(), tDOB.getText().toString().trim(), uriPath, tHeight.getText().toString().trim() + "m", tWeight.getText().toString().trim(),
                            tlocation.getText().toString().trim(), tAllergies.getText().toString().trim(),
                            tParents.getText().toString().trim());
                }
                if(!tWeight.getText().toString().trim().isEmpty())
                {
                    child = getChildFromLayout(owner.getId(), owner.getUsername(), tFname.getText().toString().trim(), tLname.getText().toString().trim(),
                            tGender.getText().toString().trim(), tDOB.getText().toString().trim(), uriPath, tHeight.getText().toString().trim(), tWeight.getText().toString().trim() + "kg",
                            tlocation.getText().toString().trim(), tAllergies.getText().toString().trim(),
                            tParents.getText().toString().trim());
                }
                else if(tHeight.getText().toString().trim().isEmpty() && tWeight.getText().toString().trim().isEmpty())
                {
                    child = getChildFromLayout(owner.getId(), owner.getUsername(), tFname.getText().toString().trim(), tLname.getText().toString().trim(),
                            tGender.getText().toString().trim(), tDOB.getText().toString().trim(), uriPath, tHeight.getText().toString().trim(), tWeight.getText().toString().trim(),
                            tlocation.getText().toString().trim(), tAllergies.getText().toString().trim(),
                            tParents.getText().toString().trim());
                }
                /*child = getChildFromLayout(owner.getId(), owner.getUsername(), tFname.getText().toString().trim(), tLname.getText().toString().trim(),
                        tGender.getText().toString().trim(), tDOB.getText().toString().trim(), uriPath, tHeight.getText().toString().trim() + "m", tWeight.getText().toString().trim() + "kg",
                        tlocation.getText().toString().trim(), tAllergies.getText().toString().trim(),
                        tParents.getText().toString().trim());*/
                    final View view = v;
                    new AsyncTask<Void, Void, Long>() {
                        long insertedUser;

                        @Override
                        protected Long doInBackground(Void... params) {

                            if (!childApp.isChildPresent(child)) {
                                myList = mini.getAll();
                                for (ItemObject object : myList) {
                                    if (!(object == null)) {
                                        infoModel.setInfo_title(object.getName());
                                        infoModel.setInfo_details(object.getDescription());

                                        moreInformation.InsertInfo(infoModel, child, owner);
                                    }
                                }
                                //unsuccessful
                                //daoVaccination.InsertVaccinations(inputVaccinations(Vaccinations.vaccines), child);
                                insertedUser = childApp.InsertChild(child, owner);
                            }

                            return insertedUser;
                        }

                        @Override
                        protected void onPostExecute(Long message) {
                            if (insertedUser > 0) {
                                //message = "Successfully Added " + child.getfirstName() + " " + child.getLastName() + "!";
                                Toast.makeText(Summary.this, "Successfully Added " + child.getfirstName() + " " + child.getLastName() + "!", Toast.LENGTH_SHORT).show();
                                //Vaccinations.vaccines.clear();
                                finish();
                                mini.delete();
                            } else {
                                Snackbar.make(view, "Not Successfull, Child Already Exists!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            }
                            super.onPostExecute(message);
                        }
                    }.execute(null, null, null);

                }

            });


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
    private Child getChildFromLayout(long userid, String username, String firstName, String lastName, String gender,
                                     String DOB, String Imgpath, String height, String weight,
                                     String addLocation, String allergies, String parentNames) {
        Child child1 = new Child();
        child1.setFirstName(firstName);
        child1.setLastName(lastName);
        child1.setGender(gender);
        child1.setDateOfBirth(DOB);
        child1.setUserId(userid);
        child1.setUsername(username);
        child1.setWeight(weight);
        child1.setHeight(height);
        child1.setParentName(parentNames);
        child1.setAddLocation(addLocation);
        child1.setImg_path(Imgpath);
        child1.setVaccinationTaken(getDateTime());
        child1.setVaccination_due(null);
        child1.setAllergies(allergies);
        return child1;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
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
    public void onBackPressed() {
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
        //Vaccinations.vaccines.clear();
        finish();
        super.onBackPressed();
    }



}


