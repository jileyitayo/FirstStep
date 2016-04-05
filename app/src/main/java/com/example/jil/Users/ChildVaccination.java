package com.example.jil.Users;

import android.content.Context;

import com.example.jil.firststep.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JIL on 29/03/16.
 */
public class ChildVaccination {
    String vaccination1,vaccination2,vaccination3,vaccination4,vaccination5,vaccination6,vaccination7,vaccination8,vaccination9,vaccination10;
    Child child = new Child();
    List<String> vacc_name = new ArrayList<>();
    String vacc_date_due, vacc_date_due2;

    public ChildVaccination()
    {

    }

    public String getVacc_date_due() {
        return vacc_date_due;
    }

    public String getVacc_date_due2() {
        return vacc_date_due2;
    }

    public List<String> getVacc_name() {
        return vacc_name;
    }

    public void setVacc_date_due(String vacc_date_due) {
        this.vacc_date_due = vacc_date_due;
    }

    public void setVacc_date_due2(String vacc_date_due2) {
        this.vacc_date_due2 = vacc_date_due2;
    }

    public void setVacc_name(List<String> vacc_name, Context activity) {
        this.vacc_name = childVaccination(activity);
    }

    private List<String> childVaccination(Context activity){
        List<String> vacc_list = new ArrayList<String>();
        List<String> duration = new ArrayList<>();
        String[] listOfVaccinations = activity.getResources().getStringArray(R.array.vaccinations);
        String[] listOfDurations = activity.getResources().getStringArray(R.array.vaccination_period);

        String childs_age = child.getAge();
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

    public String getVaccination1() {
        return vaccination1;
    }

    public String getVaccination2() {
        return vaccination2;
    }

    public String getVaccination3() {
        return vaccination3;
    }

    public String getVaccination4() {
        return vaccination4;
    }

    public String getVaccination5() {
        return vaccination5;
    }

    public String getVaccination6() {
        return vaccination6;
    }

    public String getVaccination7() {
        return vaccination7;
    }

    public String getVaccination8() {
        return vaccination8;
    }

    public String getVaccination9() {
        return vaccination9;
    }

    public String getVaccination10() {
        return vaccination10;
    }

    public void setVaccination1(String vaccination1) {
        this.vaccination1 = vaccination1;
    }

    public void setVaccination2(String vaccination2) {
        this.vaccination2 = vaccination2;
    }

    public void setVaccination3(String vaccination3) {
        this.vaccination3 = vaccination3;
    }

    public void setVaccination4(String vaccination4) {
        this.vaccination4 = vaccination4;
    }

    public void setVaccination5(String vaccination5) {
        this.vaccination5 = vaccination5;
    }

    public void setVaccination6(String vaccination6) {
        this.vaccination6 = vaccination6;
    }

    public void setVaccination7(String vaccination7) {
        this.vaccination7 = vaccination7;
    }

    public void setVaccination8(String vaccination8) {
        this.vaccination8 = vaccination8;
    }

    public void setVaccination9(String vaccination9) {
        this.vaccination9 = vaccination9;
    }

    public void setVaccination10(String vaccination10) {
        this.vaccination10 = vaccination10;
    }

}
