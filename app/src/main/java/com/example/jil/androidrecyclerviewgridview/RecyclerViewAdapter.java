package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jil.firststep.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<ItemObject> itemList;
    private Activity context;

    public RecyclerViewAdapter(Activity context, List<ItemObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView, context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.activityName.setText(itemList.get(position).getName());
        holder.activitypic.setImageResource(itemList.get(position).getPhoto());
        holder.activityDesc.setText(itemList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
