package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jil.Dialog.MoreInfoDialogControl;
import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.Users.Child;
import com.example.jil.Users.MoreInformationModel;
import com.example.jil.firststep.ChildInfoFull;
import com.example.jil.firststep.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by JIL on 18/03/16.
 */
public class MoreInfoHldrChildFull extends RecyclerView.ViewHolder implements View.OnClickListener, MoreInfoDialogControl.EditInfoDialogListener {
    Activity activity;
    MoreInformationModel info = new MoreInformationModel();
    DAOMoreInformation moreInformation;
    Child child = new Child();
    public TextView childName;
    public TextView childDesc;
    public ImageButton mChildDelete;
    public ImageButton mChildEdit;
    SharedPreferences childInfopref;
    String[] arrayName;
    private List<MoreInformationModel> itemList = Collections.emptyList();
    Fragment newFragment2;

    public MoreInfoHldrChildFull(View itemView, Activity activity, List<MoreInformationModel> newItemList) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.activity = activity;
        itemList = newItemList;
        childName = (TextView) itemView.findViewById(R.id.child_name);
        childDesc = (TextView) itemView.findViewById(R.id.child_description);
        mChildDelete = (ImageButton) itemView.findViewById(R.id.deleteImageButton);
        mChildEdit = (ImageButton) itemView.findViewById(R.id.editImageButton);
        mChildEdit.setVisibility(View.GONE);
        mChildDelete.setOnClickListener(this);
        mChildEdit.setOnClickListener(this);
        moreInformation = new DAOMoreInformation(activity);
        childInfopref = activity.getSharedPreferences("childInfo", 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deleteImageButton:
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Child Information");
                arrayName = splitString(itemList.get(getAdapterPosition()).getChildName());
                child.setFirstName(arrayName[0]);
                child.setLastName(arrayName[1]);
                builder.setMessage("Are you sure you want to Delete " + child.getfirstName() + " " + child.getLastName() + "'s " + itemList.get(getAdapterPosition()).getInfo_title() + " Information ?");
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //itemList.remove(getAdapterPosition());
                        activity.finish();
                        moreInformation.deleteChildInfo(child, itemList.get(getAdapterPosition()).getInfo_title(), itemList.get(getAdapterPosition()).getInfo_details());
                        ItemObject newObject = (ItemObject) activity.getIntent().getExtras().getSerializable("DATA");
                        activity.startActivity(new Intent(activity, ChildInfoFull.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).putExtra("DATA", newObject));
                    }
                });
                builder.show();
                break;
            case R.id.editImageButton:
                /*
                arrayName = splitString(itemList.get(getAdapterPosition()).getChildName());
                child.setFirstName(arrayName[0]);
                child.setLastName(arrayName[1]);
                SharedPreferences.Editor editor = childInfopref.edit();
                editor.putString("infoTitle", info.getInfo_title());
                editor.putString("infoDetail", info.getInfo_details());
                editor.putString("childFirstName", child.getfirstName());
                editor.putString("childLastName", child.getLastName());
                editor.apply();
                */
                break;
        }
    }

    public String[] splitString(String word) {
        String[] mystring = word.split(" ");
        return mystring;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

}
