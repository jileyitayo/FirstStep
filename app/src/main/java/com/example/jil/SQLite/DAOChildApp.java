package com.example.jil.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        contentValues.put(DBTables.Children.IMAGE_PATH, child.getImg_path());
        contentValues.put(DBTables.Children.ADDRESS_LOCATION, child.getAddLocation());
        contentValues.put(DBTables.Children.ALLERGIES, child.getAllergies());
        contentValues.put(DBTables.Children.HEIGHT, child.getHeight());
        contentValues.put(DBTables.Children.WEIGHT, child.getWeight());
        contentValues.put(DBTables.Children.PARENT_NAMES, child.getParentName());
        contentValues.put(DBTables.Children.VACCINATION_DUE, child.getVaccination_due());
        contentValues.put(DBTables.Children.VACCINATION_TAKEN, child.getVaccinationTaken());
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
        List<Child> childrenInfoList = getAllChildren();
        return childrenInfoList.size();
    }

    //Get 1 User from DB
    public Child getSingleChild(Child child) {

        Child existingUser = new Child();
        //arguments are
        String[] selectionArgs = {child.getfirstName(), child.getLastName(), child.getDateOfBirth()};
        Cursor cursor = null;
        cursor = database.query(DBTables.Children.TABLE_NAME, DBTables.Children.ALL_COLUMNS,
                DBTables.Children.FIRST_NAME + " = ? AND " + DBTables.Children.LAST_NAME + " = ? AND " + DBTables.Children.DOB + " = ?", selectionArgs, null, null, null);
        cursor.moveToFirst();
        existingUser = cursorToChildren(cursor);
        return existingUser;
    }
    public void updateChildDetails(Child child, String detailinfo, String actualdetail) {
        ContentValues contentValues = new ContentValues();

        switch (detailinfo) {
            case DBTables.Children.FIRST_NAME:
                contentValues.put(DBTables.Children.FIRST_NAME, actualdetail);
                contentValues.put(DBTables.Children.UPDATED_AT, getDateTime());
                //String[] selectionArgs = {child.getLastName()};
                database.update(DBTables.Children.TABLE_NAME, contentValues, DBTables.Children.FIRST_NAME + "= '" + child.getfirstName() + "' AND " + DBTables.Children.LAST_NAME + "= '" + child.getLastName() + "'" ,null);
                break;
            case DBTables.Children.LAST_NAME:
                contentValues.put(DBTables.Children.LAST_NAME, actualdetail);
                contentValues.put(DBTables.Children.UPDATED_AT, getDateTime());
                //String[] selectionArgs = {child.getLastName()};
                database.update(DBTables.Children.TABLE_NAME, contentValues, DBTables.Children.FIRST_NAME + "= '" + child.getfirstName() + "' AND " + DBTables.Children.LAST_NAME + "= '" + child.getLastName() + "'" ,null);

                break;
            case DBTables.Children.DOB:
                contentValues.put(DBTables.Children.DOB, actualdetail);
                break;
            case DBTables.Children.HEIGHT:
                contentValues.put(DBTables.Children.HEIGHT, actualdetail);
                break;
            case DBTables.Children.WEIGHT:
                contentValues.put(DBTables.Children.WEIGHT, actualdetail);
                break;
            case DBTables.Children.GENDER:
                contentValues.put(DBTables.Children.GENDER, actualdetail);
                break;
            case DBTables.Children.ALLERGIES:
                contentValues.put(DBTables.Children.ALLERGIES, actualdetail);
                break;
            case DBTables.Children.ADDRESS_LOCATION:
                contentValues.put(DBTables.Children.ADDRESS_LOCATION, actualdetail);
                break;
            case DBTables.Children.IMAGE_PATH:
                contentValues.put(DBTables.Children.IMAGE_PATH, actualdetail);
                break;
            case DBTables.Children.PARENT_NAMES:
                contentValues.put(DBTables.Children.PARENT_NAMES, actualdetail);
                break;

            default:

                break;
        }
        //contentValues.put(DBTables.Children.UPDATED_AT, getDateTime());
        //String[] selectionArgs = {child.getfirstName()};//child.getLastName()
        String rawQurey = "UPDATE " + DBTables.Children.TABLE_NAME + " Set " + DBTables.Children.FIRST_NAME + " = '" + actualdetail + "' Where " + DBTables.Children.FIRST_NAME + " = ?;";//AND " + DBTables.Children.LAST_NAME + "= ?
        //database.rawQuery(rawQurey, selectionArgs);
        //return database.update(DBTables.Children.TABLE_NAME, contentValues, DBTables.Children.FIRST_NAME + "= '" + child.getfirstName() + "'",null);

        //return database.update(DBTables.Children.TABLE_NAME, contentValues, DBTables.Children.FIRST_NAME + "= ?",selectionArgs);
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
        //moreInformation = new DAOMoreInformation(activityContext);
        String[] selectionArgs = {child.getfirstName(), child.getLastName()};
        //moreInformation.deleteChildInfo(child);
        return  database.delete(DBTables.Children.TABLE_NAME, DBTables.Children.FIRST_NAME + " = ? AND " + DBTables.Children.LAST_NAME + " = ?", selectionArgs) > 0;
    }

    public boolean isChildPresent(Child getChild12) {
        //long childid = 0;
        boolean status = false;
        //Child retChild = new Child();
        for (Child child1: getAllChildren())
        {
            if((getChild12.getfirstName().equals(child1.getfirstName().toUpperCase())) &&
                    (getChild12.getLastName().equals(child1.getLastName().toUpperCase())) &&
                    (getChild12.getDateOfBirth().equals(child1.getDateOfBirth())))
            {
                status = true;
            }
        }
        Child formChild = getChild12;
        /*formChild = getSingleChild(getChild12);
        if(formChild == null)
            childid = 0;
        else
        childid = formChild.getChild_Id();
        */
        return status;
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
        existingChild.setAddLocation(cursor.getString(cursor.getColumnIndex(DBTables.Children.ADDRESS_LOCATION)));
        existingChild.setAllergies(cursor.getString(cursor.getColumnIndex(DBTables.Children.ALLERGIES)));
        existingChild.setMoreInfo(cursor.getString(cursor.getColumnIndex(DBTables.Children.MOREINFO_COUNT)));
        existingChild.setHeight(cursor.getString(cursor.getColumnIndex(DBTables.Children.HEIGHT)));
        existingChild.setImg_path(cursor.getString(cursor.getColumnIndex(DBTables.Children.IMAGE_PATH)));
        existingChild.setParentName(cursor.getString(cursor.getColumnIndex(DBTables.Children.PARENT_NAMES)));
        existingChild.setVaccination_due(cursor.getString(cursor.getColumnIndex(DBTables.Children.VACCINATION_DUE)));
        existingChild.setVaccinationTaken(cursor.getString(cursor.getColumnIndex(DBTables.Children.VACCINATION_TAKEN)));
        existingChild.setWeight(cursor.getString(cursor.getColumnIndex(DBTables.Children.WEIGHT)));


        // here we convert int to boolean
        //existingUser.setIsreported(cursor.getInt(cursor.getColumnIndex(DBtables.TextReport.COLUMN_ISREPOETED)) > 0);
        return existingChild;
    }

    private Child cursorToChildren2(Cursor cursor) {
        Child existingChild = new Child();
        existingChild.setFirstName(cursor.getString(cursor.getColumnIndex(DBTables.Children.FIRST_NAME)));
        existingChild.setLastName(cursor.getString(cursor.getColumnIndex(DBTables.Children.LAST_NAME)));
        existingChild.setGender(cursor.getString(cursor.getColumnIndex(DBTables.Children.GENDER)));
        existingChild.setDateOfBirth(cursor.getString(cursor.getColumnIndex(DBTables.Children.DOB)));
        existingChild.setUserId(cursor.getInt(cursor.getColumnIndex(DBTables.Children.USER_ID)));
        existingChild.setUsername(cursor.getString(cursor.getColumnIndex(DBTables.Children.USERNAME)));
        existingChild.setAddLocation(cursor.getString(cursor.getColumnIndex(DBTables.Children.ADDRESS_LOCATION)));
        existingChild.setAllergies(cursor.getString(cursor.getColumnIndex(DBTables.Children.ALLERGIES)));
        existingChild.setMoreInfo(cursor.getString(cursor.getColumnIndex(DBTables.Children.MOREINFO_COUNT)));
        existingChild.setHeight(cursor.getString(cursor.getColumnIndex(DBTables.Children.HEIGHT)));
        existingChild.setImg_path(cursor.getString(cursor.getColumnIndex(DBTables.Children.IMAGE_PATH)));
        existingChild.setParentName(cursor.getString(cursor.getColumnIndex(DBTables.Children.PARENT_NAMES)));
        existingChild.setVaccination_due(cursor.getString(cursor.getColumnIndex(DBTables.Children.VACCINATION_DUE)));
        existingChild.setVaccinationTaken(cursor.getString(cursor.getColumnIndex(DBTables.Children.VACCINATION_TAKEN)));
        existingChild.setWeight(cursor.getString(cursor.getColumnIndex(DBTables.Children.WEIGHT)));


        // here we convert int to boolean
        //existingUser.setIsreported(cursor.getInt(cursor.getColumnIndex(DBtables.TextReport.COLUMN_ISREPOETED)) > 0);
        return existingChild;
    }
}
