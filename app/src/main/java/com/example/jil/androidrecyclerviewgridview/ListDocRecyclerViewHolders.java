package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.Users.Child;
import com.example.jil.Users.Users;
import com.example.jil.firststep.ChildInfoFull;
import com.example.jil.firststep.R;
import com.example.jil.firststep.activity_Schedules;
import com.example.jil.firststep.activity_manage;
import com.example.jil.firststep.schedule_menu;

import java.util.Collections;
import java.util.List;

/**
 * Created by JIL on 01/03/16.
 */
public class ListDocRecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    Users users = new Users();
    Activity activity;
    public TextView childName;
    public ImageView proPic;
    public TextView childDesc ,childGender;
    public ImageButton childDelete;
    private List<Users> itemList = Collections.emptyList();

    public ListDocRecyclerViewHolders(View itemView, Activity activity, List<Users> newItemList) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.activity = activity;
        itemList = newItemList;

        childName = (TextView)itemView.findViewById(R.id.child_name);
        childDesc = (TextView)itemView.findViewById(R.id.child_description_age);
        childGender = (TextView) itemView.findViewById(R.id.child_description_gender);
        proPic = (ImageView)itemView.findViewById(R.id.profilePiclist21);
        proPic.setVisibility(View.GONE);
        //preferences  = activity.getSharedPreferences("ChildName", 0);
        childDelete = (ImageButton)itemView.findViewById(R.id.deleteImageButton);
        childDelete.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        /*
            Intent intent = new Intent(activity,ChildInfoFull.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("DATA", itemList.get(getAdapterPosition()));
            intent.putExtras(bundle);
            activity.startActivity(intent);

*/


        //Toast.makeText(v.getContext(), "Clicked at listView Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }

}
