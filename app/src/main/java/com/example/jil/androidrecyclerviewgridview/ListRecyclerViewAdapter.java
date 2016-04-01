package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jil.Users.Child;
import com.example.jil.firststep.R;

import java.io.File;
import java.util.List;

/**
 * Created by JIL on 01/03/16.
 */
public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewHolders> {
    private List<Child> itemList;
    private Activity context;

    public ListRecyclerViewAdapter(Activity context, List<Child> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ListRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_view, null);
        ListRecyclerViewHolders rcv = new ListRecyclerViewHolders(layoutView, context, itemList);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ListRecyclerViewHolders holder, int position) {
        String text = itemList.get(position).getfirstName() + " " + itemList.get(position).getLastName();
        holder.childName.setText(text);
        holder.childDesc.setText(itemList.get(position).getAge());
        holder.childGender.setText(itemList.get(position).getGender());
        if(itemList.get(position).getImg_path() != null)
        {
            holder.proPic.setImageURI(retrievePath(itemList.get(position).getImg_path()));
        }
        else
            holder.proPic.setImageResource(R.drawable.ic_person_black_24dp);
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
