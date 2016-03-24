package com.example.jil.androidrecyclerviewgridview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jil.Users.Users;
import com.example.jil.firststep.R;

import java.util.List;

/**
 * Created by JIL on 18/03/16.
 */
public class ChatUsersAdpt extends RecyclerView.Adapter<ChatUsersHldr> {
    private List<Users> itemList;
    private Activity context;

    public ChatUsersAdpt(Activity context, List<Users> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ChatUsersHldr onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_listitem_user, null);
        ChatUsersHldr rcv = new ChatUsersHldr(layoutView, context, itemList);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ChatUsersHldr holder, int position) {
        holder.TUsername.setText(itemList.get(position).getUsername());
        holder.TEmail.setText(itemList.get(position).getEmailAddress());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}

