package com.example.jil.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.SQLite.DAOHealthApp;
import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.Users.Child;
import com.example.jil.Users.Users;
import com.example.jil.firststep.R;

/**
 * Created by JIL on 16/03/16.
 */
public class SummaryEditDControl extends DialogFragment {
    Child child = new Child();
    SharedPreferences preferences;
    DAOChildApp childApp;
    EditText title, desc, desc2;
    String info,childFN, childLN, name, childDOB;
    public interface TDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String data);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    public SummaryEditDControl()
    {

    }

    TDialogListener dListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        childApp = new DAOChildApp(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            dListener = (TDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement DialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_control, null);
        preferences = getActivity().getSharedPreferences("dialogg", 0);
        name = preferences.getString("name", "missing text");
        info = preferences.getString("info", "missing text2");
        childFN = preferences.getString("childFN", "missing childFirstName");
        childLN = preferences.getString("childLN", "missing childLastName");
        childDOB = preferences.getString("childDOB", "missing DOB");
        title = (EditText) layout.findViewById(R.id.title);
        desc = (EditText) layout.findViewById(R.id.desc);
        desc2 = (EditText) layout.findViewById(R.id.desc2);
        title.setVisibility(View.GONE);

        desc.setVisibility(View.GONE);
        //Building dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(layout);
        builder.setTitle("Edit " + info + "");
        //builder.setMessage("Are you sure you want to Delete " + child.getfirstName() + " " + child.getLastName() + " ?");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*if (TextUtils.isEmpty(desc2.getText().toString().trim())) {
                    desc.setError("Enter correct Detail");
                }
                else
                {*/
                child.setFirstName(childFN);
                child.setLastName(childLN);
                child.setDateOfBirth(childDOB);
                childApp.updateChildDetails(child, name, desc2.getText().toString().trim());
                dialog.dismiss();
                //String childnew = childApp.getSingleChild(child).getfirstName();
                //dListener.onDialogPositiveClick(SummaryEditDControl.this, childnew +" 09");
                preferences.edit().clear().apply();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dListener.onDialogNegativeClick(SummaryEditDControl.this);
            }
        });
        return builder.create();
    }

}