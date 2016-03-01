package com.example.jil.firststep;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by JIL on 27/02/16.
 */
public class ManageChild extends Fragment {

    public ManageChild()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.card_view, container, false);
        getActivity().setTitle("Manage Child");
        return view;
    }
}
