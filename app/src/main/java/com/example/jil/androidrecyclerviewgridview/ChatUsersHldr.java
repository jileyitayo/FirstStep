package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jil.SQLite.DAOInfoMini;
import com.example.jil.Users.Users;
import com.example.jil.firststep.ChatActivity;
import com.example.jil.firststep.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by JIL on 18/03/16.
 */
public class ChatUsersHldr extends RecyclerView.ViewHolder implements View.OnClickListener{
    Activity activity;
    TextView TUsername;
    TextView TEmail;
    private List<Users> itemList = Collections.emptyList();
    public ChatUsersHldr(View itemView, Activity activity, List<Users> newItemList) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.activity = activity;
        itemList = newItemList;
        TUsername = (TextView)itemView.findViewById(R.id.CUsername);
        TEmail = (TextView)itemView.findViewById(R.id.CEmail);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(activity,ChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("DATA212", itemList.get(getAdapterPosition()));
        intent.putExtras(bundle);
        activity.startActivity(intent);

        //Toast.makeText(v.getContext(), "Clicked at listView Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

    }
}
