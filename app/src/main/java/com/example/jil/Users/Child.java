package com.example.jil.Users;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by JIL on 03/03/16.
 */
public class Child {
    private String firstName;
    private String lastName, height, img_path, addLocation, allergies, vaccinationTaken, vaccination_due, parentName;
    private long child_id;
    private String dateOfBirth;
    private String gender, weight;
    private String moreInfo;
    private Users user = new Users();

    public Child(String firstName, String lastName, Users id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = id;
        this.child_id = 0;
    }

    public Child() {

    }

    public long getUserId() {
        return user.getId();
    }

    public void setUserId(long id) {
        user.setId(id);
    }

    public String getUsername() {
        return user.getUsername();
    }

    public void setUsername(String username) {
        if (username == "") {
            username = "";
            user.setUsername(username);
        }
        user.setUsername(username);
    }
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getChild_Id() {
        return child_id;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public void setChild_id(long child_id) {
        this.child_id = child_id;
    }

    public String getAddLocation() {
        return addLocation;
    }

    public void setAddLocation(String addLocation) {
        this.addLocation = addLocation;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getVaccination_due() {
        return vaccination_due;
    }

    public void setVaccination_due(String vaccination_due) {
        this.vaccination_due = vaccination_due;
    }

    public String getVaccinationTaken() {
        return vaccinationTaken;
    }

    public void setVaccinationTaken(String vaccinationTaken) {
        this.vaccinationTaken = vaccinationTaken;
    }
}


