package com.example.jil.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.Users.Child;
import com.example.jil.Users.MoreInformationModel;
import com.example.jil.Users.Users;
import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.firststep.ChildInfoFull;
import com.example.jil.firststep.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JIL on 16/03/16.
 */
public class MyMoreInfoDialog extends DialogFragment {
    Child child = new Child();
    SharedPreferences preferences;
    DAOChildApp childApp;
    String[] childName;
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    public MyMoreInfoDialog()
    {

    }

    NoticeDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        childApp = new DAOChildApp(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_child_info_full, null);
        preferences = getActivity().getSharedPreferences("ChildName", 0);
        String name = preferences.getString("role", "missing Role");
        //Building dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(layout);
        builder.setTitle("Child Information");
        childName = splitString(name);
        child.setFirstName(childName[0]);
        child.setLastName(childName[1]);
        builder.setMessage("Are you sure you want to Delete " + child.getfirstName() + " " + child.getLastName() + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
/*
                editor.putString(TITLE, infoTitleText);
                editor.putString(DETAILS, infoDetailsText);
                editor.apply();


                    if (!moreInformation.isChildInfoPresent(moreInformationModel)) {
                        //unsuccessful
                        moreInformationModel.setChildname();
                        long insertedInfo = moreInformation.InsertInfo(moreInformationModel, child, users);
                        if (insertedUser > 0) {
                            Snackbar.make(v, "Successfully Added " + child.getfirstName() + " " + child.getLastName() + "!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            clearEtValues();
                        } else {
                            Snackbar.make(v, "Not Successfull!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    */
                childApp.deleteChild(child);
                dialog.dismiss();
                mListener.onDialogPositiveClick(MyMoreInfoDialog.this);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mListener.onDialogNegativeClick(MyMoreInfoDialog.this);
            }
        });
        return builder.create();
    }

    public String[] splitString(String word)
    {
        String[] mystring = word.split(" ");
        return mystring;
    }
}