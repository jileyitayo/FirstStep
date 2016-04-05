package com.example.jil.Users;

import android.content.Context;

import com.example.jil.firststep.R;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by JIL on 03/03/16.
 */
public class Child implements Serializable {
    private String firstName;
    private String lastName, height, img_path, addLocation, allergies, vaccinationTaken, vaccination_due, parentName;
    private long child_id;
    private String dateOfBirth;
    private String gender, weight;
    private String moreInfo;
    private String age;
    private String profPic;
    private Users user = new Users();
    private String month, year;

    public Child(String firstName, String lastName, Users id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = id;
        this.child_id = 0;
    }
    public Child(String firstName, String lastName, String gender, String age, String pic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.profPic = pic;
        this.child_id = 0;
    }

    public Child() {
    }

    public List<String> childVaccination(Context activity){
        List<String> vacc_list = new ArrayList<String>();
        List<String> duration = new ArrayList<>();
        String[] listOfVaccinations = activity.getResources().getStringArray(R.array.vaccinations);
        String[] listOfDurations = activity.getResources().getStringArray(R.array.vaccination_period);

        String childs_age = getAge();
        for (int i = 0; i < listOfVaccinations.length; i++) {
            vacc_list.add(listOfVaccinations[i]);

            /*
            if(year.equals("0")){
                if(month.equals("0"))
                {

                }
                else if(month.equals("6"))
                {

                }
                else if(month.equals("9"))
                {

                }
            } */

        }


return vacc_list;
    }

    public String getAge() {
        age = getAgeDifference(dateOfBirth);
        return age;
    }
    public String getProfPic() {
        return profPic;
    }

    public void setProfPic(String profPic) {
        this.profPic = profPic;
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


    private String getAgeDifference(String DOB)
    {
        String diff = null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("dd/mm/yyyy");
        String strDate = simpleDateFormat.format(c.getTime());

        try {

            Date date1 = simpleDateFormat.parse(DOB);
            Date date2 = simpleDateFormat.parse(strDate);
            diff = printDifference(date1, date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }
    public String printDifference(Date startDate, Date endDate){
        long diffInYears, diffInMonths, diffInWeeks;
        String diff = null;
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long diffInDays = TimeUnit.MILLISECONDS.toDays(different);
        long diffInHours = TimeUnit.MILLISECONDS.toHours(different);
        long diffInMin = TimeUnit.MILLISECONDS.toMinutes(different);
        long diffInSec = TimeUnit.MILLISECONDS.toSeconds(different);

        if(diffInDays >= 365)
        {
             diffInYears = (diffInDays / 365);
             diffInMonths = (long) Math.round ((diffInYears * 12) / 30.455);

            if(diffInMonths <=1 && diffInYears <= 1)
            {
                diff = diffInYears + " year, " + diffInMonths + " month old";
            }
            else if(diffInMonths <=1)
            {
                diff = diffInYears + " years, " + diffInMonths + " month old";
            }

            else
            {
                diff = diffInYears + " years, " + diffInMonths + " months old";;
            }
            month = String.valueOf(diffInMonths);
            year = String.valueOf(diffInYears);
        }

        else if(diffInDays < 365)
        {
             diffInYears = 0;
            diffInMonths = (long) Math.round(diffInDays / 30.455);
            if(diffInMonths <=1)
            {
                diff = diffInYears + " year, " + diffInMonths + " month old";
            }
            else
                diff = diffInYears + " year, " + diffInMonths + " months old";
            month = String.valueOf(diffInMonths);
            year = String.valueOf(diffInYears);
        }
        return diff;
        /*
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long monthInMilli = daysInMilli * 30;
        long yearsInMilli = monthInMilli * 12;

        long elapsedYears = different / yearsInMilli;
        different = different % yearsInMilli;

        long elapsedMonths = different / monthInMilli;
        different = (different % monthInMilli);

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        String diff = null;
        if(elapsedMonths <= 1 && elapsedYears <=1)
        {
            diff = elapsedYears + " year, " + elapsedMonths + " month old";
        }
        else if(elapsedYears <=1)
        {
            diff = elapsedYears + " year, " + elapsedMonths + " months old";
        }
        else if(elapsedMonths <=1)
        {
            diff = elapsedYears + " years, " + elapsedMonths + " month old";
        }
        else
            diff = elapsedYears + " years, " + elapsedMonths + " months old";


        return diff;
        */
    }
}



