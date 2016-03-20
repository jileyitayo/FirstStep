package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import layout.MainActivityFragment;

import com.example.jil.firststep.AddChild_Activity;
import com.example.jil.firststep.ChatActivity;
import com.example.jil.firststep.ContactDoctor;
import com.example.jil.firststep.R;
import com.example.jil.firststep.activity_Schedules;
import com.example.jil.firststep.activity_manage;


public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    SharedPreferences pref;
    Activity activity;
    public TextView activityName;
    public ImageView activitypic;
    public TextView activityDesc;
    String userRole;
    Context context;

    public RecyclerViewHolders(View itemView, Activity activity) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.activity = activity;
        activityName = (TextView) itemView.findViewById(R.id.activity);
        activitypic = (ImageView) itemView.findViewById(R.id.userPix);
        activityDesc = (TextView) itemView.findViewById(R.id.description);
        pref = activity.getSharedPreferences("loginRole", 0);
        userRole = pref.getString("role", "missing Role");
    }

    Fragment newFragment;

    @Override
    public void onClick(View view) {
        Intent intent;
        int i = 0;
        if (userRole.equals("Doctor")) {
            i = getAdapterPosition() + 1;
            switch (i) {
                case 0:
                    intent = new Intent(activity, AddChild_Activity.class);
                    activity.startActivity(intent);
                    break;
                case 1:
                    //newFragment = new ManageChild();

                    intent = new Intent(activity, activity_manage.class);
                    activity.startActivity(intent);
                    break;

                case 2:
                    activity.startActivity(new Intent(activity, ContactDoctor.class));
                    break;

                case 3:
                    intent = new Intent(activity, activity_Schedules.class);
                    activity.startActivity(intent);
                    break;
                default:
                    newFragment = new MainActivityFragment();
                    insertFragment();
                    break;
            }
        } else {
            switch (getAdapterPosition()) {
                case 0:
                    intent = new Intent(activity, AddChild_Activity.class);
                    activity.startActivity(intent);
                    break;
                case 1:
                    //newFragment = new ManageChild();

                    intent = new Intent(activity, activity_manage.class);
                    activity.startActivity(intent);
                    break;

                case 2:
                    activity.startActivity(new Intent(activity, ContactDoctor.class));
                    break;

                case 3:
                    intent = new Intent(activity, activity_Schedules.class);
                    activity.startActivity(intent);
                    break;
                default:
                    newFragment = new MainActivityFragment();
                    insertFragment();
                    break;
            }

        }


        //Toast.makeText(view.getContext(), "Clicked on activity Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }

    private void insertFragment() {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack("newFragment");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

}

