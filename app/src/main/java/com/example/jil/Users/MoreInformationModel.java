package com.example.jil.Users;

import com.example.jil.androidrecyclerviewgridview.ItemObject;

/**
 * Created by JIL on 15/03/16.
 */
public class MoreInformationModel {
    private long info_id;
    ItemObject object = new ItemObject();
    private Users user = new Users();
    private Child child = new Child();

    public MoreInformationModel(Child child1, Users user1) {
        this.user = user1;
        this.child = child1;
    }

    public MoreInformationModel() {

    }

    public long getInfo_id()
    {
        return info_id;
    }

    public void setInfo_id(long id)
    {
        this.info_id = id;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public void setUsername(String username) {
        user.setUsername(username);
    }

    public String getChildName() {
        String nameOfChild  = child.getfirstName() + " " + child.getLastName();
        return nameOfChild;
    }

    public void setChildname(String childFirstname, String childLastName) {
        child.setFirstName(childFirstname);
        child.setLastName(childLastName);
    }

    public String getInfo_title() {
        return object.getName();
    }

    public void setInfo_title(String info_title1) {
        this.object.setName(info_title1);
    }

    public String getInfo_details() {
        return object.getDescription();
    }

    public void setInfo_details(String info_details1) {
        this.object.setDescription(info_details1);
    }

    public Child getChild()
    {
        return child;
    }

}
