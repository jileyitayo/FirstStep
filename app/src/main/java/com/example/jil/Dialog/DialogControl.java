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

import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.SQLite.DAOHealthApp;
import com.example.jil.Users.Child;
import com.example.jil.Users.Users;
import com.example.jil.firststep.R;

/**
 * Created by JIL on 16/03/16.
 */
public class DialogControl extends DialogFragment {
    Users user = new Users();
    SharedPreferences preferences;
    DAOHealthApp healthUser;
    EditText title, desc;
    String[] childName;
    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    public DialogControl()
    {

    }

    DialogListener dListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        healthUser = new DAOHealthApp(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            dListener = (DialogListener) activity;
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
        preferences = getActivity().getSharedPreferences("loginRole", 0);
        String userRole = preferences.getString("role", "missing Role");
        String username = preferences.getString("username", "missing Username");
        String email = preferences.getString("email", "missing email");
        user.setEmailAddress(email);
        user.setRole(userRole);
        user.setUsername(username);
        title = (EditText) layout.findViewById(R.id.title);
        desc = (EditText) layout.findViewById(R.id.desc);
        //Building dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(layout);
        builder.setTitle("Password Reset");
        //builder.setMessage("Are you sure you want to Delete " + child.getfirstName() + " " + child.getLastName() + " ?");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(title.getText().toString().trim())) {
                    title.setError("Enter Password");
                } else if (TextUtils.isEmpty(desc.getText().toString().trim())) {
                    desc.setError("Enter Password Again!");
                }
                else if (!isPasswordConfirmed(title.getText().toString().trim(), desc.getText().toString().trim())) {
                    desc.setError(getString(R.string.error_field_confirmed_required));
                }
                else
                {
                    user.setPassword(title.getText().toString().trim());
                    healthUser.updateUsersPassword(user);
                    dialog.dismiss();
                    dListener.onDialogPositiveClick(DialogControl.this);
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dListener.onDialogNegativeClick(DialogControl.this);
            }
        });
        return builder.create();
    }

    private boolean isPasswordConfirmed(String password, String confirmPassword) {
        //TODO: Replace this with your own logic
        return password.equals(confirmPassword);
    }

}