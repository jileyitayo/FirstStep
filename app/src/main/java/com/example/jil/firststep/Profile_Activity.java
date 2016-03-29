package com.example.jil.firststep;

import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jil.Dialog.DialogControl;
import com.example.jil.Dialog.MyMoreInfoDialog;
import com.example.jil.SQLite.DAOChildApp;

import org.w3c.dom.Text;

public class Profile_Activity extends AppCompatActivity implements DialogControl.DialogListener {

    DAOChildApp allChilren;
    TextView tvUserEmail, tvNoChild, tvUsername;
    SharedPreferences preferences;
    Button btnReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        allChilren = new DAOChildApp(this);
        preferences = getSharedPreferences("loginRole", 0);
        String userRole = preferences.getString("role", "missing Role");
        String password = preferences.getString("password", "password not found");
        String username = preferences.getString("username", "missing Username");
        String email = preferences.getString("email", "missing email");
        setTitle("Profile");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvUsername = (TextView) findViewById(R.id.username);
        tvUserEmail = (TextView) findViewById(R.id.emailAddressText);
        tvNoChild = (TextView) findViewById(R.id.NoOfChilrenCount);
        btnReset = (Button) findViewById(R.id.buttonReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoticeDialog();
            }
        });
        int count = allChilren.getAllChildren().size();
        tvUserEmail.setText(email);
        tvUsername.setText(username);
        tvNoChild.setText(String.valueOf(count));

    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogControl();
        dialog.show(getSupportFragmentManager(), "DialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(this, "Password Changed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
