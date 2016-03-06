package com.example.jil.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JIL on 04/03/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "HEALTH";
    public static final int DB_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";
    private static final String NUMERIC_TYPE = " NUMERIC";
    private static final String COMMA_SEP = ",";
    private static final String CREATE_HEALTHDB_TABLE = "CREATE TABLE "
            + DBTables.HealthUsers.TABLE_NAME
            + " ("
            + DBTables.HealthUsers.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DBTables.HealthUsers.USERNAME + TEXT_TYPE + ", "
            + DBTables.HealthUsers.PASSWORD + TEXT_TYPE + ", "
            + DBTables.HealthUsers.EMAIL_ADDRESS + TEXT_TYPE + ", "
            + DBTables.HealthUsers.PHONE + TEXT_TYPE + ", "
            + DBTables.HealthUsers.CREATED_AT + TEXT_TYPE + ", "
            + DBTables.HealthUsers.UPDATED_AT + TEXT_TYPE
            + ");";

    private static final String DELETE_HEALTHDB_TABLE = "DROP TABLE IF EXISTS " + DBTables.HealthUsers.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HEALTHDB_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_HEALTHDB_TABLE);
        onCreate(db);
    }
}