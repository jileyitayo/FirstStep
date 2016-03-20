package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jil.Dialog.MyMoreInfoDialog;
import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.Users.Child;
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
    Child child = new Child();
    Activity activity;
    DAOChildApp childApp;
    public TextView childName;
    String[] arrayName;
    public TextView childDesc;
    public ImageButton childDelete;
    private List<ItemObject> itemList = Collections.emptyList();
    Fragment newFragment2;
    public ListRecyclerViewHolders(View itemView, Activity activity,List<ItemObject> newItemList) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.activity = activity;
        itemList = newItemList;
        childName = (TextView)itemView.findViewById(R.id.child_name);
        childDesc = (TextView)itemView.findViewById(R.id.child_description);
        //preferences  = activity.getSharedPreferences("ChildName", 0);
        childDelete = (ImageButton)itemView.findViewById(R.id.deleteImageButton);
        childDelete.setOnClickListener(this);
        childApp = new DAOChildApp(activity);
    }

    @Override
    public void onClick(View v) {
       /* FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentActManage, newFragment2);
        transaction.addToBackStack("newFragment");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
 */
        if(v.getId() == R.id.deleteImageButton) {
            AlertDialog.Builder builder=new AlertDialog.Builder(activity);
            builder.setTitle("Child Information");
            arrayName = splitString(itemList.get(getAdapterPosition()).getName());
            child.setFirstName(arrayName[0]);
            child.setLastName(arrayName[1]);
            builder.setMessage("Are you sure you want to Delete " + child.getfirstName() + " " + child.getLastName() + " ?");
            final int positionToRemove = getAdapterPosition();
            builder.setNegativeButton("Cancel", null);
            builder.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    itemList.remove(getAdapterPosition());
                    childApp.deleteChild(child);
                }
            });
            builder.show();
        }

        else {
            Intent intent = new Intent(activity,ChildInfoFull.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("DATA", itemList.get(getAdapterPosition()));
            intent.putExtras(bundle);
            activity.startActivity(intent);
        }



        //Toast.makeText(v.getContext(), "Clicked at listView Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }

    public String[] splitString(String word)
    {
        String[] mystring = word.split(" ");
        return mystring;
    }
}
