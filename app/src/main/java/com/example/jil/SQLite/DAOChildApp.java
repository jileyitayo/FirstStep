package com.example.jil.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jil.Users.Child;
import com.example.jil.Users.Users;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JIL on 09/03/16.
 */
public class DAOChildApp {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    Context activityContext;
    private DAOMoreInformation moreInformation;
    List<DAOMoreInformation> mylist = new ArrayList<>();

    public DAOChildApp(Context context) {
        dbHelper = new DBHelper(context);
        // Gets the data repository in write mode
        activityContext = context;
        database = dbHelper.getWritableDatabase();
    }

    //close any db object
    public void close() {
        dbHelper.close();
    }

    //Insert Users to DB
    public long InsertChild(Child child, Users user) {
        moreInformation = new DAOMoreInformation(activityContext);
        // Gets the data repository in write mode
        // Create a new map of values, where column names are the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBTables.Children.FIRST_NAME, child.getfirstName());
        contentValues.put(DBTables.Children.LAST_NAME, child.getLastName());
        contentValues.put(DBTables.Children.GENDER, child.getGender());
        contentValues.put(DBTables.Children.DOB, child.getDateOfBirth());
        contentValues.put(DBTables.Children.MOREINFO_COUNT, moreInformation.ChildInfoCount(child));
        contentValues.put(DBTables.Children.USER_ID, user.getId()); // gets userid for the user that added the child
        contentValues.put(DBTables.Children.USERNAME, user.getUsername());
        contentValues.put(DBTables.Children.CREATED_AT, getDateTime());
        contentValues.put(DBTables.Children.UPDATED_AT, getDateTime());
        return database.insert(DBTables.Children.TABLE_NAME, null, contentValues);
    }

    //getChild details from db
    public List<Child> getAllChildren() {
        List<Child> childrenList = new ArrayList<>();
        Cursor cursor =
                database.query(DBTables.Children.TABLE_NAME, DBTables.Children.ALL_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Child existingChild = cursorToChildren(cursor);
            childrenList.add(existingChild);
            cursor.moveToNext();
        }
        cursor.close();
        return childrenList;
    }

    public int ChildInfoCount(Child child)
    {
        List<Child> childrenInfoList = new ArrayList<>();

        String[] selectionArgs = {child.getfirstName(), child.getLastName()};
        Cursor cursor =
                database.query(DBTables.Children.TABLE_NAME, DBTables.Children.ALL_COLUMNS,
                        DBTables.Children.FIRST_NAME + " = ? AND " + DBTables.Children.LAST_NAME + " = ?", selectionArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Child existingChild = cursorToChildren(cursor);
            childrenInfoList.add(existingChild);
            cursor.moveToNext();
        }
        cursor.close();
        return childrenInfoList.size();
    }

    //Get 1 User from DB
    public Child getSingleChild(String fName, String LName) {
        Child existingUser = new Child();
        //arguments are
        String[] selectionArgs = {fName, LName};
        Cursor cursor = null;
        cursor = database.query(DBTables.Children.TABLE_NAME, DBTables.Children.ALL_COLUMNS,
                DBTables.Children.FIRST_NAME + " = ? AND " + DBTables.Children.LAST_NAME + " = ?", selectionArgs, null, null, null);
        cursor.moveToFirst();
        if (cursor != null)
        {
            if(cursor.moveToFirst()) {
                existingUser = cursorToChildren(cursor);
            }
        }
        return existingUser;
    }

    public int getChildRowCount() {
        String countQuery = "SELECT  * FROM " + DBTables.Children.TABLE_NAME;
        Cursor cursor = database.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public boolean deleteChild(Child child)
    {
        moreInformation = new DAOMoreInformation(activityContext);
        String[] selectionArgs = {child.getfirstName(), child.getLastName()};
        moreInformation.deleteChildInfo(child);
        return  database.delete(DBTables.Children.TABLE_NAME, DBTables.Children.FIRST_NAME + " = ? AND " + DBTables.Children.LAST_NAME + " = ?", selectionArgs) > 0;
    }

    public boolean isChildPresent(Child getChild12) {
        long childid = 0;
        Child formChild = getChild12;
        formChild = getSingleChild(getChild12.getfirstName(), getChild12.getLastName());
        if(formChild == null)
            childid = 0;
        else
        childid = formChild.getChild_Id();

        return (childid > 0);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private Child cursorToChildren(Cursor cursor) {
        Child existingChild = new Child();

        existingChild.setChild_id(cursor.getLong(cursor.getColumnIndex(DBTables.Children.CHILD_ID)));
        existingChild.setFirstName(cursor.getString(cursor.getColumnIndex(DBTables.Children.FIRST_NAME)));
        existingChild.setLastName(cursor.getString(cursor.getColumnIndex(DBTables.Children.LAST_NAME)));
        existingChild.setGender(cursor.getString(cursor.getColumnIndex(DBTables.Children.GENDER)));
        existingChild.setDateOfBirth(cursor.getString(cursor.getColumnIndex(DBTables.Children.DOB)));
        existingChild.setUserId(cursor.getInt(cursor.getColumnIndex(DBTables.Children.USER_ID)));
        existingChild.setUsername(cursor.getString(cursor.getColumnIndex(DBTables.Children.USERNAME)));

        // here we convert int to boolean
        //existingUser.setIsreported(cursor.getInt(cursor.getColumnIndex(DBtables.TextReport.COLUMN_ISREPOETED)) > 0);
        return existingChild;
    }
}
