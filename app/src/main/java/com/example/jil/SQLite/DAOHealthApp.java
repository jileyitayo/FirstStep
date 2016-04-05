package com.example.jil.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jil.Users.Users;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by JIL on 04/03/16.
 */
public class DAOHealthApp {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public DAOHealthApp(Context context) {
        dbHelper = new DBHelper(context);
        // Gets the data repository in write mode
        database = dbHelper.getWritableDatabase();
    }

    //close any db object
    public void close() {
        dbHelper.close();
    }

    //Insert Users to DB
    public long InsertUser(Users user)
    {
            // Gets the data repository in write mode
            // Create a new map of values, where column names are the keys
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBTables.HealthUsers.EMAIL_ADDRESS, user.getEmailAddress());
            contentValues.put(DBTables.HealthUsers.PHONE, user.getPhoneNumber());
            contentValues.put(DBTables.HealthUsers.USERNAME, user.getUsername());
            contentValues.put(DBTables.HealthUsers.PASSWORD, user.getPassword());
            contentValues.put(DBTables.HealthUsers.ROLE, user.getRole());
            contentValues.put(DBTables.HealthUsers.CREATED_AT, getDateTime());
            contentValues.put(DBTables.HealthUsers.UPDATED_AT, getDateTime());
        return database.insert(DBTables.HealthUsers.TABLE_NAME, null, contentValues);
    }

    //Get 1 User from DB
    public Users getUsers(String username, String password)
    {
        Users existingUser = new Users(username, password);

        //arguments are
        String[] selectionArgs = {username, password};
        Cursor cursor =
                database.query(DBTables.HealthUsers.TABLE_NAME, DBTables.HealthUsers.ALL_COLUMNS,
                        DBTables.HealthUsers.USERNAME + " = ? AND " + DBTables.HealthUsers.PASSWORD + " = ?"  , selectionArgs, null, null, null);

        cursor.moveToFirst();
        existingUser = cursorToUsers(cursor);

        /*
        while (!cursor.isAfterLast()) {
            Users textReport = cursorToTextReport(cursor);
            textReports.add(textReport);
            cursor.moveToNext();
        }
        */
        // make sure to close the cursor
        cursor.close();
        return existingUser;
    }

    public Users getUsers()
    {
        Users existingUser = new Users();
        Cursor cursor =
                database.query(DBTables.HealthUsers.TABLE_NAME, DBTables.HealthUsers.ALL_COLUMNS,null,null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            existingUser = cursorToUsers(cursor);
        }


        /*
        while (!cursor.isAfterLast()) {
            Users textReport = cursorToTextReport(cursor);
            textReports.add(textReport);
            cursor.moveToNext();
        }
        */
        // make sure to close the cursor
        cursor.close();
        return existingUser;
    }

    public Users getExistingUsers()
    {
        Users existingUser = new Users();
        Cursor cursor =
                database.query(DBTables.HealthUsers.TABLE_NAME, DBTables.HealthUsers.ALL_COLUMNS,null,null, null, null, null);

        cursor.moveToFirst();
        existingUser = cursorToUsers(cursor);
        cursor.close();
        return existingUser;
    }

    public long updateUsersPassword(Users user)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBTables.HealthUsers.PASSWORD, user.getPassword());
        contentValues.put(DBTables.HealthUsers.UPDATED_AT, getDateTime());
        return database.update(DBTables.HealthUsers.TABLE_NAME, contentValues, DBTables.HealthUsers.USERNAME + "= '" + user.getUsername() + "'",null);
    }

    public boolean isUserPresent(Users getUser1)
    {
        Users formUser = getUser1;
        formUser = getUsers();
        long userid = formUser.getId();
        return (userid > 0);
    }
    public boolean isUserPresent(Users getUsers, String username, String password)
    {
        long userid;
        getUsers = getUsers(username, password);
        userid = getUsers.getId();

        return (userid != 0);
    }
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + DBTables.HealthUsers.TABLE_NAME;
        Cursor cursor = database.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private Users cursorToUsers(Cursor cursor) {
        Users existingUser = new Users();

        existingUser.setId(cursor.getInt(cursor.getColumnIndex(DBTables.HealthUsers.USER_ID)));
        existingUser.setUsername(cursor.getString(cursor.getColumnIndex(DBTables.HealthUsers.USERNAME)));
        existingUser.setPassword(cursor.getString(cursor.getColumnIndex(DBTables.HealthUsers.PASSWORD)));
        existingUser.setEmailAddress(cursor.getString(cursor.getColumnIndex(DBTables.HealthUsers.EMAIL_ADDRESS)));
        existingUser.setPhoneNumber(cursor.getInt(cursor.getColumnIndex(DBTables.HealthUsers.PHONE)));
        existingUser.setRole(cursor.getString(cursor.getColumnIndex(DBTables.HealthUsers.ROLE)));
        // here we convert int to boolean
        //existingUser.setIsreported(cursor.getInt(cursor.getColumnIndex(DBtables.TextReport.COLUMN_ISREPOETED)) > 0);
        return existingUser;
    }

}
