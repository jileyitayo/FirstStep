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

    public static abstract class Children implements BaseColumns {
        public static final String TABLE_NAME = "ChildDB";
        public static final String CHILD_ID = "child_id";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String GENDER = "gender";
        public static final String DOB = "date_of_birth";
        public static final String MOREINFO_COUNT = "more_info_Count";
        public static final String USER_ID = "added_by_user_id";
        public static final String USERNAME = "added_by_username";
        //public static final String USERNAME = "username";
        //public static final String EMAIL_ADDRESS = "email";
        //public static final String PHONE = "phoneNumber";
        //public static final String PASSWORD = "password";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
        //public static final String PRIMARY_KEY = "PRIMARY KEY (" + USER_ID + ")";
        public static final String[] ALL_COLUMNS =
                {CHILD_ID, FIRST_NAME, LAST_NAME, GENDER, DOB,MOREINFO_COUNT, USER_ID, USERNAME, CREATED_AT, UPDATED_AT};
    }

    public static abstract class MoreInfo implements BaseColumns {
        public static final String TABLE_NAME = "ChildInfoDB";
        public static final String INFO_ID = "child_info_id";
        public static final String INFO_TITLE = "child_info_title";
        public static final String INFO_DETAILS = "child_info_details";
        public static final String CHILD_ID = "child_id";
        public static final String USER_ID = "added_by_user_id";
        public static final String USERNAME = "added_by_username";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
        public static final String[] ALL_COLUMNS =
                {INFO_ID, INFO_TITLE, INFO_DETAILS, CHILD_ID, USER_ID, USERNAME, CREATED_AT, UPDATED_AT};
    }

}
