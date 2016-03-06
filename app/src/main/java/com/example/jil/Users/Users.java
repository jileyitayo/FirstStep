package com.example.jil.Users;

/**
 * Created by JIL on 03/03/16.
 */
public class Users {
    private String username;
    private String password;
    private String emailAddress;
    private int phoneNo;
    private long id;

    public Users(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.emailAddress = null;
        this.phoneNo = 00000000;
        this.id = 0;
    }
    public Users(long id, String username, String password, String emailAddress, int phoneNumber)
    {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.phoneNo = phoneNumber;
        this.id = id;
    }

    public Users(String username)
    {
        this.username = username;
        this.password = "root";
        this.emailAddress = null;
        this.phoneNo = 00000000;
        this.id = 0;
    }
    public Users()
    {

    }

    public long getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getEmailAddress()
    {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }
    public int getPhoneNumber()
    {
        return phoneNo;
    }
    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNo = phoneNumber;
    }
    public int getUserCount(int countFromDB)
    {
        return countFromDB;
    }
}
