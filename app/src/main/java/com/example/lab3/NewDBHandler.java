package com.example.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    private static final String COURSES_COLUMN_INSTRUCTOR = "instructor";
    private static final String COURSES_COLUMN_DAYS = "days";
    private static final String COURSES_COLUMN_HOURS = "hours";
    private static final String COURSES_COLUMN_DESCRIPTION = "description";
    private static final String COURSES_COLUMN_STUDENT_CAPACITY = "studentcapacity";


    public NewDBHandler(Context context){ super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +"(" + USERS_COLUMN_ID + " INTEGER PRIMARY KEY," + USERS_COLUMN_USERNAME + " TEXT," + USERS_COLUMN_PASSWORD + " TEXT,"  + USERS_COLUMN_ACCOUNT_TYPE + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
        String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES +"(" + COURSES_COLUMN_ID + " INTEGER PRIMARY KEY, " + COURSES_COLUMN_CODE + " TEXT," + COURSES_COLUMN_NAME + " TEXT,"
                + COURSES_COLUMN_INSTRUCTOR + " TEXT," + COURSES_COLUMN_DAYS +" TEXT," + COURSES_COLUMN_HOURS + " TEXT," + COURSES_COLUMN_DESCRIPTION + " TEXT," + COURSES_COLUMN_STUDENT_CAPACITY + " INTEGER"
                + ")";
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
            course.setCode(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_CODE)));
            course.setName(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_NAME)));
            course.setInstructor(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_INSTRUCTOR)));
            course.setDays(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_DAYS)));
            course.setHours(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_HOURS)));
            course.setDescription(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_DESCRIPTION)));
            course.setStudentCapacity(cursor.getInt(cursor.getColumnIndex(COURSES_COLUMN_STUDENT_CAPACITY)));
        } else {
            course = null;
        }
        cursor.close();
        db.close();
        return course;
    }

    public Course findCourseByCode(String courseCode){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_COURSES+" WHERE "+COURSES_COLUMN_CODE+" = \""+courseCode+"\"";
        Cursor cursor = db.rawQuery(query, null);

        // create an object and get the result
        Course course = new Course();
        if (cursor.moveToFirst()){
            course.setCode(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_CODE)));
            course.setName(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_NAME)));
            course.setInstructor(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_INSTRUCTOR)));
            course.setDays(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_DAYS)));
            course.setHours(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_HOURS)));
            course.setDescription(cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_DESCRIPTION)));
            course.setStudentCapacity(cursor.getInt(cursor.getColumnIndex(COURSES_COLUMN_STUDENT_CAPACITY)));
        } else {
            course = null;
        }
        cursor.close();
        db.close();
        return course;
    }

    public void addCourseInfo(String courseName, String days, String hours, String description, int studentCapacity){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_COURSES+" WHERE "+COURSES_COLUMN_NAME+" = \""+courseName+"\"";
        Cursor cursor = db.rawQuery(query, null);

        String idStr;
        if (cursor.moveToFirst()){
            idStr = cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_ID));
            ContentValues values = new ContentValues();
            values.put(COURSES_COLUMN_CODE, cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_CODE)));
            values.put(COURSES_COLUMN_NAME, cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_NAME)));
            values.put(COURSES_COLUMN_INSTRUCTOR, cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_INSTRUCTOR)));
            values.put(COURSES_COLUMN_DAYS, days);
            values.put(COURSES_COLUMN_HOURS, hours);
            values.put(COURSES_COLUMN_DESCRIPTION, description);
            values.put(COURSES_COLUMN_STUDENT_CAPACITY, studentCapacity);
            db.update(TABLE_COURSES, values, COURSES_COLUMN_ID+" = ?", new String[] {idStr});
        }
        cursor.close();
        db.close();
    }

    public void removeCourseInfo(String courseName){
        addCourseInfo(courseName, new String("NA"), new String("NA"), new String("NA"), 0);
    }

    public void assignInstructor(String courseName, String instructor){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_COURSES+" WHERE "+COURSES_COLUMN_NAME+" = \""+courseName+"\"";
        Cursor cursor = db.rawQuery(query, null);

        String idStr;
        if (cursor.moveToFirst()){
            idStr = cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_ID));
            ContentValues values = new ContentValues();
            values.put(COURSES_COLUMN_CODE, cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_CODE)));
            values.put(COURSES_COLUMN_NAME, cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_NAME)));
            values.put(COURSES_COLUMN_INSTRUCTOR, instructor);
            values.put(COURSES_COLUMN_DAYS, cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_DAYS)));
            values.put(COURSES_COLUMN_HOURS, cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_HOURS)));
            values.put(COURSES_COLUMN_DESCRIPTION, cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_DESCRIPTION)));
            values.put(COURSES_COLUMN_STUDENT_CAPACITY, cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_STUDENT_CAPACITY)));
            db.update(TABLE_COURSES, values, COURSES_COLUMN_ID+" = ?", new String[] {idStr});
        }
        cursor.close();
        db.close();
    }

    public void unassignInstructor(String courseName){
        assignInstructor(courseName, new String("NA"));
    }

    // used by the admin
    public void editCourse(String prevName, String newCode, String newName){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_COURSES+" WHERE "+COURSES_COLUMN_NAME+" = \""+prevName+"\"";
        Cursor cursor = db.rawQuery(query, null);

        // edit course information
        String idStr;
        if (cursor.moveToFirst()){
            idStr = cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_ID));
            ContentValues values = new ContentValues();
            values.put(COURSES_COLUMN_CODE, newCode);
            values.put(COURSES_COLUMN_NAME, newName);
            db.update(TABLE_COURSES, values, COURSES_COLUMN_ID+" = ?", new String[] {idStr});
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
            String idStr = cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_ID));
            result = db.delete(TABLE_COURSES, COURSES_COLUMN_ID+" = "+idStr, null) > 0;
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
                        cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_INSTRUCTOR)),
                        cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_DAYS)),
                        cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_HOURS)),
                        cursor.getString(cursor.getColumnIndex(COURSES_COLUMN_DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndex(COURSES_COLUMN_STUDENT_CAPACITY))
                        ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return courses;
    }

    public void addAccount(String username, String password, String accountType){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERS_COLUMN_USERNAME, username);
        values.put(USERS_COLUMN_PASSWORD, password);
        values.put(USERS_COLUMN_ACCOUNT_TYPE, accountType);


        db.insert(TABLE_USERS, null, values);


        db.close();


    }

    public boolean findAccount(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_COLUMN_USERNAME+" = \""+username
                +"\" AND "+USERS_COLUMN_PASSWORD+" = \""+password+"\"";
        Cursor cursor = db.rawQuery(query, null);

        boolean accountExists = false;
        if (cursor.moveToFirst()){
            accountExists = true;
        }
        cursor.close();
        db.close();
        return  accountExists;
    }

    public boolean findAccountForAccountsPage(String username){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_COLUMN_USERNAME+" = \""+username
                +"\"";
        Cursor cursor = db.rawQuery(query, null);

        boolean accountExists = false;
        if (cursor.moveToFirst()){
            accountExists = true;
        }
        cursor.close();
        db.close();
        return  accountExists;
    }
    public Account findAccountUsername( String userName ){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_COLUMN_USERNAME+" = \""+userName+"\"";
        Cursor cursor = db.rawQuery(query, null);

        Account thisAccount = new Account();

        if ( cursor.moveToFirst() ){
            thisAccount.setUsername( cursor.getString( cursor.getColumnIndex( USERS_COLUMN_USERNAME ) ) );
            thisAccount.setPassword( cursor.getString( cursor.getColumnIndex( USERS_COLUMN_PASSWORD ) ) );
            thisAccount.setAccountType( cursor.getString( cursor.getColumnIndex( USERS_COLUMN_ACCOUNT_TYPE ) ) );
        }
        else {
            thisAccount = null;
        }
        cursor.close();
        db.close();
        return thisAccount;
    }
//    public boolean findAccountForAccountsPage(String username){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String query = "SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_COLUMN_USERNAME+" = \""+username
//                +"\"";
//        Cursor cursor = db.rawQuery(query, null);
//
//        boolean accountExists = false;
//        if (cursor.moveToFirst()){
//            accountExists = true;
//        }
//        cursor.close();
//        db.close();
//        return  accountExists;
//    }

    public boolean deleteAccount(String username){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_COLUMN_USERNAME+" = \""+username+"\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_USERS, USERS_COLUMN_ID+" = "+idStr, null);
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
