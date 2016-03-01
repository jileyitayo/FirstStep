package com.example.jil.firststep;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.androidrecyclerviewgridview.ListRecyclerViewAdapter;
import com.example.jil.androidrecyclerviewgridview.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JIL on 27/02/16.
 */
public class ManageChild extends Fragment {

    private LinearLayoutManager linearLayout;
    public final List<ItemObject> rowListItem = getAllItemList();
    public ManageChild()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.manage_child_fragment, container, false);
        getActivity().setTitle("Manage Child");

        linearLayout = new LinearLayoutManager(getActivity());
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayout);
        ListRecyclerViewAdapter recyclerViewAdapterAdapter = new ListRecyclerViewAdapter(getActivity(), rowListItem);
        recyclerView.setAdapter(recyclerViewAdapterAdapter);
        return view;
    }
    private List<ItemObject> getAllItemList(){

        String[] descriptions = {
                "Age: 2 months, Gender: Male",
                "Age: 2 years, Gender: Female",
                "Age: 1 month, Gender: Female"};
        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("Ronald Duke", descriptions[0]));
        allItems.add(new ItemObject("Hale Halison", descriptions[1]));
        allItems.add(new ItemObject("Malia Bradson", descriptions[2]));
        return allItems;
    }
}
