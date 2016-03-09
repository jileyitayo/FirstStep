package com.example.jil.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JIL on 07/03/16.
 */
public class ChildDBHelper extends SQLiteOpenHelper {

    public ChildDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public static final String DB_NAME = "HEALTH";
    public static final int DB_VERSION = 1;

    //Child Table Definition
    private static final String TEXT_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";
    private static final String NUMERIC_TYPE = " NUMERIC";
    private static final String COMMA_SEP = ",";
    private static final String CREATE_CHILDDB_TABLE = "CREATE TABLE "
            + DBTables.Children.TABLE_NAME
            + " ("
            + DBTables.Children.CHILD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DBTables.Children.FIRST_NAME + TEXT_TYPE + ", "
            + DBTables.Children.LAST_NAME + TEXT_TYPE + ", "
            + DBTables.Children.GENDER + TEXT_TYPE + ", "
            + DBTables.Children.DOB + TEXT_TYPE + ", "
            + DBTables.Children.MOREINFO_COUNT + TEXT_TYPE + ", "
            + DBTables.Children.USER_ID + TEXT_TYPE + ", "
            + DBTables.Children.USERNAME + TEXT_TYPE + ", "
            + DBTables.Children.CREATED_AT + TEXT_TYPE + ", "
            + DBTables.Children.UPDATED_AT + TEXT_TYPE
            + ");";

    private static final String DELETE_CHILDDB_TABLE = "DROP TABLE IF EXISTS " + DBTables.Children.TABLE_NAME;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CHILDDB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_CHILDDB_TABLE);
        onCreate(db);
    }
}
