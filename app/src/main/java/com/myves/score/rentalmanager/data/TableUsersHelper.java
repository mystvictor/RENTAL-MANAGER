package com.myves.score.rentalmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.myves.score.rentalmanager.library.ApplicationContract;
import com.myves.score.rentalmanager.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by myves.stvictor on 2015-07-15.
 */
public class TableUsersHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public TableUsersHelper(Context context){
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    private User setUser(Cursor cursor){
        User user = new User();

        if(cursor != null && cursor.moveToFirst()){
            user.setId(cursor.getInt(cursor.getColumnIndex(DatabaseContract.KEY_ID)));
            user.setFullName(cursor.getString(cursor.getColumnIndex(DatabaseContract.KEY_USERS_FULL_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseContract.KEY_USERS_EMAIL)));
            user.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseContract.KEY_USERS_PHONE)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(DatabaseContract.KEY_USERS_PASSWORD)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseContract.KEY_USERS_PASSWORD)));

            if(cursor.getInt(cursor.getColumnIndex(DatabaseContract.KEY_USERS_PROFILE)) == 1){
                user.setProfile(true);
            }else{
                user.setProfile(false);
            }

            String date = cursor.getString(cursor.getColumnIndex(DatabaseContract.KEY_USERS_EXPIRATION_DATE));
            user.setExpirationDate(ApplicationContract.convertStringToDate(date));
        }

        return user;
    }

    /**
     * This function allows to insert a new row into the USERS TABLE.
     * It takes a user as parameter and returns the ID of the inserted User
     * @param user
     * @return long id
     */
    public long addUser(User user){
        long id = 0;

        try {
            databaseHelper.open(database);

            ContentValues values = new ContentValues();
            values.put(DatabaseContract.KEY_USERS_FULL_NAME, user.getFullName());
            values.put(DatabaseContract.KEY_USERS_EMAIL, user.getEmail());
            values.put(DatabaseContract.KEY_USERS_PHONE, user.getPhone());
            values.put(DatabaseContract.KEY_USERS_USERNAME, user.getUsername());
            values.put(DatabaseContract.KEY_USERS_PASSWORD, user.getPassword());
            values.put(DatabaseContract.KEY_USERS_PROFILE, (user.getProfile()) ? 1 : 0);
            values.put(DatabaseContract.KEY_USERS_EXPIRATION_DATE, ApplicationContract
                    .convertDateToString(user.getExpirationDate()));

            /*values.put(DatabaseContract.KEY_USERS_EXPIRATION_DATE, ApplicationContract.SDF_DATE_AND_TIME.format
                    (user.getExpirationDate().getTime()));*/

            id = database.insert(DatabaseContract.TABLE_USERS, null, values);

        }catch (SQLException e){
            Log.e(DatabaseHelper.LOG, e.getMessage());
        }

        return id;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();

        try {
            Log.e(DatabaseHelper.LOG, DatabaseContract.QUERY_SELECT_FROM);

            databaseHelper.open(database);
            Cursor cursor = database.rawQuery(DatabaseContract.QUERY_SELECT_FROM, null);

            if(cursor != null && cursor.moveToFirst()){
                do{
                    User user = new User();
                    setUser(cursor);
                    users.add(user);
                }while (cursor.moveToNext());
            }

        }catch(SQLException e){
            Log.e(DatabaseHelper.LOG, e.getMessage());
        }

        return users;
    }
}
