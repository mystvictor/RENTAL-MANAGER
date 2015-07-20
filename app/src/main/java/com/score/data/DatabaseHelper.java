package com.score.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.score.models.Employee;
import com.score.models.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by myves.stvictor on 2015-06-30.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    //LOGCAT TAG
    private static final String LOG = "DatabaseHelper";

    //DB VERSION
    private static final int DATABASE_VERSION = 1;

    //DB NAME
    private static final String DATABASE_NAME = "applicationdb";

    // DB TABLES
    private static final String TABLE_EMPLOYEES = "tbl_employees";
    private static final String TABLE_VEHICLES = "tbl_vehicles";
    private static final String TABLE_CUSTOMERS = "tbl_customers";
    private static final String TABLE_RENTALS = "tbl_rentals";
    private static final String TABLE_INVOICES = "tbl_invoices";

    // COMMON TABLES COLUMN NAMES
    private static final String KEY_ID = "id";

    // TABLE EMPLOYEES COLUMN NAME
    private static final String KEY_EMPLOYEE_FULL_NAME = "full_name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMPLOYEE_EMAIL = "email";
    private static final String KEY_EMPLOYEE_PHONE = "phone";
    private static final String KEY_IS_ADMIN = "admin";
    private static final String KEY_ACCOUNT_EXPIRATION_DATE = "expiration_date";

    // CREATE TABLE EMPLOYEES
    private static final String CREATE_TABLE_EMPLOYEES = "CREATE TABLE "
            + TABLE_EMPLOYEES
            + "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_EMPLOYEE_FULL_NAME + "TEXT NOT NULL,"
            + KEY_USERNAME + "TEXT UNIQUE NOT NULL,"
            + KEY_PASSWORD + "TEXT NOT NULL,"
            + KEY_EMPLOYEE_EMAIL + "TEXT UNIQUE NOT NULL,"
            + KEY_EMPLOYEE_PHONE + "TEXT NOT NULL,"
            + KEY_IS_ADMIN + "INTEGER NOT NULL,"
            + KEY_ACCOUNT_EXPIRATION_DATE + "DATETIME NOT NULL" + ")";

    // TABLE VEHICLES COLUMN NAMES
    private static final String KEY_MAKE = "make";
    private static final String KEY_MODEL = "model";
    private static final String KEY_PLATE = "plate";
    private static final String KEY_RATE = "rate";
    private static final String KEY_STATUS = "status";
    private static final String KEY_OWNER_NAME = "name";
    private static final String KEY_OWNER_PHONE = "phone";
    private static final String KEY_OWNER_EMAIL = "email";

    // CREATE TABLE VEHICLES
    private static final String CREATE_TABLE_VEHICLES = "CREATE TABLE "
            + TABLE_VEHICLES
            + "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_MAKE + "TEXT NOT NULL,"
            + KEY_MODEL + "TEXT NOT NULL,"
            + KEY_PLATE + "TEXT UNIQUE NOT NULL,"
            + KEY_RATE + "REAL NOT NULL,"
            + KEY_STATUS + "INTEGER NOT NULL,"
            + KEY_OWNER_NAME + "TEXT,"
            + KEY_OWNER_PHONE + "TEXT,"
            + KEY_OWNER_EMAIL + "TEXT" + ")";

    // TABLE CUSTOMERS COLUMN NAMES
    private static final String KEY_CUSTOMER_FULL_NAME = "full_name";
    private static final String KEY_STREET_NUMBER = "street_number";
    private static final String KEY_STREET_ADDRESS = "street_address";
    private static final String KEY_CITY = "city";
    private static final String KEY_STATE = "state";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_POSTAL_CODE = "postal_code";
    private static final String KEY_CUSTOMER_CELL_PHONE = "phone";
    private static final String KEY_CUSTOMER_EMAIL = "email";
    private static final String KEY_DRIVER_LICENSE = "driver_license";

    // CREATE TABLE CUSTOMERS
    private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE "
            + TABLE_CUSTOMERS
            + "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_CUSTOMER_FULL_NAME + "TEXT NOT NULL,"
            + KEY_STREET_NUMBER + "INTEGER NOT NULL,"
            + KEY_STREET_ADDRESS + "TEXT NOT NULL,"
            + KEY_CITY + "TEXT NOT NULL,"
            + KEY_STATE + "TEXT NOT NULL,"
            + KEY_POSTAL_CODE + "TEXT NOT NULL,"
            + KEY_COUNTRY + "TEXT NOT NULL,"
            + KEY_CUSTOMER_CELL_PHONE + "TEXT NOT NULL,"
            + KEY_CUSTOMER_EMAIL + "TEXT NOT NULL,"
            + KEY_DRIVER_LICENSE + "TEXT UNIQUE NOT NULL" + ")";

    // TABLE RENTALS COLUMN NAMES
    private static final String KEY_EMPLOYEE_ID = "employee_id";
    private static final String KEY_CUSTOMER_ID = "customer_id";
    private static final String KEY_VEHICLE_ID = "vehicle_id";
    private static final String KEY_START_DATE = "start_date";
    private static final String KEY_RETURN_DATE = "return_date";
    private static final String KEY_TOTAL = "total";

    // CREATE TABLE RENTALS
    private static final String CREATE_TABLE_RENTALS = "CREATE TABLE "
            + TABLE_RENTALS
            + "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_EMPLOYEE_ID + "INTEGER NOT NULL,"
            + KEY_VEHICLE_ID + "INTEGER NOT NULL,"
            + KEY_CUSTOMER_ID + "INTEGER NOT NULL,"
            + KEY_START_DATE + "DATETIME NOT NULL,"
            + KEY_RETURN_DATE + "DATETIME NOT NULL,"
            + KEY_TOTAL + "REAL NOT NULL" + ")";

    // TABLE INVOICES COLUMN NAMES
    private static final String KEY_INVOICE_DATE = "date";
    private static final String KEY_RENTAL_ID = "rental_id";
    private static final String KEY_RENTAL_EMPLOYEE_ID = "employee_id";
    private static final String KEY_RENTAL_CUSTOMER_ID = "customer_id";
    private static final String KEY_INVOICE_TOTAL = "total";
    private static final String KEY_PAID_CASH = "cash";
    private static final String KEY_PAID_CREDIT = "credit";
    private static final String KEY_INVOICE_BALANCE = "balance";

    //CREATE TABLE INVOICES
    private static final String CREATE_TABLE_INVOICES = "CREATE TABLE "
            + TABLE_INVOICES
            + "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_INVOICE_DATE + "DATETIME NOT NULL,"
            + KEY_RENTAL_ID + "INTEGER NOT NULL,"
            + KEY_RENTAL_EMPLOYEE_ID + "INTEGER NOT NULL,"
            + KEY_RENTAL_CUSTOMER_ID + "INTEGER NOT NULL,"
            + KEY_INVOICE_TOTAL + "REAL NOT NULL,"
            + KEY_PAID_CASH + "REAL NOT NULL,"
            + KEY_PAID_CREDIT + "REAL NOT NULL,"
            + KEY_INVOICE_BALANCE + "REAL NOT NULL" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_EMPLOYEES);
        db.execSQL(CREATE_TABLE_VEHICLES);
        db.execSQL(CREATE_TABLE_CUSTOMERS);
        db.execSQL(CREATE_TABLE_RENTALS);
        db.execSQL(CREATE_TABLE_INVOICES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICLES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RENTALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVOICES);

        onCreate(db);
    }

    public void open() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        if(!db.isOpen() ){
            db.isOpen();
        }
    }

    public void close() {
        SQLiteDatabase db = this.getReadableDatabase();

        if(db != null && db.isOpen()){
            db.close();
        }
    }

    // EMPLOYEE CRUD OPERATION

    /**
     * This function allows to insert an EMPLOYEE into the TABLE_EMPLOYEES.
     * It takes an Employee as parameter an return the ID of the inserted EMPLOYEE.
     * It returns an EMPLOYEE.
     * @param employee
     * @return long id
     */
    public long addEmployee(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMPLOYEE_FULL_NAME, employee.getFullName());
        values.put(KEY_USERNAME, employee.getUsername());
        values.put(KEY_PASSWORD, employee.getPassword());
        values.put(KEY_EMPLOYEE_PHONE, employee.getPhone());
        values.put(KEY_EMPLOYEE_EMAIL, employee.getEmail());
        values.put(KEY_IS_ADMIN, employee.getProfile());
        values.put(KEY_ACCOUNT_EXPIRATION_DATE, employee.getAccountExpirationDateToString());

        long employee_id = db.insert(TABLE_EMPLOYEES, null, values);

        db.close();

        return employee_id;
    }

    /**
     * This function allows you search an EMPLOYEE BY his ID.
     * It takes a long as parameter and returns an EMPLOYEE
     * @param id
     * @return Employee
     */
    public Employee getEmployeeByID(long id){
        Employee employee = new Employee();
        String accountExpirationDate = null;
        String query = "SELECT * FROM "
                + TABLE_EMPLOYEES
                + " WHERE " + KEY_ID + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Log.e(LOG, query);

        if(cursor != null && cursor.moveToFirst()){
            employee.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            employee.setFullName(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            employee.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
            employee.setPhone(cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_PHONE)));
            employee.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_EMAIL)));
            employee.setProfile(cursor.getInt(cursor.getColumnIndex(KEY_IS_ADMIN)));
            accountExpirationDate = cursor.getString(cursor.getColumnIndex
                    (KEY_ACCOUNT_EXPIRATION_DATE));
            employee.setAccountExpirationDate(employee.getAccountExpirationStringToDate
                    (accountExpirationDate));
        }

        assert cursor != null;
        cursor.close();

        return employee;
    }

    /**
     * This function allows you search an EMPLOYEE BY his USERNAME.
     * It takes a long as parameter and returns an EMPLOYEE
     * @param username
     * @return Employee
     */
    public Employee getEmployeeByUsername(String username){
        Employee employee = new Employee();
        String accountExpirationDate = null;
        String query = "SELECT * FROM "
                + TABLE_EMPLOYEES
                + " WHERE " + KEY_USERNAME + " = " + username;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Log.e(LOG, query);

        if(cursor != null && cursor.moveToFirst()){
            employee.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            employee.setFullName(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            employee.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
            employee.setPhone(cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_PHONE)));
            employee.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_EMAIL)));
            employee.setProfile(cursor.getInt(cursor.getColumnIndex(KEY_IS_ADMIN)));
            accountExpirationDate = cursor.getString(cursor.getColumnIndex
                    (KEY_ACCOUNT_EXPIRATION_DATE));
            employee.setAccountExpirationDate(employee.getAccountExpirationStringToDate
                    (accountExpirationDate));
        }

        assert cursor != null;
        cursor.close();

        return employee;
    }

    /**
     * This function allows to search an EMPLOYEE either by his ID or his USERNAME.
     * It takes 2 parameters. The 1st one indicates the KEY VALUE and the 2nd the searchable value.
     * @param searchBy
     * @param variable
     * @return Employee employee
     */
    public Employee getEmployee(String searchBy, String variable){
        SQLiteDatabase db = this.getReadableDatabase();
        String querySearchByID = "SELECT * FROM "
                + TABLE_EMPLOYEES
                + " WHERE " + KEY_ID + " = " + variable;
        String querySearchByUsername = "SELECT * FROM "
                + TABLE_EMPLOYEES
                + " WHERE " + KEY_USERNAME + " = " + variable;
        Cursor cursor = null;

        Employee employee = null;
        String accountExpirationDate = null;

        switch (searchBy){
            case("id"):
                Log.e(LOG, querySearchByID);
                cursor = db.rawQuery(querySearchByID, null);

                if(cursor != null && cursor.moveToFirst()){
                    employee.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                    employee.setFullName(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
                    employee.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                    employee.setPhone(cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_PHONE)));
                    employee.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_EMAIL)));
                    employee.setProfile(cursor.getInt(cursor.getColumnIndex(KEY_IS_ADMIN)));
                    accountExpirationDate = cursor.getString(cursor.getColumnIndex
                            (KEY_ACCOUNT_EXPIRATION_DATE));
                    employee.setAccountExpirationDate(employee.getAccountExpirationStringToDate
                            (accountExpirationDate));

                }

                break;

            case("username"):
                Log.e(LOG, querySearchByUsername);
                cursor = db.rawQuery(querySearchByUsername, null);

                if(cursor != null && cursor.moveToFirst()){
                    employee.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                    employee.setFullName(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
                    employee.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                    employee.setPhone(cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_PHONE)));
                    employee.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_EMAIL)));
                    employee.setProfile(cursor.getInt(cursor.getColumnIndex(KEY_IS_ADMIN)));
                    accountExpirationDate = cursor.getString(cursor.getColumnIndex
                            (KEY_ACCOUNT_EXPIRATION_DATE));
                    employee.setAccountExpirationDate(employee.getAccountExpirationStringToDate
                            (accountExpirationDate));

                }
                break;
        }

        assert cursor != null;
        cursor.close();
        return employee;
    }

    /**
     * This function return a list of all EMPLOYEES from the TABLE_EMPLOYEES
     * @return List<Employee>employees</Employee>
     */
    public List<Employee> getAllEmployees(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_EMPLOYEES;
        Cursor cursor = db.rawQuery(query, null);

        List<Employee> employees = new ArrayList<Employee>();
        String accountExpirationDate = null;

        Log.e(LOG, query);

        if(cursor != null && cursor.moveToFirst()){
            do{
                Employee employee = new Employee();
                employee.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                employee.setFullName(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
                employee.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                employee.setPhone(cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_PHONE)));
                employee.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_EMAIL)));
                employee.setProfile(cursor.getInt(cursor.getColumnIndex(KEY_IS_ADMIN)));
                accountExpirationDate = cursor.getString(cursor.getColumnIndex
                        (KEY_ACCOUNT_EXPIRATION_DATE));
                employee.setAccountExpirationDate(employee.getAccountExpirationStringToDate
                        (accountExpirationDate));

                employees.add(employee);
            }while (cursor.moveToNext());
        }

        assert cursor != null;
        cursor.close();

        return employees;
    }

    /**
     * This function allows to update the values of an existing EMPLOYEE.
     * It takes an Employee as parameter an return the ID of the updated Employee.
     * @param employee
     * @return long ID
     */
    public long updateEmployee(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMPLOYEE_FULL_NAME, employee.getFullName());
        values.put(KEY_USERNAME, employee.getUsername());
        values.put(KEY_PASSWORD, employee.getPassword());
        values.put(KEY_EMPLOYEE_PHONE, employee.getPhone());
        values.put(KEY_EMPLOYEE_EMAIL, employee.getEmail());
        values.put(KEY_IS_ADMIN, employee.getProfile());
        values.put(KEY_ACCOUNT_EXPIRATION_DATE, employee.getAccountExpirationDateToString());

        return db.update(TABLE_EMPLOYEES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(employee.getID())});
    }

    /**
     * This function allows to verify if an EMPLOYEE with a given USERNAME already exists in
     * TABLE_EMPLOYEES.
     * It takes a String as parameter and returns TRUE if record founded.
     * @param username
     * @return boolean
     */
    public boolean doesEmployeeUsernameExist(String username){
        boolean exists = false;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "
                + TABLE_EMPLOYEES
                + " WHERE " + KEY_USERNAME + " = " + username;
        Cursor cursor = db.rawQuery(query, null);

        Log.e(LOG, query);

        if(cursor != null && cursor.moveToFirst()){
            exists = true;
        }

        assert cursor != null;
        cursor.close();

        return exists;
    }

    /**
     * This function allows to verify if an EMPLOYEE with a given EMAIL already exists in
     * TABLE_EMPLOYEES.
     * It takes a String as parameter and returns TRUE if record founded.
     * @param email
     * @return boolean
     */
    public boolean doesEmployeeEmailExist(String email){
        boolean exists = false;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "
                + TABLE_EMPLOYEES
                + " WHERE " + KEY_USERNAME + " = " + email;
        Cursor cursor = db.rawQuery(query, null);

        Log.e(LOG, query);

        if(cursor != null && cursor.moveToFirst()){
            exists = true;
        }

        assert cursor != null;
        cursor.close();

        return exists;
    }

    /**
     * This function allows to verify the profile of a selected EMPLOYEE.
     * It takes a long as parameter and return TRUE if the employee has an admin account.
     * @param id
     * @return boolean
     */
    public boolean isEmployeeAdmin(long id){
        boolean isAdmin = false;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "
                + TABLE_EMPLOYEES
                + " WHERE " + KEY_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);

        Log.e(LOG, query);

        if(cursor != null && cursor.moveToFirst()){
            int profile = cursor.getInt(cursor.getColumnIndex(KEY_IS_ADMIN));

            if(profile == 1){
                isAdmin = true;
            }
        }

        assert cursor != null;
        cursor.close();

        return isAdmin;
    }

    /*public Employee getEmployeeByID(long id){
        Employee employee = new Employee();
        String query = "SELECT * FROM " + TABLE_EMPLOYEES + " WHERE " + KEY_ID + " = " + id;

        Log.e(LOG, query);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null && cursor.moveToFirst()){
            employee.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            employee.setFullName(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            employee.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
            employee.setPhone(cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_PHONE)));
            employee.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_EMAIL)));
            employee.setProfile(cursor.getInt(cursor.getColumnIndex(KEY_IS_ADMIN)));
            //employee.setAccountExpirationDate(cursor.getString());
        }

        return employee;
    }*/


    // VEHICLE CRUD OPERATION

    public long addVehicle (Vehicle vehicle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MAKE, vehicle.getMake());
        values.put(KEY_MODEL, vehicle.getModel());
        values.put(KEY_PLATE, vehicle.getPlate());
        values.put(KEY_RATE, vehicle.getRate());
        values.put(KEY_STATUS, vehicle.getStatus());
        values.put(KEY_OWNER_NAME, vehicle.getOwnerName());
        values.put(KEY_OWNER_PHONE, vehicle.getOwnerPhone());
        values.put(KEY_OWNER_EMAIL, vehicle.getOwnerEmail());

        long vehicle_id = db.insert(TABLE_VEHICLES, null, values);

        return vehicle_id;
    }

    public Vehicle getVehicleByID(long id){
        Vehicle vehicle = new Vehicle();
        String query = "SELECT * FROM " + TABLE_VEHICLES + " WHERE " + KEY_ID + " = " + id;

        Log.e(LOG, query);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null && cursor.moveToFirst()){
            vehicle.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            vehicle.setMake(cursor.getString(cursor.getColumnIndex(KEY_MAKE)));
            vehicle.setModel(cursor.getString(cursor.getColumnIndex(KEY_MODEL)));
            vehicle.setPlate(cursor.getString(cursor.getColumnIndex(KEY_PLATE)));
            vehicle.setRate(cursor.getDouble(cursor.getColumnIndex(KEY_RATE)));
            vehicle.setStatus(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
            vehicle.setOwnerName(cursor.getString(cursor.getColumnIndex(KEY_OWNER_NAME)));
            vehicle.setOwnerPhone(cursor.getString(cursor.getColumnIndex(KEY_OWNER_PHONE)));
            vehicle.setOwnerEmail(cursor.getString(cursor.getColumnIndex(KEY_OWNER_EMAIL)));
        }

        return vehicle;
    }

    public Vehicle getVehicleByPlate(String plate){
        Vehicle vehicle = new Vehicle();
        String query = "SELECT * FROM " + TABLE_VEHICLES + " WHERE " + KEY_PLATE + " = " + plate;

        Log.e(LOG, query);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null && cursor.moveToFirst()){
            vehicle.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            vehicle.setMake(cursor.getString(cursor.getColumnIndex(KEY_MAKE)));
            vehicle.setModel(cursor.getString(cursor.getColumnIndex(KEY_MODEL)));
            vehicle.setPlate(cursor.getString(cursor.getColumnIndex(KEY_PLATE)));
            vehicle.setRate(cursor.getDouble(cursor.getColumnIndex(KEY_RATE)));
            vehicle.setStatus(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
            vehicle.setOwnerName(cursor.getString(cursor.getColumnIndex(KEY_OWNER_NAME)));
            vehicle.setOwnerPhone(cursor.getString(cursor.getColumnIndex(KEY_OWNER_PHONE)));
            vehicle.setOwnerEmail(cursor.getString(cursor.getColumnIndex(KEY_OWNER_EMAIL)));
        }

        return vehicle;
    }

    public List<Vehicle> getVehiclesByStatus(int status){
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        String query = "SELECT * FROM " + TABLE_VEHICLES + " WHERE " + KEY_STATUS + " = " + status;

        Log.e(LOG, query);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null && cursor.moveToFirst()){
            do{
                Vehicle v = new Vehicle();
                v.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                v.setMake(cursor.getString(cursor.getColumnIndex(KEY_MAKE)));
                v.setModel(cursor.getString(cursor.getColumnIndex(KEY_MODEL)));
                v.setPlate(cursor.getString(cursor.getColumnIndex(KEY_PLATE)));
                v.setRate(cursor.getDouble(cursor.getColumnIndex(KEY_RATE)));
                v.setStatus(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
                v.setOwnerName(cursor.getString(cursor.getColumnIndex(KEY_OWNER_NAME)));
                v.setOwnerPhone(cursor.getString(cursor.getColumnIndex(KEY_OWNER_PHONE)));
                v.setOwnerEmail(cursor.getString(cursor.getColumnIndex(KEY_OWNER_EMAIL)));

                vehicles.add(v);
            } while (cursor.moveToNext());
        }

        return vehicles;
    }

    public List<Vehicle> getVehiclesByOwner(String name){
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        String query = "SELECT * FROM " + TABLE_VEHICLES + " WHERE " + KEY_OWNER_NAME + " = " +
                name;

        Log.e(LOG, query);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null && cursor.moveToFirst()){
            do{
                Vehicle v = new Vehicle();
                v.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                v.setMake(cursor.getString(cursor.getColumnIndex(KEY_MAKE)));
                v.setModel(cursor.getString(cursor.getColumnIndex(KEY_MODEL)));
                v.setPlate(cursor.getString(cursor.getColumnIndex(KEY_PLATE)));
                v.setRate(cursor.getDouble(cursor.getColumnIndex(KEY_RATE)));
                v.setStatus(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
                v.setOwnerName(cursor.getString(cursor.getColumnIndex(KEY_OWNER_NAME)));
                v.setOwnerPhone(cursor.getString(cursor.getColumnIndex(KEY_OWNER_PHONE)));
                v.setOwnerEmail(cursor.getString(cursor.getColumnIndex(KEY_OWNER_EMAIL)));

                vehicles.add(v);
            } while (cursor.moveToNext());
        }

        return vehicles;
    }

    public List<Vehicle> getAllVehicles(){
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        String query = "SELECT * FROM " + TABLE_VEHICLES;

        Log.e(LOG, query);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Vehicle vehicle = new Vehicle();
                vehicle.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                vehicle.setMake(cursor.getString(cursor.getColumnIndex(KEY_MAKE)));
                vehicle.setModel(cursor.getString(cursor.getColumnIndex(KEY_MODEL)));
                vehicle.setPlate(cursor.getString(cursor.getColumnIndex(KEY_PLATE)));
                vehicle.setRate(cursor.getDouble(cursor.getColumnIndex(KEY_RATE)));
                vehicle.setStatus(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
                vehicle.setOwnerName(cursor.getString(cursor.getColumnIndex(KEY_OWNER_NAME)));
                vehicle.setOwnerPhone(cursor.getString(cursor.getColumnIndex(KEY_OWNER_PHONE)));
                vehicle.setOwnerEmail(cursor.getString(cursor.getColumnIndex(KEY_OWNER_EMAIL)));

                vehicles.add(vehicle);
            } while (cursor.moveToNext());
        }

        return vehicles;
    }

    public long updateVehicle(Vehicle vehicle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MAKE, vehicle.getMake());
        values.put(KEY_MODEL, vehicle.getModel());
        values.put(KEY_PLATE, vehicle.getPlate());
        values.put(KEY_RATE, vehicle.getRate());
        values.put(KEY_STATUS, vehicle.getStatus());
        values.put(KEY_OWNER_NAME, vehicle.getOwnerName());
        values.put(KEY_OWNER_PHONE, vehicle.getOwnerPhone());
        values.put(KEY_OWNER_EMAIL, vehicle.getOwnerEmail());

        return db.update(TABLE_VEHICLES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(vehicle.getId())});
    }

    public void deleteVehicle(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VEHICLES, KEY_ID + " = ? ", new String[]{String.valueOf(id)});
    }
}
