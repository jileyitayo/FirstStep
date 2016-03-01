package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jil.firststep.R;

import java.util.List;

/**
 * Created by JIL on 01/03/16.
 */
public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewHolders> {
    private List<ItemObject> itemList;
    private Activity context;

    public ListRecyclerViewAdapter(Activity context, List<ItemObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ListRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_view, null);
        ListRecyclerViewHolders rcv = new ListRecyclerViewHolders(layoutView, context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ListRecyclerViewHolders holder, int position) {
        holder.childName.setText(itemList.get(position).getName());
        holder.childDesc.setText(itemList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
