package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.Users.Child;
import com.example.jil.Users.MoreInformationModel;
import com.example.jil.firststep.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by JIL on 18/03/16.
 */
public class MoreInfoHldrChildFull extends RecyclerView.ViewHolder implements View.OnClickListener{
    Activity activity;
    MoreInformationModel info  = new MoreInformationModel();
    DAOMoreInformation moreInformation;
    Child child = new Child();
    public TextView childName;
    public TextView childDesc;
    public ImageButton mChildDelete;
    public ImageButton mChildEdit;
    String[] arrayName;
    private List<MoreInformationModel> itemList = Collections.emptyList();
    Fragment newFragment2;
    public MoreInfoHldrChildFull(View itemView, Activity activity, List<MoreInformationModel> newItemList) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.activity = activity;
        itemList = newItemList;
        childName = (TextView)itemView.findViewById(R.id.child_name);
        childDesc = (TextView)itemView.findViewById(R.id.child_description);
        mChildDelete = (ImageButton) itemView.findViewById(R.id.deleteImageButton);
        mChildEdit = (ImageButton) itemView.findViewById(R.id.editImageButton);
        mChildDelete.setOnClickListener(this);
        mChildEdit.setOnClickListener(this);
        moreInformation = new DAOMoreInformation(activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.deleteImageButton:
                AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                builder.setTitle("Child Information");
                arrayName = splitString(itemList.get(getAdapterPosition()).getChildName());
                child.setFirstName(arrayName[0]);
                child.setLastName(arrayName[1]);
                builder.setMessage("Are you sure you want to Delete " + child.getfirstName() + " " + child.getLastName() + "'s " + itemList.get(getAdapterPosition()).getInfo_title() + " Information ?");
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //itemList.remove(getAdapterPosition());
                        moreInformation.deleteChildInfo(child, itemList.get(getAdapterPosition()).getInfo_title(), itemList.get(getAdapterPosition()).getInfo_details());
                    }
                });
                builder.show();
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
    public String[] splitString(String word)
    {
        String[] mystring = word.split(" ");
        return mystring;
    }
}
