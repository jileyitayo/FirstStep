package layout;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.firststep.R;

import java.util.ArrayList;
import java.util.List;

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
    DAOMoreInformation moreInformation;
    DAOChildApp childApp;
    LinearLayoutManager linearLayout;
    List<ItemObject> objectList = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vaccination, container, false);
        getActivity().setTitle("First Name");
        childApp = new DAOChildApp(getActivity());
        moreInformation = new DAOMoreInformation(getActivity());


        //child_Name = (TextView)view.findViewById(R.id.sh_first_name);
        child_Age = (TextView)view.findViewById(R.id.child_age);
        child_DOB = (TextView)view.findViewById(R.id.child_gender);
        linearLayout = new LinearLayoutManager(getActivity());
        /*
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_view_info);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayout);
        MoreInfoAdpt recyclerViewAdapterAdapter = new MoreInfoAdpt(this, null);
        recyclerView.setAdapter(recyclerViewAdapterAdapter);
        */
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
