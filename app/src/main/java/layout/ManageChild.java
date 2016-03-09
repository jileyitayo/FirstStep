package layout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.Users.Child;
import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.androidrecyclerviewgridview.ListRecyclerViewAdapter;
import com.example.jil.androidrecyclerviewgridview.RecyclerViewAdapter;
import com.example.jil.firststep.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JIL on 27/02/16.
 */
public class  ManageChild extends Fragment {

    manage_R_Child.Communicator communicator;
    DAOChildApp childApp;

    private LinearLayoutManager linearLayout;
    public final List<ItemObject> rowListItem = getAllItemList();
    FragmentManager newFm = getFragmentManager();
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
        ListRecyclerViewAdapter recyclerViewAdapterAdapter = new ListRecyclerViewAdapter(getActivity(), rowListItem);
        recyclerView.setAdapter(recyclerViewAdapterAdapter);
        return view;
    }

    private List<ItemObject> getAllItemList(){

        String[] descriptions = getChildListDesc();
        String[] firstName = getChildFN();
        String[] lastname = getChildLN();
        List<ItemObject> allItems = new ArrayList<ItemObject>();
        for(int i = 0; i < firstName.length; i++) {
            allItems.add(new ItemObject(firstName[i] + " " + lastname[i], descriptions[i]));
        }
        return allItems;
    }

    private String[] getChildListDesc() {
        childApp = new DAOChildApp(getActivity());
        List<Child> alist = childApp.getAllChildren();
        String[] list = new String[alist.size()];

            for (int i = 0; i < alist.size(); i++) {
                String childAge = alist.get(i).getDateOfBirth(), childGender = alist.get(i).getGender(), combinationPhase = null;
                combinationPhase = "Age: " + childAge + ", Gender: " + childGender;
                list[i] = combinationPhase;
            }
        childApp.close();
        return list;
    }

    private String[] getChildFN()
    {
        childApp = new DAOChildApp(getActivity());
        List<Child> alist = childApp.getAllChildren();
        String[] list = new String[alist.size()];

        for (int i = 0; i < alist.size(); i++) {
            String FN = alist.get(i).getfirstName();
            list[i] = FN;
        }
        childApp.close();
        return list;
    }

    private String[] getChildLN()
    {
        childApp = new DAOChildApp(getActivity());
        List<Child> alist = childApp.getAllChildren();
        String[] list = new String[alist.size()];

        for (int i = 0; i < alist.size(); i++) {
            String LN = alist.get(i).getLastName();
            list[i] = LN;
        }
        childApp.close();
        return list;
    }


    private void getAgeDifference(String CurrentDate, String DOB)
    {

    }

}
