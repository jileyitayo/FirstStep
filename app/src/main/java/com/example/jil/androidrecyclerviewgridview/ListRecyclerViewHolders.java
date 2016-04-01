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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jil.Dialog.MyMoreInfoDialog;
import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.Users.Child;
import com.example.jil.firststep.ChildInfoFull;
import com.example.jil.firststep.R;
import com.example.jil.firststep.activity_manage;

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
    public ImageView proPic;
    public TextView childDesc ,childGender;
    public ImageButton childDelete;
    private List<Child> itemList = Collections.emptyList();

    public interface MyListerner
    {
        public void updateListView(List<Child> itemObjects);
        public void updateList();
    }
    MyListerner myListerner;
    Fragment newFragment2;

    public ListRecyclerViewHolders(View itemView, Activity activity,List<Child> newItemList) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.activity = activity;
        itemList = newItemList;
        //myListerner = (MyListerner) this.activity;
        childName = (TextView)itemView.findViewById(R.id.child_name);
        childDesc = (TextView)itemView.findViewById(R.id.child_description_age);
        childGender = (TextView) itemView.findViewById(R.id.child_description_gender);
        proPic = (ImageView)itemView.findViewById(R.id.profilePiclist);

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
            child = itemList.get(getAdapterPosition());
            builder.setMessage("Are you sure you want to Delete " + child.getfirstName() + " " + child.getLastName() + " ?");
            final int positionToRemove = getAdapterPosition();
            builder.setNegativeButton("Cancel", null);
            builder.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //itemList.remove(getAdapterPosition());
                    //myListerner.updateListView(itemList);
                    //myListerner = setOnclickDeleteListener(myListerner, activity);
                    //myListerner.updateList();
                    activity.finish();
                    childApp.deleteChild(child);
                    activity.startActivity(new Intent(activity, activity_manage.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
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
