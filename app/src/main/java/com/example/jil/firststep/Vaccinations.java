package com.example.jil.firststep;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.Users.Child;
import com.example.jil.Users.ChildVaccination;
import com.example.jil.androidrecyclerviewgridview.ListRecyclerViewAdapter;
import com.example.jil.androidrecyclerviewgridview.ListRecyclerViewHolders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vaccinations extends AppCompatActivity {

    private CheckBox vac1, vac2, vac3, vac4, vac5, vac6, vac7, vac8, vac9, vac10;
    String va1, va2, va3, va4, va5, va6, va7, va8, va9, va10;
    private Button btnSave1;
    Child newdata12 = new Child();

    VaccRecyclerViewAdapter recyclerViewAdapterAdapter;
    private LinearLayoutManager mylinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccination);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBluish);
        setSupportActionBar(toolbar);
        newdata12= (Child) getIntent().getExtras().getSerializable("DATA123");

        setTitle("Vaccinations");
        mylinearLayout = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.vacc_info_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mylinearLayout);
        recyclerViewAdapterAdapter = new VaccRecyclerViewAdapter(this, newdata12.childVaccination(this));
        recyclerView.setAdapter(recyclerViewAdapterAdapter);

    }

        public class VaccRecyclerViewAdapter extends RecyclerView.Adapter<VaccRecyclerViewHolders> {

        private Activity context;
            List<String> itemList = new ArrayList<>();
        public VaccRecyclerViewAdapter(Activity context, List<String> itemList) {
            this.itemList = itemList;
            this.context = context;
        }

        @Override
        public VaccRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_vaccination, null);
            VaccRecyclerViewHolders rcv = new VaccRecyclerViewHolders(layoutView, context, itemList);
            return rcv;
        }

        @Override
        public void onBindViewHolder(VaccRecyclerViewHolders holder, int position) {
            holder.childVacc.setText(itemList.get(position));
            //holder.childVaccDate.setText(itemList.get(position).getAge());
            //holder.childVaccDate2.setText(itemList.get(position).getGender());
        }

        @Override
        public int getItemCount() {
            return this.itemList.size();
        }

        public Uri retrievePath(String path) {
            File imgFile = new File(path);
            if (imgFile.exists()) {
                return Uri.fromFile(imgFile);
            } else
                return null;
        }
    }

    public class VaccRecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
        Child child = new Child();
        Activity activity;
        DAOChildApp childApp;
        public TextView childVacc;
        public TextView childVaccDate ,childVaccDate2;
        List<String> itemList = new ArrayList<>();
        Fragment newFragment2;

        public VaccRecyclerViewHolders(View itemView, Activity activity,List<String> newItemList) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.activity = activity;
            itemList = newItemList;
            //myListerner = (MyListerner) this.activity;
            childVacc = (TextView)itemView.findViewById(R.id.vacc_name);
            childVaccDate = (TextView)itemView.findViewById(R.id.vac_due_date);
            childVaccDate2 = (TextView) itemView.findViewById(R.id.vac_due_date2);
            childApp = new DAOChildApp(activity);
        }

        @Override
        public void onClick(View v) {

        }

        public String[] splitString(String word)
        {
            String[] mystring = word.split(" ");
            return mystring;
        }
    }
}
