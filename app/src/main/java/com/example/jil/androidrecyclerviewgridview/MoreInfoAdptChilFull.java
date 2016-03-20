package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jil.Users.MoreInformationModel;
import com.example.jil.firststep.R;

import java.util.List;

/**
 * Created by JIL on 18/03/16.
 */
public class MoreInfoAdptChilFull extends RecyclerView.Adapter<MoreInfoHldrChildFull> {
    private List<MoreInformationModel> itemList;
    private Activity context;

    public MoreInfoAdptChilFull(Activity context, List<MoreInformationModel> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public MoreInfoHldrChildFull onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_view2, null);
        MoreInfoHldrChildFull rcv = new MoreInfoHldrChildFull(layoutView, context, itemList);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MoreInfoHldrChildFull holder, int position) {
        holder.childName.setText(itemList.get(position).getInfo_title());
        holder.childDesc.setText(itemList.get(position).getInfo_details());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}

