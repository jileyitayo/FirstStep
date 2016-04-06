package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jil.Users.Child;
import com.example.jil.Users.Users;
import com.example.jil.firststep.R;

import java.io.File;
import java.util.List;

/**
 * Created by JIL on 01/03/16.
 */
public class ListDocRecyclerViewAdapter extends RecyclerView.Adapter<ListDocRecyclerViewHolders> {
    private List<Users> itemList;
    private Activity context;

    public ListDocRecyclerViewAdapter(Activity context, List<Users> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ListDocRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_view, null);
        ListDocRecyclerViewHolders rcv = new ListDocRecyclerViewHolders(layoutView, context, itemList);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ListDocRecyclerViewHolders holder, int position) {
        holder.childName.setText(itemList.get(position).getUsername());
        holder.childDesc.setText(itemList.get(position).getEmailAddress());
        holder.childGender.setText(itemList.get(position).getCreated_at());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

}
