package com.example.jil.firststep;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jil.SQLite.DAOHealthApp;
import com.example.jil.Users.Users;

import layout.LoginFragment;
import layout.MainActivityFragment;
import layout.SignUpFragment;

public class Login_Activity extends AppCompatActivity {

    DAOHealthApp daoHealthApp;
    private Users newUser;
    Fragment clFragment;
    FragmentManager fm = getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        int countUser = 0;
        daoHealthApp = new DAOHealthApp(this);
        //newUser = daoHealthApp.getUsers();
        countUser = daoHealthApp.getRowCount();
        if(countUser >= 1)
        {
            clFragment = new LoginFragment();
        }
        else
        {

            //newUser = daoHealthApp.getUsers();

            //the issue is here
            clFragment = new SignUpFragment();

        }
        fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.userLoginPlaceholder, clFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
}

}

