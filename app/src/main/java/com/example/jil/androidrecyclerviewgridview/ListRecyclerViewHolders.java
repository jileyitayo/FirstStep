package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jil.firststep.ChildInfoFull;
import com.example.jil.firststep.R;

import java.util.Collections;
import java.util.List;

import layout.MainActivityFragment;
import layout.ManageChild;
import layout.manage_R_Child;

/**
 * Created by JIL on 01/03/16.
 */
public class ListRecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    Activity activity;
    public TextView childName;
    public TextView childDesc;
    public ImageButton childEdit;
    private List<ItemObject> itemList = Collections.emptyList();
    Fragment newFragment2;
    public ListRecyclerViewHolders(View itemView, Activity activity, List<ItemObject> newItemList) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.activity = activity;
        itemList = newItemList;
        childName = (TextView)itemView.findViewById(R.id.child_name);
        childDesc = (TextView)itemView.findViewById(R.id.child_description);
        childEdit = (ImageButton)itemView.findViewById(R.id.imageButton);
    }

    @Override
    public void onClick(View v) {
       /* FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentActManage, newFragment2);
        transaction.addToBackStack("newFragment");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
        */
        Intent intent = new Intent(activity,ChildInfoFull.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("DATA", itemList.get(getAdapterPosition()));
        intent.putExtras(bundle);
        activity.startActivity(intent);

        Toast.makeText(v.getContext(), "Clicked at listView Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }
}
