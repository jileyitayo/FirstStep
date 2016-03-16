package com.example.jil.Users;

/**
 * Created by JIL on 15/03/16.
 */
public class MoreInformationModel {
    private long info_id;
    private String info_title;
    private String info_details;
    private Users user = new Users();
    private Child child = new Child();

    public MoreInformationModel(Child child1, Users user1) {
        this.user = user1;
        this.child = child1;
    }

    public MoreInformationModel() {

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
        child.setFirstName(childLastName);
    }

    public String getInfo_title() {
        return info_title;
    }

    public void setInfo_id(String info_title1) {
        this.info_title = info_title1;
    }

    public String getInfo_details() {
        return info_details;
    }

    public void setInfo_details(String info_details1) {
        this.info_details = info_details1;
    }



}
