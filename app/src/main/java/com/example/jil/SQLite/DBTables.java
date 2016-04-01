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
        public static final String ROLE = "role";
        public static final String PHONE = "phoneNumber";
        public static final String PASSWORD = "password";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
        //public static final String PRIMARY_KEY = "PRIMARY KEY (" + USER_ID + ")";
        public static final String[] ALL_COLUMNS =
                {USER_ID, USERNAME, PASSWORD, EMAIL_ADDRESS, PHONE, ROLE, CREATED_AT, UPDATED_AT};
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
        public static final String USERNAME = "added_by_username",
                                    HEIGHT = "height",
                                    WEIGHT = "weight",
                                    ADDRESS_LOCATION = "address_location",
        IMAGE_PATH ="image_path",
                ALLERGIES = "allergies",
                VACCINATION_DUE = "vaccination_due",
                VACCINATION_TAKEN = "vaccination_taken",
                PARENT_NAMES = "parent_names";
        //public static final String USERNAME = "username";
        //public static final String EMAIL_ADDRESS = "email";
        //public static final String PHONE = "phoneNumber";
        //public static final String PASSWORD = "password";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
        //public static final String PRIMARY_KEY = "PRIMARY KEY (" + USER_ID + ")";
        public static final String[] ALL_COLUMNS =
                {CHILD_ID, FIRST_NAME, LAST_NAME, GENDER, IMAGE_PATH, DOB,
                        HEIGHT, WEIGHT, ADDRESS_LOCATION, ALLERGIES,
                        VACCINATION_TAKEN,
                        VACCINATION_DUE,PARENT_NAMES,MOREINFO_COUNT, USER_ID, USERNAME, CREATED_AT, UPDATED_AT};
    }

    public static abstract class MoreInfo implements BaseColumns {
        public static final String TABLE_NAME = "ChildInfoDB";
        public static final String INFO_ID = "child_info_id";
        public static final String INFO_TITLE = "child_info_title";
        public static final String INFO_DETAILS = "child_info_details";
        public static final String CHILD_ID = "child_id";
        public static final String CHILD_FIRST_NAME = "child_first_name";
        public static final String CHILD_LAST_NAME = "child_last_name";
        public static final String USERNAME = "added_by_username";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
        public static final String[] ALL_COLUMNS =
                {INFO_ID, INFO_TITLE, INFO_DETAILS, CHILD_ID, CHILD_FIRST_NAME, CHILD_LAST_NAME, USERNAME, CREATED_AT, UPDATED_AT};
    }

    public static abstract class SchedulesAlarm implements BaseColumns {
        public static final String TABLE_NAME = "SchedulesDB";
        public static final String SCHEDULES_ID = "schedule_id";
        public static final String SCHEDULE_TITLE = "schedule_title";
        public static final String SCHEDULE_NOTE = "schedule_note";
        public static final String USER_ID = "added_by_user_id";
        public static final String USERNAME = "added_by_username";
        public static final String CHILD_ID = "child_id";
        public static final String CHILD_FIRST_NAME = "child_first_name";
        public static final String CHILD_LAST_NAME = "child_last_name";
        public static final String DATEOFSCHEDULE = "schedule_date";
        public static final String TIMEOFSCHEDULE = "schedule_time";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
        public static final String[] ALL_COLUMNS =
                {SCHEDULES_ID, SCHEDULE_TITLE, SCHEDULE_NOTE, USER_ID, USERNAME, CHILD_ID, CHILD_FIRST_NAME, CHILD_LAST_NAME, DATEOFSCHEDULE, TIMEOFSCHEDULE, CREATED_AT, UPDATED_AT};
    }
    public static abstract class MoreinfoMini implements BaseColumns {
        public static final String TABLE_NAME = "infominiDB";
        public static final String infoTitle = "title_id1";
        public static final String infoDetails = "infoDetails1";

        public static final String[] ALL_COLUMNS =
                {infoTitle, infoDetails};
    }

    public static abstract class Vaccinations implements BaseColumns {
        public static final String TABLE_NAME = "vaccinationDB";
        public static final String vaccine_ID = "_ID";
        public static final String vaccination1 = "vacc_1",
                vaccination2 = "vacc_2",
                vaccination3 = "vacc_3",
                vaccination4 = "vacc_4",
                vaccination5 = "vacc_5",
                vaccination6 = "vacc_6",
                vaccination7 = "vacc_7",
                vaccination8 = "vacc_8",
                vaccination9 = "vacc_9",
                vaccination10 = "vacc_10";
        public static final String childFirstName = "child_first_name";
        public static final String childLastName = "child_last_name",
        BYUSER = "added_by_user";

        public static final String DUEDATE = "due_date";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";

        public static final String[] ALL_COLUMNS =
                {vaccine_ID,vaccination1, vaccination2, vaccination3,vaccination4,
                        vaccination5, vaccination6, vaccination7, vaccination8,
                        vaccination9, vaccination10, childFirstName, childLastName,DUEDATE, CREATED_AT,UPDATED_AT};
    }
}
