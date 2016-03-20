package com.example.jil.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jil.Users.Child;
import com.example.jil.Users.Users;
import com.example.jil.androidrecyclerviewgridview.ItemObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JIL on 19/03/16.
 */
public class DAOInfoMini {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public DAOInfoMini(Context context) {
        dbHelper = new DBHelper(context);
        // Gets the data repository in write mode
        database = dbHelper.getWritableDatabase();
    }

    //close any db object
    public void close() {
        dbHelper.close();
    }

    //Insert Users to DB
    public long Insert(ItemObject object) {
        // Gets the data repository in write mode
        // Create a new map of values, where column names are the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBTables.MoreinfoMini.infoTitle, object.getName());
        contentValues.put(DBTables.MoreinfoMini.infoDetails, object.getDescription());
        return database.insert(DBTables.MoreinfoMini.TABLE_NAME, null, contentValues);
    }

    //getChild details from db
    public List<ItemObject> getAll() {
        List<ItemObject> itmList = new ArrayList<>();
        Cursor cursor =
                database.query(DBTables.MoreinfoMini.TABLE_NAME, DBTables.MoreinfoMini.ALL_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ItemObject itm = cursorToObject(cursor);
            itmList.add(itm);
            cursor.moveToNext();
        }
        cursor.close();
        return itmList;
    }

    //Get 1 User from DB
    public ItemObject getSingle(ItemObject object) {
        ItemObject existingObj = new ItemObject();
        //arguments are

        String[] selectionArgs = {existingObj.getName(), existingObj.getDescription()};
        Cursor cursor = null;
        cursor = database.query(DBTables.MoreinfoMini.TABLE_NAME, DBTables.MoreinfoMini.ALL_COLUMNS,
                DBTables.MoreinfoMini.infoTitle + " = ? AND " + DBTables.MoreinfoMini.infoDetails + " = ?", selectionArgs, null, null, null);
        cursor.moveToFirst();
        if (cursor != null)
        {
            if(cursor.moveToFirst()) {
                existingObj = cursorToObject(cursor);
            }
        }
        return existingObj;
    }
    // Function in DBAdapter class
    public boolean delete()
    {
        //        return database.delete(DBTables.MoreinfoMini.TABLE_NAME, DBTables.MoreinfoMini.infoTitle + "=" + object.getName(), null) > 0;
        return database.delete(DBTables.MoreinfoMini.TABLE_NAME,null,null) > 0;
    }
    public boolean delete(String title, String detail)
    {
        //        return database.delete(DBTables.MoreinfoMini.TABLE_NAME, DBTables.MoreinfoMini.infoTitle + "=" + object.getName(), null) > 0;
        String[] selectionArgs = {title, detail};
        return database.delete(DBTables.MoreinfoMini.TABLE_NAME,DBTables.MoreinfoMini.infoTitle + " = ? AND " + DBTables.MoreinfoMini.infoDetails + " = ?", selectionArgs) > 0;
    }
    private ItemObject cursorToObject(Cursor cursor) {
        ItemObject existingObj = new ItemObject();
        existingObj.setName(cursor.getString(cursor.getColumnIndex(DBTables.MoreinfoMini.infoTitle)));
        existingObj.setDescription(cursor.getString(cursor.getColumnIndex(DBTables.MoreinfoMini.infoDetails)));
        return existingObj;
    }

}
