package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import layout.Add_Child;
import layout.MainActivityFragment;
import com.example.jil.firststep.R;
import com.example.jil.firststep.activity_manage;


public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    Activity activity;
    public TextView activityName;
    public ImageView activitypic;
    public TextView activityDesc;
    Context context;
    public RecyclerViewHolders(View itemView, Activity activity) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.activity = activity;
        activityName = (TextView)itemView.findViewById(R.id.activity);
        activitypic = (ImageView)itemView.findViewById(R.id.userPix);
        activityDesc = (TextView)itemView.findViewById(R.id.description);
    }

    Fragment newFragment;
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (getAdapterPosition())
        {
            case 0:
                newFragment = new Add_Child();
                insertFragment();
                break;
            case 1:
                //newFragment = new ManageChild();

                intent = new Intent(activity, activity_manage.class);
                activity.startActivity(intent);
                break;

            case 2:

                break;
            default:
                newFragment = new MainActivityFragment();
                insertFragment();
                break;
        }


        Toast.makeText(view.getContext(), "Clicked on activity Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }

    private void insertFragment()
    {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack("newFragment");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

}

