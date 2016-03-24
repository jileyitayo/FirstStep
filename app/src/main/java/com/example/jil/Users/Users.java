package com.example.jil.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JIL on 03/03/16.
 */
public class Users implements Serializable {
    private String username;
    private String password;
    private String emailAddress;
    private int phoneNo;
    private String Role;
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
    public void setId(long id)
    {
        this.id = id;
    }
    public String getRole()
    {
        return Role;
    }
    public void setRole(String role)
    {
        this.Role = role;
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

    public static Users fromJson(JSONObject jsonObject)
    {
        Users users = new Users();
        // Deserialize json into object fields
        try {
            users.username = jsonObject.getString("username");
            users.emailAddress = jsonObject.getString("email");
            users.Role = jsonObject.getString("role");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return users;
    }

    public static ArrayList<Users> fromJson(JSONArray jsonArray) {
        JSONObject usersJson;
        ArrayList<Users> listOfUsers = new ArrayList<Users>(jsonArray.length());
        // Process each result in json array, decode and convert to business object
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                usersJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Users users = Users.fromJson(usersJson);
            if (users != null) {
                listOfUsers.add(users);
            }
        }

        return listOfUsers;
    }
}
