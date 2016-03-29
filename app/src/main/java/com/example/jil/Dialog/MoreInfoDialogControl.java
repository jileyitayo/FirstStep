package com.example.jil.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.Users.MoreInformationModel;
import com.example.jil.firststep.R;

/**
 * Created by JIL on 28/03/16.
 */
public class MoreInfoDialogControl extends DialogFragment {
    MoreInformationModel childInfo = new MoreInformationModel();
    SharedPreferences preferences;
    DAOMoreInformation childDBInfo;
    EditText title, desc;
    String[] childName;

    public interface EditInfoDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);

        public void onDialogNegativeClick(DialogFragment dialog);
    }

    public MoreInfoDialogControl() {

    }

    EditInfoDialogListener dListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        childDBInfo = new DAOMoreInformation(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            dListener = (EditInfoDialogListener) activity;
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
        preferences = getActivity().getSharedPreferences("childInfo", 0);
        String Infotitle = preferences.getString("infoTitle", "missing title");
        String Infodetails = preferences.getString("infoDetail", "missing details");
        final String childFirstName = preferences.getString("childFirstName", "missing first name");
        final String childLastName = preferences.getString("childLastName", "missing last name");
        title = (EditText) layout.findViewById(R.id.title);
        desc = (EditText) layout.findViewById(R.id.desc);
        title.setText(Infotitle);
        desc.setText(Infodetails);
        //Building dialog
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setView(layout);
        builder.setTitle("Edit Child Information");
        //builder.setMessage("Are you sure you want to Delete " + child.getfirstName() + " " + child.getLastName() + " ?");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(title.getText().toString().trim())) {
                    title.setError("Input Title");
                } else if (TextUtils.isEmpty(desc.getText().toString().trim())) {
                    desc.setError("Input Details");
                } else {
                    childInfo.setInfo_title(title.getText().toString().trim());
                    childInfo.setInfo_details(desc.getText().toString().trim());
                    childInfo.setChild(childFirstName, childLastName);
                    childDBInfo.updateChildInfo(childInfo);
                    dialog.dismiss();
                    dListener.onDialogPositiveClick(MoreInfoDialogControl.this);
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dListener.onDialogNegativeClick(MoreInfoDialogControl.this);
            }
        });
        return builder.create();
    }
}