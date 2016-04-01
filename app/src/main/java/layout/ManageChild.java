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
    public final List<ItemObject> rowListItem = getAllItemList();
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
        recyclerViewAdapterAdapter = new ListRecyclerViewAdapter(getActivity(), getAllItemList2());
        recyclerView.setAdapter(recyclerViewAdapterAdapter);
        return view;
    }

    @Override
    public void onResume() {
        this.onCreate(null);
        super.onResume();
    }



    private List<ItemObject> getAllItemList()
    {
        String[] descriptions = {"No Detail Found"};
        List<ItemObject> allItems = new ArrayList<ItemObject>();
        Child child1 = new Child();
        allItems.add(new ItemObject("No Child Name Found", descriptions[0]));
        return allItems;
    }
    private List<ItemObject> getAllItemList2(){

        String[] descriptions = getChildListDesc();
        String[] firstName = getChildFN();
        String[] lastname = getChildLN();
        String[] pics = getPics();
        List<Child> children = getChildren();
        List<ItemObject> allItems = new ArrayList<ItemObject>();
        for(int i = 0; i < children.size(); i++) {
            allItems.add(new ItemObject(firstName[i] + " " + lastname[i], descriptions[i], pics[i]));
        }

        if(allItems == null)
        {
            allItems = getAllItemList();
        }
        return allItems;
    }

    private String[] getChildListDesc() {
        childApp = new DAOChildApp(getActivity());
        List<Child> alist = childApp.getAllChildren();
        String[] list = new String[alist.size()];

            for (int i = 0; i < alist.size(); i++) {
                //String childAge = alist.get(i).getDateOfBirth(), childGender = alist.get(i).getGender(), combinationPhase = null;
                String childAge = getAgeDifference(alist.get(i).getDateOfBirth()), childGender = alist.get(i).getGender(), combinationPhase = null;
                combinationPhase = "Age: " + childAge + ", Gender: " + childGender;
                list[i] = combinationPhase;
            }
        childApp.close();
        return list;
    }

    private List<Child> getChildren()
    {
        childApp = new DAOChildApp(getActivity());
        List<Child> alist = childApp.getAllChildren();
        return alist;
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

    private String[] getPics()
    {
        childApp = new DAOChildApp(getActivity());
        List<Child> alist = childApp.getAllChildren();
        String[] list = new String[alist.size()];

        for (int i = 0; i < alist.size(); i++) {
            String pic = alist.get(i).getImg_path();
            list[i] = pic;
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

    private String getAgeDifference(String DOB)
    {
        String diff = null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("dd/mm/yyyy");
        String strDate = simpleDateFormat.format(c.getTime());

        try {

            Date date1 = simpleDateFormat.parse(DOB);
            Date date2 = simpleDateFormat.parse(strDate);
            diff = printDifference(date1, date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
return diff;
    }


    //1 minute = 60 seconds
    //1 hour = 60 x 60 = 3600
    //1 day = 3600 x 24 = 86400
    public String printDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long monthInMilli = daysInMilli * 30;
        long yearsInMilli = monthInMilli * 12;

        long elapsedYears = different / yearsInMilli;
        different = different % yearsInMilli;

        long elapsedMonths = different / monthInMilli;
        different = different % monthInMilli;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        String diff = null;
        if(elapsedMonths <= 1 && elapsedYears <=1)
        {
            diff = elapsedYears + " year, " + elapsedMonths + " month old";
        }
        else if(elapsedYears <=1)
        {
            diff = elapsedYears + " year, " + elapsedMonths + " months old";
        }
        else if(elapsedMonths <=1)
        {
            diff = elapsedYears + " years, " + elapsedMonths + " month old";
        }
        else
            diff = elapsedYears + " years, " + elapsedMonths + " months old";


        return diff;
    }


    @Override
    public void updateListView(List<ItemObject> itemObjects) {
        recyclerViewAdapterAdapter = new ListRecyclerViewAdapter(getActivity(), getAllItemList2());
        recyclerViewAdapterAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateList() {
        recyclerViewAdapterAdapter.notifyDataSetChanged();
    }
}
