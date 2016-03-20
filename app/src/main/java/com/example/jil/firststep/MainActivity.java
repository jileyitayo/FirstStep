package com.example.jil.firststep;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import layout.MainActivityFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


//Fragments starts from here

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //initialize what the fragment should be;
        //MainActivityFragment mainActivityFragment = new MainActivityFragment();
        ft.add(R.id.fragment, new MainActivityFragment());
        ft.commit();

    }

    @Override
    protected void onResume() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //initialize what the fragment should be;
        //MainActivityFragment mainActivityFragment = new MainActivityFragment();
        ft.add(R.id.fragment, new MainActivityFragment());
        ft.commit();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    public void setFragment(Fragment frag, String tag)
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment fragment = getFragmentManager().findFragmentById(R.id.container);
        if(fragment == null)
        {
            ft.add(R.id.container, frag, tag);
        } else {
            ft.replace(R.id.container, frag, tag);
        }

        ft.addToBackStack(null);
        ft.commit();

    }
    */


}
