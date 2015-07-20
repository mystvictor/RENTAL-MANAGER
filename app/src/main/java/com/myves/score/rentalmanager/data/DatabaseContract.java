package com.myves.score.rentalmanager.data;

import android.os.ParcelUuid;

/**
 * Created by myves.stvictor on 2015-07-15.
 */
public class DatabaseContract {
    private static final String PK = " INTEGER PRIMARY KEY AUTOINCREMENT,";
    private static final String TEXT_NOT_NULL = " TEXT NOT NULL";
    private static final String INTEGER_NOT_NULL = " INTEGER NOT NULL";
    private static final String DATETIME_NOT_NULL = " DATETIME NOT NULL";

    public static final String QUERY_SELECT_FROM = "SELECT * FROM ";

    // Table Names
    public static final String TABLE_USERS = "TBL_USERS";

    // Common column names
    public static final String KEY_ID = "id";

    // TBL_USERS column names
    public static final String KEY_USERS_FULL_NAME = "full_name";
    public static final String KEY_USERS_EMAIL = "email";
    public static final String KEY_USERS_PHONE = "phone";
    public static final String KEY_USERS_USERNAME = "username";
    public static final String KEY_USERS_PASSWORD = "password";
    public static final String KEY_USERS_PROFILE = "profile";
    public static final String KEY_USERS_EXPIRATION_DATE = "expiration_date";

    // TBL_USERS CREATE STATEMENT
    public static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USERS
            + "(" + KEY_ID + PK
            + KEY_USERS_FULL_NAME + " TEXT NOT NULL,"
            + KEY_USERS_EMAIL + TEXT_NOT_NULL
            + KEY_USERS_PHONE + TEXT_NOT_NULL
            + KEY_USERS_USERNAME + TEXT_NOT_NULL
            + KEY_USERS_PASSWORD + TEXT_NOT_NULL
            + KEY_USERS_PROFILE + INTEGER_NOT_NULL
            + KEY_USERS_EXPIRATION_DATE + DATETIME_NOT_NULL + ")";
}
