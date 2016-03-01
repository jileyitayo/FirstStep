package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jil.firststep.R;

/**
 * Created by JIL on 01/03/16.
 */
public class ListRecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    Activity activity;
    public TextView childName;
    public TextView childDesc;
    public ImageButton childEdit;
    Fragment newFragment2;
    public ListRecyclerViewHolders(View itemView, Activity activity) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.activity = activity;
        childName = (TextView)itemView.findViewById(R.id.child_name);
        childDesc = (TextView)itemView.findViewById(R.id.child_description);
        childEdit = (ImageButton)itemView.findViewById(R.id.imageButton);
    }

    @Override
    public void onClick(View v) {
        /*
        switch (getAdapterPosition())
        {
            case 0:
                newFragment2 = new Add_Child();
                break;
            case 1:
                newFragment2 = new ManageChild();
                break;
            case 2:

                break;
            default:
                newFragment2 = new MainActivityFragment();
                break;
        }
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack("newFragment");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
*/
        Toast.makeText(v.getContext(), "Clicked at listView Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }
}
