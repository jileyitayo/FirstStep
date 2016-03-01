package com.example.jil.firststep;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by JIL on 26/02/16.
 */
public class Add_Child extends Fragment {

    public Add_Child()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.add_child_fragment, container, false);

        return view;
    }
}
