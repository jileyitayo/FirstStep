package com.example.jil.Users;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.PeriodicSync;
import android.util.Log;

import com.example.jil.firststep.Age;
import com.example.jil.firststep.AgeCalculator;
import com.example.jil.firststep.R;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

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
    private Age realAge;
    private String profPic;
    private String DueDate;
    private String HasTaken;
    private Users user = new Users();
    private int month, realmonth, year, week, day, hours;

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

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public List<String> childVaccination(Context activity){
        List<String> vacc_list = new ArrayList<String>();
        List<String> duration = new ArrayList<>();
        String[] listOfVaccinations = activity.getResources().getStringArray(R.array.vaccinations);
        String[] listOfDurations = activity.getResources().getStringArray(R.array.vaccination_period);



        String childs_age = getAge();
            if(year == 0){
                if((hours <= 24))
                {
                    vacc_list.add("(BCG) Bacillus Calmette-Guerin vaccine");
                    vacc_list.add("(Hep B) Hepatitis B vaccine");
                    ///////////////////////////////
                    vacc_list.add("Polio vaccine");
                    vacc_list.add("DTP - Diptheria, Tetanus and acellular Pertussis vaccine");
                    vacc_list.add("Haemophilus Influenzae Type B vaccine");
                    vacc_list.add("Pneumococcal vaccine");
                    vacc_list.add("Rotavirus vaccine");
                    vacc_list.add("Measles vaccine");
                    vacc_list.add("Rubella vaccine");
                    vacc_list.add("Seasonal Influenza vaccine");
                    vacc_list.add("Yellow Fever");
                    vacc_list.add("Mumps vaccine");
                    vacc_list.add("Varicella vaccine");
                }
                if((week >= 6 && week <= 8))
                {
                    vacc_list.clear();
                    ////////////
                    vacc_list.add("Polio vaccine");
                    vacc_list.add("DTP - Diptheria, Tetanus and acellular Pertussis vaccine");

                }
                if((week >= 6 && week <= 59))
                {
                    vacc_list.remove("(BCG) Bacillus Calmette-Guerin vaccine");
                    vacc_list.remove("(Hep B) Hepatitis B vaccine");
                    vacc_list.add("Haemophilus Influenzae Type B vaccine");
                }
                if(week >= 6)
                {
                    vacc_list.remove("(BCG) Bacillus Calmette-Guerin vaccine");
                    vacc_list.remove("(Hep B) Hepatitis B vaccine");
                    vacc_list.add("Pneumococcal vaccine");
                    vacc_list.add("Rotavirus vaccine");
                }
                if(month >= 6 || (realmonth >= 9  && realmonth <= 12))
                {
                    vacc_list.add("Measles vaccine");
                    vacc_list.add("Rubella vaccine");
                    vacc_list.add("Seasonal Influenza vaccine");

                    if(realmonth >= 9  && realmonth <= 12)
                    {
                        vacc_list.add("Yellow Fever");
                    }

                }
                if(getGender().toUpperCase().equals("FEMALE"))
                {
                    if(realmonth >= 9 )
                    {
                        vacc_list.add("HPV - Human papiloma virus vaccine");
                    }
                }
                if(realmonth >= 12 && realmonth <= 18)
                {
                    vacc_list.add("Mumps vaccine");
                    vacc_list.add("Varicella vaccine");
                }

            }
        if(year >= 2 )
        {
            vacc_list.add("Typhoid vaccine");
        }
        if(year >= 1 )
        {
            vacc_list.add("Cholera vaccine");
            vacc_list.add("Hepatitis A");
        }

        for (String vacc : vacc_list)
        {
            if(vacc == "(BCG) Bacillus Calmette-Guerin vaccine")
            {
                setDueDate("Now");
            }
            if(vacc == "(Hep B) Hepatitis B vaccine")
            {
                setDueDate("Now");
            }
            if(vacc == "Polio vaccine" || vacc == "DTP - Diptheria, Tetanus and acellular Pertussis vaccine")
            {
                setDueDate("Due in 2 months");
            }

            if(vacc == "Haemophilus Influenzae Type B vaccine")
            {
                 setDueDate("Due in 10 months");
            }
            if(vacc == "Pneumococcal vaccine" || vacc == "Rotavirus vaccine")
            {
                setDueDate("Give your child this vaccine NOW");
            }
            if(vacc == "Measles vaccine" || vacc == "Rubella vaccine" || vacc == "Seasonal Influenza vaccine")
            {
                setDueDate("Give your child this vaccine NOW");
            }

            if(vacc == "Mumps vaccine") {
                setDueDate("Give your child this vaccine NOW");
            }
            if(vacc == "Varicella vaccine")
            {
                setDueDate("Due in 6 months");
            }
            if(vacc == "Yellow Fever")
            {
                setDueDate("Due in 3 months");
            }


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
        AgeCalculator calculator = new AgeCalculator();
        Age age = new Age();
        String diff = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date birthDate = sdf.parse(DOB);
            age = calculator.calculateAge(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        realAge = age;
        day = age.getDays();
        hours = ((age.getYears() * 365) + (month*30)) * 24;
        month =  age.getMonths();
        realmonth = (age.getYears() * 12) + month;
        week = ((age.getYears() * 365) + (month*30)) / 7;
        year = age.getYears();


        /*
        Calendar c = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("dd/mm/yyyy");
        String strDate = simpleDateFormat.format(c.getTime());
        java.sql.Date javaSqlDate = java.sql.Date.valueOf(DOB);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(javaSqlDate);
        LocalDate birthdate = new LocalDate (calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));          //Birth date
        LocalDate now = new LocalDate();                    //Today's date
        Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
        day = period.getDays();
        hours = period.getHours();
        month =  period.getMonths();
        week = period.getWeeks();
        year = period.getYears();

        Log.d("day", String.valueOf(day));
        Log.d("day", String.valueOf(day));
        */
        diff = printDifference();
        return diff;
    }

    public String printDifference(Date startDate, Date endDate){


        double diffInYears, diffInMonths, diffInWeeks;
        String diff = null;
        //milliseconds
        double different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);
        /*
        long diffInDays = TimeUnit.MILLISECONDS.toDays(different);
        long diffInHours = TimeUnit.MILLISECONDS.toHours(different);
        long diffInMin = TimeUnit.MILLISECONDS.toMinutes(different);
        long diffInSec = TimeUnit.MILLISECONDS.toSeconds(different);

        if(diffInDays >= 365)
        {
             diffInYears = (diffInDays / 365);
             diffInMonths = (long) Math.round ((diffInYears * 12) / 30.455);

            while(diffInMonths >= 12)
            {
                double myYears = diffInMonths / 12;
                 diffInYears  = diffInYears + (long)Math.ceil(myYears);
            }
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
            hours = String.valueOf(diffInHours);
            day = String.valueOf(diffInDays);
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
            hours = String.valueOf(diffInHours);
            day = String.valueOf(diffInDays);
        }
        long elapsedWeeks = diffInDays / 7;
        week = String.valueOf(elapsedWeeks);
        return diff;
        */



        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        double daysInMilli = (hoursInMilli * 24);
        double monthInMilli = daysInMilli * 30.44;
        double yearsInMilli = monthInMilli * 12;

        int elapsedYears = (int) (different / yearsInMilli);
        different = different % yearsInMilli;

        int elapsedMonths = (int) Math.floor(different / monthInMilli);
        different = (different % monthInMilli);

        double elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        double elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        double elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        double elapsedSeconds = different / secondsInMilli;

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


        double[] dates = {year, month, };
        return diff;

    }

    public String printDifference()
    {
        String diff = null;
        if(month <= 1 && year <=1)
        {
            diff = year + " year, " + month + " month old";
        }
        else if(year <=1)
        {
            diff = year + " year, " + month + " months old";
        }
        else if(month <=1)
        {
            diff = year + " years, " + month + " month old";
        }
        else
            diff = year + " years, " + month + " months old";


        return diff;

    }
}



