package com.example.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class NewDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "universityDB.db";
    private static final String TABLE_USERS = "users";
    private static final String USERS_COLUMN_ID = "_id";
    private static final String USERS_COLUMN_USERNAME = "username";
    private static final String USERS_COLUMN_PASSWORD = "password";
    private static final String USERS_COLUMN_ACCOUNT_TYPE = "accounttype";
    private static final String TABLE_COURSES = "courses";
    private static final String COURSES_COLUMN_ID = "_id";
    private static final String COURSES_COLUMN_CODE = "code";
    private static final String COURSES_COLUMN_NAME = "name";


    public NewDBHandler(Context context){ super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +"(" + USERS_COLUMN_ID + " INTEGER PRIMARY KEY," + USERS_COLUMN_USERNAME + " TEXT," + USERS_COLUMN_PASSWORD + " TEXT,"  + USERS_COLUMN_ACCOUNT_TYPE + "TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
        String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES +"(" + COURSES_COLUMN_ID + " INTEGER PRIMARY KEY, " + COURSES_COLUMN_CODE + " TEXT," + COURSES_COLUMN_NAME + " TEXT" + ")";
        db.execSQL(CREATE_COURSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_COURSES);
        onCreate(db);
    }

    public boolean addCourse(String courseCode, String courseName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COURSES_COLUMN_CODE, courseCode);
        values.put(COURSES_COLUMN_NAME, courseName);

        db.insert(TABLE_COURSES, null, values);
        db.close();
        return true;
    }

    public Course findCourse(String courseName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_COURSES+" WHERE "+COURSES_COLUMN_NAME+" = \""+courseName+"\"";
        Cursor cursor = db.rawQuery(query, null);

        // create an object and get the result
        Course course = new Course();
        if (cursor.moveToFirst()){
            course.setCode(cursor.getString(1));
            course.setName(cursor.getString(2));
        } else {
            course = null;
        }
        cursor.close();
        db.close();
        return course;
    }

    public void editCourse(String prevName, String newCode, String newName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_COURSES+" WHERE "+COURSES_COLUMN_NAME+" = \""+prevName+"\"";
        Cursor cursor = db.rawQuery(query, null);

        // edit course information
        String idStr;
        if (cursor.moveToFirst()){
            idStr = cursor.getString(0);
            query = "UPDATE "+TABLE_COURSES+" SET "+COURSES_COLUMN_CODE+" = \""+newCode+"\", "
                    +COURSES_COLUMN_NAME+" = \""+newName+"\" WHERE ID = "+idStr;
            db.rawQuery(query, null);
        }
        cursor.close();
        db.close();
    }

    // delete from database
    public boolean deleteCourse(String courseName){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_COURSES+" WHERE "+COURSES_COLUMN_NAME+" = \""+courseName+"\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_COURSES, COURSES_COLUMN_ID+" = "+idStr, null);
            cursor.close();
            result = true;
        }
        cursor.close();
        db.close();
        return result;
    }

    public ArrayList<Course> allCourses(){
        ArrayList<Course> courses = new ArrayList<Course>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_COURSES;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                courses.add(new Course(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_CODE)),
                        cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_NAME))));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return courses;
    }

    public boolean addAccount(String username, String password, String accountType){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERS_COLUMN_USERNAME, username);
        values.put(USERS_COLUMN_PASSWORD, password);
        values.put(USERS_COLUMN_ACCOUNT_TYPE, accountType);

        // insert into table and close
        db.insert(TABLE_USERS, null, values);
        db.close();
        return true;
    }

    public Account findAccount(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_COLUMN_USERNAME+" = \""+username
                +"\" AND "+USERS_COLUMN_PASSWORD+" = \""+password+"\"";
        Cursor cursor = db.rawQuery(query, null);

        // create an object and get the result
        Account account = new Account();
        if (cursor.moveToFirst()){
            account.setUsername(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_USERNAME)));
            account.setPassword(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_PASSWORD)));
            account.setAccountType(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_ACCOUNT_TYPE)));
            cursor.close();
        } else {
            account = null;
        }
        cursor.close();
        db.close();
        return  account;
    }

    public boolean deleteAccount(String username){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_COLUMN_USERNAME+" = \""+username+"\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_USERS, USERS_COLUMN_ID+" = "+idStr, null);
            cursor.close();
            result = true;
        }
        cursor.close();
        db.close();
        return result;
    }

    public ArrayList<Account> allAccounts(){
        ArrayList<Account> accounts = new ArrayList<Account>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                accounts.add(new Account(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_USERNAME)),
                        cursor.getString(cursor.getColumnIndex(USERS_COLUMN_PASSWORD)),
                        cursor.getString(cursor.getColumnIndex(USERS_COLUMN_ACCOUNT_TYPE))));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return accounts;
    }
}
