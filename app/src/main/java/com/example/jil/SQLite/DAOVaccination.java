package com.example.jil.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jil.Users.Child;
import com.example.jil.Users.ChildVaccination;
import com.example.jil.Users.Users;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JIL on 09/03/16.
 */
public class DAOVaccination {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    Context activityContext;
    private DAOChildApp daoChildApp;

    public DAOVaccination(Context context) {
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
    public long InsertVaccinations(ChildVaccination newVaccinations, Child child) {
        // Gets the data repository in write mode
        // Create a new map of values, where column names are the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBTables.Vaccinations.vaccination1, newVaccinations.getVaccination1());
        contentValues.put(DBTables.Vaccinations.vaccination2, newVaccinations.getVaccination2());
        contentValues.put(DBTables.Vaccinations.vaccination3, newVaccinations.getVaccination3());
        contentValues.put(DBTables.Vaccinations.vaccination4, newVaccinations.getVaccination4());
        contentValues.put(DBTables.Vaccinations.vaccination5, newVaccinations.getVaccination5());
        contentValues.put(DBTables.Vaccinations.vaccination6, newVaccinations.getVaccination6());
        contentValues.put(DBTables.Vaccinations.vaccination7, newVaccinations.getVaccination7());
        contentValues.put(DBTables.Vaccinations.vaccination8, newVaccinations.getVaccination8());
        contentValues.put(DBTables.Vaccinations.vaccination9, newVaccinations.getVaccination9());
        contentValues.put(DBTables.Vaccinations.vaccination10,newVaccinations.getVaccination10());
        contentValues.put(DBTables.Vaccinations.childFirstName, child.getfirstName());
        contentValues.put(DBTables.Vaccinations.childLastName, child.getLastName());
        //contentValues.put(DBTables.Vaccinations.DUEDATE, moreInformation.ChildInfoCount(child));
        contentValues.put(DBTables.Vaccinations.BYUSER, child.getUsername());
        contentValues.put(DBTables.Vaccinations.CREATED_AT, getDateTime());
        contentValues.put(DBTables.Vaccinations.UPDATED_AT, getDateTime());
        return database.insert(DBTables.Vaccinations.TABLE_NAME, null, contentValues);
    }

    //getChild details from db
    public List<ChildVaccination> getAllChildVaccination() {
        List<ChildVaccination> childrenVaccList = new ArrayList<>();
        Cursor cursor =
                database.query(DBTables.Vaccinations.TABLE_NAME, DBTables.Vaccinations.ALL_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ChildVaccination existingChildVacc = cursorToChildVaccination(cursor);
            childrenVaccList.add(existingChildVacc);
            cursor.moveToNext();
        }
        cursor.close();
        return childrenVaccList;
    }

    //Get 1 User from DB
    public ChildVaccination getSingleChildVacc(String fName, String LName) {
        ChildVaccination existingUserVacc = new ChildVaccination();
        //arguments are
        String[] selectionArgs = {fName, LName};
        Cursor cursor = null;
        cursor = database.query(DBTables.Vaccinations.TABLE_NAME, DBTables.Vaccinations.ALL_COLUMNS,
                DBTables.Vaccinations.childFirstName + " = ? AND " + DBTables.Vaccinations.childLastName + " = ?", selectionArgs, null, null, null);
        cursor.moveToFirst();
        if (cursor != null)
        {
            if(cursor.moveToFirst()) {
                existingUserVacc = cursorToChildVaccination(cursor);
            }
        }
        return existingUserVacc;
    }

    public boolean deleteChildVacc(Child child)
    {
        String[] selectionArgs = {child.getfirstName(), child.getLastName()};
        return  database.delete(DBTables.Vaccinations.TABLE_NAME, DBTables.Vaccinations.childFirstName + " = ? AND " + DBTables.Vaccinations.childLastName + " = ?", selectionArgs) > 0;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private ChildVaccination cursorToChildVaccination(Cursor cursor) {
        ChildVaccination existingChildVaccine = new ChildVaccination();

        existingChildVaccine.setVaccination1(cursor.getString(cursor.getColumnIndex(DBTables.Vaccinations.vaccination1)));
        existingChildVaccine.setVaccination2(cursor.getString(cursor.getColumnIndex(DBTables.Vaccinations.vaccination2)));
        existingChildVaccine.setVaccination3(cursor.getString(cursor.getColumnIndex(DBTables.Vaccinations.vaccination3)));
        existingChildVaccine.setVaccination4(cursor.getString(cursor.getColumnIndex(DBTables.Vaccinations.vaccination4)));
        existingChildVaccine.setVaccination5(cursor.getString(cursor.getColumnIndex(DBTables.Vaccinations.vaccination5)));
        existingChildVaccine.setVaccination6(cursor.getString(cursor.getColumnIndex(DBTables.Vaccinations.vaccination6)));
        existingChildVaccine.setVaccination7(cursor.getString(cursor.getColumnIndex(DBTables.Vaccinations.vaccination7)));
        existingChildVaccine.setVaccination8(cursor.getString(cursor.getColumnIndex(DBTables.Vaccinations.vaccination8)));
        existingChildVaccine.setVaccination9(cursor.getString(cursor.getColumnIndex(DBTables.Vaccinations.vaccination9)));
        existingChildVaccine.setVaccination10(cursor.getString(cursor.getColumnIndex(DBTables.Vaccinations.vaccination10)));

        // here we convert int to boolean
        //existingUser.setIsreported(cursor.getInt(cursor.getColumnIndex(DBtables.TextReport.COLUMN_ISREPOETED)) > 0);
        return existingChildVaccine;
    }
}
