package com.example.jil.firststep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.Users.Child;
import com.example.jil.androidrecyclerviewgridview.ListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class activity_Schedules extends AppCompatActivity {

    DAOChildApp childApp;
    private LinearLayoutManager linearLayout;
    public final List<Child> rowListItem = getAllItemList();
    ListRecyclerViewAdapter recyclerViewAdapterAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_schedules);
        setTitle("Schedules");
        childApp = new DAOChildApp(this);
        linearLayout = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_list_view23);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayout);
        recyclerViewAdapterAdapter = new ListRecyclerViewAdapter(this, getChildren());
        recyclerView.setAdapter(recyclerViewAdapterAdapter);

    }
    private List<Child> getAllItemList()
    {
        List<Child> allItems = new ArrayList<Child>();
        allItems.add(new Child("First Name", "Last Name", "Age", "Gender", null));
        return allItems;
    }

    private List<Child> getChildren()
    {
        childApp = new DAOChildApp(this);
        List<Child> alist = childApp.getAllChildren();
        return alist;
    }
}

