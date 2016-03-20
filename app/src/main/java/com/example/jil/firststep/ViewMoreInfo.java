package com.example.jil.firststep;

import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.example.jil.SQLite.DAOInfoMini;
import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.androidrecyclerviewgridview.MoreInfoAdpt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewMoreInfo extends AppCompatActivity {

    DAOInfoMini mini;
    ItemObject itemObject1 = new ItemObject();
    LinearLayoutManager linearLayout;
    List<ItemObject> myList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_more_information);
        mini = new DAOInfoMini(this);
        myList = mini.getAll();
        if(myList == null)
        {
            myList = new ArrayList<>();
        }
        linearLayout = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_list_view_info);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayout);
        MoreInfoAdpt recyclerViewAdapterAdapter = new MoreInfoAdpt(this, myList);
        recyclerView.setAdapter(recyclerViewAdapterAdapter);
    }

    private List<ItemObject> getAllItemList2(){

        String[] descriptions = {"You can add a new child here", "You can edit your child here", "You can communicate with your doctor", "View your schedules and add Reminders"};
        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("Add Child", descriptions[0]));
        allItems.add(new ItemObject("Manage Child", descriptions[1]));
        allItems.add(new ItemObject("Contact Doctor", descriptions[2]));
        allItems.add(new ItemObject("Schedules", descriptions[3]));
        return allItems;
    }

}
