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

    //User Table Definition
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
            + DBTables.HealthUsers.ROLE + TEXT_TYPE + ", "
            + DBTables.HealthUsers.CREATED_AT + TEXT_TYPE + ", "
            + DBTables.HealthUsers.UPDATED_AT + TEXT_TYPE
            + ");";
    private static final String CREATE_CHILDDB_TABLE = "CREATE TABLE "
            + DBTables.Children.TABLE_NAME
            + " ("
            + DBTables.Children.CHILD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DBTables.Children.FIRST_NAME + TEXT_TYPE + ", "
            + DBTables.Children.LAST_NAME + TEXT_TYPE + ", "
            + DBTables.Children.GENDER + TEXT_TYPE + ", "
            + DBTables.Children.IMAGE_PATH + TEXT_TYPE + ", "
            + DBTables.Children.DOB + TEXT_TYPE + ", "
            + DBTables.Children.HEIGHT + TEXT_TYPE + ", "
            + DBTables.Children.WEIGHT + TEXT_TYPE + ", "
            + DBTables.Children.ADDRESS_LOCATION + TEXT_TYPE + ", "
            + DBTables.Children.ALLERGIES + TEXT_TYPE + ", "
            + DBTables.Children.VACCINATION_DUE + TEXT_TYPE + ", "
            + DBTables.Children.VACCINATION_TAKEN + TEXT_TYPE + ", "
            + DBTables.Children.MOREINFO_COUNT + TEXT_TYPE + ", "
            + DBTables.Children.PARENT_NAMES + TEXT_TYPE + ", "
            + DBTables.Children.USER_ID + TEXT_TYPE + ", "
            + DBTables.Children.USERNAME + TEXT_TYPE + ", "
            + DBTables.Children.CREATED_AT + TEXT_TYPE + ", "
            + DBTables.Children.UPDATED_AT + TEXT_TYPE
            + ");";
    private static final String CREATE_CHILD_MOREINFO_DB_TABLE = "CREATE TABLE "
            + DBTables.MoreInfo.TABLE_NAME
            + " ("
            + DBTables.MoreInfo.INFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DBTables.MoreInfo.INFO_TITLE + TEXT_TYPE + ", "
            + DBTables.MoreInfo.INFO_DETAILS + TEXT_TYPE + ", "
            + DBTables.MoreInfo.CHILD_ID + TEXT_TYPE + ", "
            + DBTables.MoreInfo.CHILD_FIRST_NAME + TEXT_TYPE + ", "
            + DBTables.MoreInfo.CHILD_LAST_NAME + TEXT_TYPE + ", "
            + DBTables.MoreInfo.USERNAME + TEXT_TYPE + ", "
            + DBTables.MoreInfo.CREATED_AT + TEXT_TYPE + ", "
            + DBTables.MoreInfo.UPDATED_AT + TEXT_TYPE
            + ");";

    private static final String CREATE_CHILD_SCHEDULE_DB_TABLE = "CREATE TABLE "
            + DBTables.SchedulesAlarm.TABLE_NAME
            + " ("
            + DBTables.SchedulesAlarm.SCHEDULES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DBTables.SchedulesAlarm.SCHEDULE_TITLE + TEXT_TYPE + ", "
            + DBTables.SchedulesAlarm.SCHEDULE_NOTE + TEXT_TYPE + ", "
            + DBTables.SchedulesAlarm.USER_ID + TEXT_TYPE + ", "
            + DBTables.SchedulesAlarm.USERNAME + TEXT_TYPE + ", "
            + DBTables.SchedulesAlarm.CHILD_ID + TEXT_TYPE + ", "
            + DBTables.SchedulesAlarm.CHILD_FIRST_NAME + TEXT_TYPE + ", "
            + DBTables.SchedulesAlarm.CHILD_LAST_NAME + TEXT_TYPE + ", "
            + DBTables.SchedulesAlarm.DATEOFSCHEDULE + TEXT_TYPE + ", "
            + DBTables.SchedulesAlarm.TIMEOFSCHEDULE + TEXT_TYPE + ", "
            + DBTables.SchedulesAlarm.CREATED_AT + TEXT_TYPE + ", "
            + DBTables.SchedulesAlarm.UPDATED_AT + TEXT_TYPE
            + ");";

    private static final String CREATE_CHILD_VACCINATION_DB_TABLE = "CREATE TABLE "
            + DBTables.Vaccinations.TABLE_NAME
            + " ("
            + DBTables.Vaccinations.vaccine_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DBTables.Vaccinations.vaccination1 + TEXT_TYPE + ", "
            + DBTables.Vaccinations.vaccination2 + TEXT_TYPE + ", "
            + DBTables.Vaccinations.vaccination3 + TEXT_TYPE + ", "
            + DBTables.Vaccinations.vaccination4 + TEXT_TYPE + ", "
            + DBTables.Vaccinations.vaccination5 + TEXT_TYPE + ", "
            + DBTables.Vaccinations.vaccination6 + TEXT_TYPE + ", "
            + DBTables.Vaccinations.vaccination7 + TEXT_TYPE + ", "
            + DBTables.Vaccinations.vaccination8 + TEXT_TYPE + ", "
            + DBTables.Vaccinations.vaccination9 + TEXT_TYPE + ", "
            + DBTables.Vaccinations.vaccination10 + TEXT_TYPE + ", "
            + DBTables.Vaccinations.childFirstName + TEXT_TYPE + ", "
            + DBTables.Vaccinations.childLastName + TEXT_TYPE + ", "
            + DBTables.Vaccinations.BYUSER + TEXT_TYPE + ", "
            + DBTables.Vaccinations.DUEDATE + TEXT_TYPE + ", "
            + DBTables.SchedulesAlarm.CREATED_AT + TEXT_TYPE + ", "
            + DBTables.SchedulesAlarm.UPDATED_AT + TEXT_TYPE
            + ");";
    private static final String CREATE_MoreinfoMini_DB_TABLE = "CREATE TABLE "
            + DBTables.MoreinfoMini.TABLE_NAME
            + " ("
            + DBTables.MoreinfoMini.infoTitle + TEXT_TYPE + ", "
            + DBTables.MoreinfoMini.infoDetails + TEXT_TYPE
            + ");";
    private static final String DELETE_CHILD_VACCINATION_TABLE = "DROP TABLE IF EXISTS " + DBTables.Vaccinations.TABLE_NAME;
    private static final String DELETE_CHILDDB_TABLE = "DROP TABLE IF EXISTS " + DBTables.Children.TABLE_NAME;
    private static final String DELETE_CHILD_MOREINFO_DB_TABLE = "DROP TABLE IF EXISTS " + DBTables.MoreInfo.TABLE_NAME;
    private static final String DELETE_HEALTHDB_TABLE = "DROP TABLE IF EXISTS " + DBTables.HealthUsers.TABLE_NAME;
    private static final String DELETE_CHILD_SCHEDULE_TABLE = "DROP TABLE IF EXISTS " + DBTables.SchedulesAlarm.TABLE_NAME;
    private static final String DELETE_MOREINFOMINI_TABLE = "DROP TABLE IF EXISTS " + DBTables.MoreinfoMini.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HEALTHDB_TABLE);
        db.execSQL(CREATE_CHILDDB_TABLE);
        db.execSQL(CREATE_CHILD_MOREINFO_DB_TABLE);
        db.execSQL(CREATE_CHILD_SCHEDULE_DB_TABLE);
        db.execSQL(CREATE_MoreinfoMini_DB_TABLE);
        db.execSQL(CREATE_CHILD_VACCINATION_DB_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_CHILDDB_TABLE);
        db.execSQL(DELETE_HEALTHDB_TABLE);
        db.execSQL(DELETE_CHILD_MOREINFO_DB_TABLE);
        db.execSQL(DELETE_CHILD_SCHEDULE_TABLE);
        db.execSQL(DELETE_MOREINFOMINI_TABLE);
        db.execSQL(DELETE_CHILD_VACCINATION_TABLE);
        onCreate(db);
    }
}