package com.myves.score.rentalmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myves.score.rentalmanager.library.ApplicationContract;
import com.myves.score.rentalmanager.models.User;

import java.sql.SQLException;

/**
 * Created by myves.stvictor on 2015-07-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "rental_manager.db";

    // Logcat tag
    public static final String LOG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // ADMIN USER
        User admin = new User();

        admin.setFullName("Administrator");
        admin.setEmail("myves.stvictor@gmail.com");
        admin.setPhone("5148121213");
        admin.setUsername("admin");
        admin.setPassword("041181");
        admin.setProfile(true);
        admin.setExpirationDate(ApplicationContract.convertStringToDate("31-12-2500"));

        // creating required tables
        db.execSQL(DatabaseContract.CREATE_TABLE_USERS);

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.KEY_USERS_FULL_NAME, admin.getFullName());
        values.put(DatabaseContract.KEY_USERS_EMAIL, admin.getEmail());
        values.put(DatabaseContract.KEY_USERS_PHONE, admin.getPhone());
        values.put(DatabaseContract.KEY_USERS_USERNAME, admin.getUsername());
        values.put(DatabaseContract.KEY_USERS_PASSWORD, admin.getPassword());
        values.put(DatabaseContract.KEY_USERS_PROFILE, (admin.getProfile()) ? 1 : 0);
        values.put(DatabaseContract.KEY_USERS_EXPIRATION_DATE, ApplicationContract
                .convertDateToString(admin.getExpirationDate()));

        db.insert(DatabaseContract.TABLE_USERS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_USERS);

        // create new tables
        onCreate(db);
    }

    /*public DatabaseHelper open() throws SQLException{
        return this;
    }*/

    public void open(SQLiteDatabase database) throws SQLException {
        database = this.getWritableDatabase();
    }

    public void read(SQLiteDatabase database) throws SQLException{
        database = this.getReadableDatabase();
    }

    public void close(){
        SQLiteDatabase db = this.getReadableDatabase();
        if(db != null && db.isOpen()){
            db.close();
        }
    }
}
