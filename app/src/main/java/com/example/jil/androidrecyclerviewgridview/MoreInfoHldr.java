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
import android.widget.Toast;

import com.example.jil.SQLite.DAOInfoMini;
import com.example.jil.firststep.ChildInfoFull;
import com.example.jil.firststep.R;
import com.example.jil.firststep.ViewMoreInfo;

import java.util.Collections;
import java.util.List;

/**
 * Created by JIL on 18/03/16.
 */
public class MoreInfoHldr extends RecyclerView.ViewHolder implements View.OnClickListener{
    Activity activity;
    public TextView childName;
    public TextView childDesc;
    DAOInfoMini min;
    ImageButton btnDelete, btnEdit;
    private List<ItemObject> itemList = Collections.emptyList();
    Fragment newFragment2;
    public MoreInfoHldr(View itemView, Activity activity, List<ItemObject> newItemList) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.activity = activity;
        itemList = newItemList;
        min = new DAOInfoMini(activity);
        childName = (TextView)itemView.findViewById(R.id.child_name);
        childDesc = (TextView)itemView.findViewById(R.id.child_description);
        btnDelete = (ImageButton)itemView.findViewById(R.id.deleteImageButton);
        btnEdit = (ImageButton)itemView.findViewById(R.id.editImageButton);
        btnDelete.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnEdit.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.deleteImageButton:
                boolean j;
                activity.finish();
                j = min.delete(itemList.get(getAdapterPosition()).getName(), itemList.get(getAdapterPosition()).getDescription());
                if(j)
                {
                    Snackbar.make(v, "Successfull! deleted", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                activity.startActivity(new Intent(activity, ViewMoreInfo.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                break;
            case R.id.editImageButton:

                break;
        }
       /* FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentActManage, newFragment2);
        transaction.addToBackStack("newFragment");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();

        Intent intent = new Intent(activity,ChildInfoFull.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("DATA", itemList.get(getAdapterPosition()));
        intent.putExtras(bundle);
        activity.startActivity(intent);

        Toast.makeText(v.getContext(), "Clicked at listView Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        */
    }
}
