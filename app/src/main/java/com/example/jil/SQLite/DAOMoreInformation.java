package com.example.jil.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jil.Users.Child;
import com.example.jil.Users.MoreInformationModel;
import com.example.jil.Users.Users;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JIL on 15/03/16.
 */
public class DAOMoreInformation {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    public DAOMoreInformation(Context context) {
        dbHelper = new DBHelper(context);
        // Gets the data repository in write mode
        database = dbHelper.getWritableDatabase();
    }

    //close any db object
    public void close() {
        dbHelper.close();
    }

    //Insert Users to DB
    public long InsertInfo(MoreInformationModel moreInformationModel, Child child, Users user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBTables.MoreInfo.INFO_TITLE,  moreInformationModel.getInfo_title());
        contentValues.put(DBTables.MoreInfo.INFO_DETAILS, moreInformationModel.getInfo_details());
        contentValues.put(DBTables.MoreInfo.CHILD_ID, child.getChild_Id());
        contentValues.put(DBTables.MoreInfo.CHILD_FIRST_NAME, child.getfirstName());
        contentValues.put(DBTables.MoreInfo.CHILD_LAST_NAME, child.getLastName()); // gets userid for the user that added the child
        contentValues.put(DBTables.MoreInfo.USERNAME, user.getUsername());
        contentValues.put(DBTables.MoreInfo.CREATED_AT, getDateTime());
        contentValues.put(DBTables.MoreInfo.UPDATED_AT, getDateTime());
        return database.insert(DBTables.MoreInfo.TABLE_NAME, null, contentValues);
    }

    //getChild details from db
    public List<MoreInformationModel> getAllChildInfo() {
        List<MoreInformationModel> childrenInfoList = new ArrayList<>();
        Cursor cursor =
                database.query(DBTables.MoreInfo.TABLE_NAME, DBTables.MoreInfo.ALL_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MoreInformationModel existingChildInfo = cursorToInfo(cursor);
            childrenInfoList.add(existingChildInfo);
            cursor.moveToNext();
        }
        cursor.close();
        return childrenInfoList;
    }

    public int ChildInfoCount(Child child)
    {
        List<MoreInformationModel> childrenInfoList = new ArrayList<>();

        String[] selectionArgs = {child.getfirstName(), child.getLastName()};
        Cursor cursor =
                database.query(DBTables.MoreInfo.TABLE_NAME, DBTables.MoreInfo.ALL_COLUMNS,
                        DBTables.MoreInfo.CHILD_FIRST_NAME + " = ? AND " + DBTables.MoreInfo.CHILD_LAST_NAME + " = ?", selectionArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MoreInformationModel existingChildInfo = cursorToInfo(cursor);
            childrenInfoList.add(existingChildInfo);
            cursor.moveToNext();
        }
        cursor.close();
        return childrenInfoList.size();
    }


    //Get 1 User from DB
    public List<MoreInformationModel> getSingleChildInfo(Child existingChild) {
        List<MoreInformationModel> childrenInfoList = new ArrayList<>();
        //arguments are
        String[] selectionArgs = {existingChild.getfirstName(), existingChild.getLastName()};
        Cursor cursor = null;
        cursor = database.query(DBTables.MoreInfo.TABLE_NAME, DBTables.MoreInfo.ALL_COLUMNS,
                DBTables.MoreInfo.CHILD_FIRST_NAME + " = ? AND " + DBTables.MoreInfo.CHILD_LAST_NAME + " = ?", selectionArgs, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MoreInformationModel existingChildInfo = cursorToInfo(cursor);
            childrenInfoList.add(existingChildInfo);
            cursor.moveToNext();
        }
        cursor.close();
        return childrenInfoList;
    }
/*
    public boolean isChildInfoPresent(MoreInformationModel moreInformationModel) {
        long childInfoId = 0;
        MoreInformationModel formChildInfo = moreInformationModel;
        formChildInfo = getSingleChildInfo(moreInformationModel.getChild());
        if(formChildInfo == null)
            childInfoId = 0;
        else
            childInfoId = formChildInfo.getInfo_id();

        return (childInfoId > 0);
    }
*/
    public int getChildInfoRowCount() {
        String countQuery = "SELECT  * FROM " + DBTables.MoreInfo.TABLE_NAME;
        Cursor cursor = database.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

        public boolean deleteChildInfo(Child child, String title, String details)
    {
        String[] selectionArgs = {child.getfirstName(), child.getLastName(), title, details};
        return  database.delete(DBTables.MoreInfo.TABLE_NAME, DBTables.MoreInfo.CHILD_FIRST_NAME + " = ? AND " + DBTables.MoreInfo.CHILD_LAST_NAME + " = ? AND " + DBTables.MoreInfo.INFO_TITLE + " = ? AND " + DBTables.MoreInfo.INFO_DETAILS + " = ?", selectionArgs) > 0;
    }

    public long updateChildInfo(MoreInformationModel info)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBTables.MoreInfo.INFO_TITLE, info.getInfo_title());
        contentValues.put(DBTables.MoreInfo.INFO_DETAILS, info.getInfo_details());
        contentValues.put(DBTables.MoreInfo.UPDATED_AT, getDateTime());
        return database.update(DBTables.MoreInfo.TABLE_NAME, contentValues, DBTables.MoreInfo.INFO_ID + "= '" + info.getInfo_id() + "' AND " + DBTables.MoreInfo.CHILD_FIRST_NAME + " = '" + info.getChild().getfirstName() + "' AND " + DBTables.MoreInfo.CHILD_LAST_NAME + " = '" + info.getChild().getLastName() + "'",null);
    }

    public boolean deleteChildInfo(Child child)
    {
        String[] selectionArgs = {child.getfirstName(), child.getLastName()};
        return  database.delete(DBTables.MoreInfo.TABLE_NAME, DBTables.MoreInfo.CHILD_FIRST_NAME + " = ? AND " + DBTables.MoreInfo.CHILD_LAST_NAME + " = ?", selectionArgs) > 0;
    }

    private MoreInformationModel cursorToInfo(Cursor cursor) {
        MoreInformationModel existingInfo = new MoreInformationModel();
        String firstName = cursor.getString(cursor.getColumnIndex(DBTables.MoreInfo.CHILD_FIRST_NAME));
        String lastName = cursor.getString(cursor.getColumnIndex(DBTables.MoreInfo.CHILD_LAST_NAME));
        existingInfo.setChildname(firstName, lastName);
        existingInfo.setInfo_id(cursor.getLong(cursor.getColumnIndex(DBTables.MoreInfo.INFO_ID)));
        existingInfo.setInfo_details(cursor.getString(cursor.getColumnIndex(DBTables.MoreInfo.INFO_DETAILS)));
        existingInfo.setInfo_title(cursor.getString(cursor.getColumnIndex(DBTables.MoreInfo.INFO_TITLE)));
        existingInfo.setUsername(cursor.getString(cursor.getColumnIndex(DBTables.MoreInfo.USERNAME)));
        existingInfo.getChild().setChild_id(cursor.getLong(cursor.getColumnIndex(DBTables.MoreInfo.CHILD_ID)));

        // here we convert int to boolean
        //existingUser.setIsreported(cursor.getInt(cursor.getColumnIndex(DBtables.TextReport.COLUMN_ISREPOETED)) > 0);
        return existingInfo;
    }


    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
