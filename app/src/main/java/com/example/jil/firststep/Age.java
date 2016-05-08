package com.example.jil.firststep;

import java.io.Serializable;

/**
 * Created by JIL on 05/05/16.
 */
public class Age implements Serializable
{
    private int days;
    private int months;
    private int years;

    public Age()
    {
        //Prevent default constructor
    }

    public Age(int days, int months, int years)
    {
        this.days = days;
        this.months = months;
        this.years = years;
    }

    public int getDays()
    {
        return this.days;
    }

    public int getMonths()
    {
        return this.months;
    }

    public int getYears()
    {
        return this.years;
    }

    @Override
    public String toString()
    {
        return years + " Years, " + months + " Months, " + days + " Days";
    }
}
