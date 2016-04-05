package layout;

import android.app.FragmentTransaction;
import android.content.Context;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jil.Users.Child;
import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.androidrecyclerviewgridview.RecyclerViewAdapter;
import com.example.jil.firststep.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private GridLayoutManager lLayout;
    public List<ItemObject> rowListItem = new ArrayList<>();
    SharedPreferences pref;
    //ImageView picImage;
    //TextView userText;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        pref = getActivity().getSharedPreferences("loginRole", 0);
        String userRole = pref.getString("role", "missing Role");
        String username = pref.getString("username", "missing Username");
        getActivity().setTitle(username.toUpperCase());
        if (userRole.equals("Doctor")) {
            rowListItem = getAllItemListDoctors();
        } else {
            rowListItem = getAllItemList();
        }
        //1 determines the number of grid on a row
        lLayout = new GridLayoutManager(getActivity(), 3);

        RecyclerView rView = (RecyclerView)view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
    private List<ItemObject> getAllItemList(){

        String[] descriptions = {"You can add a new child here", "You can edit your child here", "You can communicate with your doctor", "View your schedules and add Reminders"};
        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("Add Child", R.drawable.child_baby, descriptions[0]));
        allItems.add(new ItemObject("Manage Child", R.drawable.african_american_children, descriptions[1]));
        allItems.add(new ItemObject("Contact Doctor", R.drawable.black_mother_child_healthcare_doctor, descriptions[2]));
        allItems.add(new ItemObject("View Schedules", R.drawable.group_4, descriptions[3]));
        return allItems;
    }

    private List<ItemObject> getAllItemListDoctors(){

        String[] descriptions = {"You can add a new child here", "You can view all users and their children added", "You can communicate with Patients", "Set schedules and add Reminders"};
        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("View Children", R.drawable.african_american_children, descriptions[1]));
        allItems.add(new ItemObject("Contact Patients", R.drawable.black_mother_child_healthcare_doctor, descriptions[2]));
        allItems.add(new ItemObject("Set Schedules", R.drawable.group_4, descriptions[3]));
        return allItems;
    }



}
