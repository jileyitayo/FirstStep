package com.example.jil.firststep;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by JIL on 29/03/16.
 */
public class Settings extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);

    }
}