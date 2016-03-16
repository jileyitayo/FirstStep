package layout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jil.firststep.R;

import org.w3c.dom.Text;

/**
 * Created by JIL on 02/03/16.
 */
public class manage_R_Child extends Fragment {

    public manage_R_Child()
    {

    }

    TextView child_Name;
    TextView child_Age;
    TextView child_DOB;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_child_full, container, false);
        getActivity().setTitle("First Name");
        //child_Name = (TextView)view.findViewById(R.id.sh_first_name);
        child_Age = (TextView)view.findViewById(R.id.child_age);
        child_DOB = (TextView)view.findViewById(R.id.child_dOB);
        return view;
    }

    public void changeData(String data)
    {
    //    child_Name.setText(data);
    }

    public interface Communicator
    {
            public void respond(String data);
    }


}
