package layout;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jil.firststep.R;

/**
 * Created by JIL on 26/02/16.
 */
public class Add_Child extends Fragment {
Context cxt;
    public Add_Child()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.add_child_fragment, container, false);
        getActivity().setTitle("New Child");
        return view;
    }


}
