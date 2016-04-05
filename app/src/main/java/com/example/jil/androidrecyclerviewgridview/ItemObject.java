package com.example.jil.androidrecyclerviewgridview;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.jil.Users.Child;

import java.io.Serializable;

public class ItemObject implements Serializable {

    private String name;
    private int photo;
    private String profPic;
    private String description;

    public ItemObject(String name, int photo, String description) {
        this.name = name;
        this.photo = photo;
        this.description = description;
    }
    public ItemObject(String name,String description) {
        this.name = name;
        this.description = description;
    }
    public ItemObject(String name,String description, String profPic) {
        this.name = name;
        this.description = description;
        this.profPic =profPic;
    }
    public ItemObject()
    {

    }

    public String getProfPic() {
        return profPic;
    }

    public void setProfPic(String profPic) {
        this.profPic = profPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
