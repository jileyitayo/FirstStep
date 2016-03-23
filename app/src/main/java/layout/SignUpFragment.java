package layout;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.jil.SQLite.DAOHealthApp;
import com.example.jil.Users.Users;
import com.example.jil.firststep.MainActivity;
import com.example.jil.firststep.R;
import com.example.jil.firststep.activity_manage;

/**
 * Created by JIL on 04/03/16.
 */
public class SignUpFragment extends Fragment {

    public SignUpFragment() {

    }

    EditText username, emailAddress, phoneNo;
    Button btnSubmit;
    String role = "Parent";
    private EditText password, confirmPassword;
    DAOHealthApp daoHealthApp;
    private CheckBox chkRole;
    Users firstTimeUser = new Users();
    SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.first_time_login_layout, container, false);
        daoHealthApp = new DAOHealthApp(this.getActivity());
        pref = getActivity().getSharedPreferences("loginRole", 0);
        username = (EditText) view.findViewById(R.id.LUsername);
        password = (EditText) view.findViewById(R.id.LPassword);
        confirmPassword = (EditText) view.findViewById(R.id.LConfirmPassword);
        emailAddress = (EditText) view.findViewById(R.id.LEmail);
        //phoneNo = (EditText) view.findViewById(R.id.LPhone);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmitLogin);
        chkRole = (CheckBox) view.findViewById(R.id.chkIos);

        chkRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    role = "Doctor";
                }
                else
                {
                    role = "Parent";
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstTimeUser = getUserFromLayout(username.getText().toString().trim(), password.getText().toString().trim(), emailAddress.getText().toString().trim(), role);

                if (TextUtils.isEmpty(username.getText().toString().trim())) {
                    username.setError(getString(R.string.error_invalid_usernane));
                }
/*
                else if (!TextUtils.isEmpty(phoneNo.getText().toString())) {
                    phoneNo.setError(getString(R.string.error_field__phone_required));
                }
*/
                else if (TextUtils.isEmpty(password.getText().toString().trim()) || !isPasswordValid(password.getText().toString().trim())) {
                    password.setError(getString(R.string.error_invalid_password));
                }

                else if (!isPasswordConfirmed(password.getText().toString().trim(), confirmPassword.getText().toString().trim())) {
                    confirmPassword.setError(getString(R.string.error_field_confirmed_required));
                }

                else if (TextUtils.isEmpty(confirmPassword.getText().toString().trim())) {
                    confirmPassword.setError(getString(R.string.error_field_required));
                }
                else if (TextUtils.isEmpty(emailAddress.getText().toString().trim()) || !isEmailValid(emailAddress.getText().toString().trim())) {
                    emailAddress.setError(getString(R.string.error_invalid_email));
                }
                else
                {

                    if (!daoHealthApp.isUserPresent(firstTimeUser)) {
                        //unsuccessful
                        long insertedUser = daoHealthApp.InsertUser(firstTimeUser);
                        if(insertedUser > 0)
                        {
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("username", firstTimeUser.getUsername());
                            editor.putString("email", firstTimeUser.getEmailAddress());
                            editor.putString("role", firstTimeUser.getRole());
                            editor.apply();
                            Snackbar.make(v, "Added Successfully!", Snackbar.LENGTH_LONG)
                                    .setAction("No", null).show();
                            /*Snackbar.make(v, "Successfully Added " + firstTimeUser.getUsername() + "!", Snackbar.LENGTH_LONG)
                            .setAction("Submit", null)
                                .show();*/

                            getActivity().finish();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            getActivity().startActivity(intent);
                        }
                        else
                        {
                            Snackbar.make(v, "UnSuccessfull!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }


                    }
                }

            }


        });

        return view;
    }

    private Users getUserFromLayout(String username, String password, String emailAddress, String role) {
        int newPhoneNo = 0;
        Users users = new Users();
        /*
        try {
            // the String to int conversion happens here
            newPhoneNo = Integer.parseInt(phoneNo1.trim());
            users.setUsername(username);
            users.setPassword(password);
            users.setEmailAddress(emailAddress);
        } catch (NumberFormatException nfe) {
            phoneNo.setError(getString(R.string.error_field__phone_required));
        }*/
        users.setUsername(username);
        users.setPassword(password);
        users.setEmailAddress(emailAddress);
        users.setRole(role);
        return users;
    }

    private boolean isPasswordConfirmed(String password, String confirmPassword) {
        //TODO: Replace this with your own logic
        return password.equals(confirmPassword);
    }

    private boolean isnameValid(String name) {
        //TODO: Replace this with your own logic
        return name.length() >= 3;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}
