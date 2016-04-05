package layout;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.Users.Child;
import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.androidrecyclerviewgridview.ListRecyclerViewAdapter;
import com.example.jil.androidrecyclerviewgridview.ListRecyclerViewHolders;
import com.example.jil.androidrecyclerviewgridview.RecyclerViewAdapter;
import com.example.jil.firststep.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by JIL on 27/02/16.
 */
public class  ManageChild extends Fragment implements ListRecyclerViewHolders.MyListerner{

    manage_R_Child.Communicator communicator;
    DAOChildApp childApp;
    Child child = new Child();

    private LinearLayoutManager linearLayout;
    public final List<Child> rowListItem = getAllItemList();
    FragmentManager newFm = getFragmentManager();
    ListRecyclerViewAdapter recyclerViewAdapterAdapter;
    public ManageChild()
    {

    }
    @Override
    public void onAttach(Context context) {
        communicator = (manage_R_Child.Communicator)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.manage_child_fragment, container, false);
        getActivity().setTitle("Manage Child");
        childApp = new DAOChildApp(this.getActivity());
        linearLayout = new LinearLayoutManager(getActivity());
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayout);
        recyclerViewAdapterAdapter = new ListRecyclerViewAdapter(getActivity(), getChildren());
        recyclerView.setAdapter(recyclerViewAdapterAdapter);
        return view;
    }

    @Override
    public void onResume() {
        this.onCreate(null);
        super.onResume();
    }



    private List<Child> getAllItemList()
    {
        List<Child> allItems = new ArrayList<Child>();
        allItems.add(new Child("First Name", "Last Name", "Age", "Gender", null));
        return allItems;
    }

    private List<Child> getChildren()
    {
        childApp = new DAOChildApp(getActivity());
        List<Child> alist = childApp.getAllChildren();
        return alist;
    }

    @Override
    public void updateListView(List<Child> itemObjects) {
        recyclerViewAdapterAdapter = new ListRecyclerViewAdapter(getActivity(), getChildren());
        recyclerViewAdapterAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateList() {
        recyclerViewAdapterAdapter.notifyDataSetChanged();
    }

}
