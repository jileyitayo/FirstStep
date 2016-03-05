package com.example.jil.SQLite;

import android.provider.BaseColumns;

/**
 * Created by JIL on 04/03/16.
 */
public class DBTables {
    // To prevent someone from accidentally instantiating the contract class, give it an empty constructor.
    public DBTables() {}

    /* Inner class that defines the table contents */
    public static abstract class HealthUsers implements BaseColumns {
        public static final String TABLE_NAME = "HealthDB";
        public static final String USER_ID = "_ID";
        //private static final String FIRST_NAME = "first_name";
        //private static final String LAST_NAME = "last_name";
        public static final String USERNAME = "username";
        public static final String EMAIL_ADDRESS = "email";
        public static final String PHONE = "phoneNumber";
        public static final String PASSWORD = "password";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
        //public static final String PRIMARY_KEY = "PRIMARY KEY (" + USER_ID + ")";
        public static final String[] ALL_COLUMNS =
                {USER_ID, USERNAME, PASSWORD, EMAIL_ADDRESS, PHONE, CREATED_AT, UPDATED_AT};
    }
}
