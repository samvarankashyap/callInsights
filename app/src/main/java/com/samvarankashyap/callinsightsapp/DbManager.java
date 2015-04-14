package com.samvarankashyap.callinsightsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by samvarankashyap on 4/14/2015.
 */

public class DbManager extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CallInsights";

    // Contacts table name
    private static final String TABLE_CALL_LOGS = "calllogs";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CALLER_ID = "caller_id";
    private static final String KEY_CALLER_TYPE = "caller_type";
    private static final String KEY_DATE = "date";
    private static final String KEY_CALL_DURATION = "call_duration";
    private static final String KEY_CALL_TIME = "call_time";


    public DbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
        //        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
        //        + KEY_PH_NO + " TEXT" + ")";
        //db.execSQL(CREATE_CONTACTS_TABLE);
        String CREATE_CALL_LOGS = "CREATE TABLE "+ TABLE_CALL_LOGS + "("
                +KEY_ID + " INTEGER PRIMARY KEY ,"
                +KEY_CALLER_ID+" TEXT,"
                +KEY_CALLER_TYPE+" TEXT,"
                +KEY_DATE+" TEXT,"
                +KEY_CALL_DURATION+" TEXT,"
                +KEY_CALL_TIME+" TEXT"+")";
        db.execSQL(CREATE_CALL_LOGS);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALL_LOGS);

        // Create tables again
        onCreate(db);
    }

    void addCallRecord(CallRecord callRecord) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CALLER_ID, callRecord.getCallerId()); // Contact Name
        values.put(KEY_CALLER_TYPE, callRecord.getCallType());
        values.put(KEY_DATE, callRecord.getDate());
        values.put(KEY_CALL_DURATION, callRecord.getCallDuration());
        values.put(KEY_CALL_TIME, callRecord.getCallTime());
        // Inserting Row
        db.insert(TABLE_CALL_LOGS, null, values);
        db.close(); // Closing database connection
    }

    public List<CallRecord> getAllCallRecords() {
        List<CallRecord> callRecordList = new ArrayList<CallRecord>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CALL_LOGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CallRecord cr = new CallRecord();
                cr.setId(Integer.parseInt(cursor.getString(0)));
                cr.setCallerId(cursor.getString(1));
                cr.setCallType(cursor.getString(2));
                cr.setDate(cursor.getString(3));
                cr.setCallDuration(cursor.getString(4));
                cr.setCallTime(cursor.getString(5));
                // Adding contact to list
                callRecordList.add(cr);
            } while (cursor.moveToNext());
        }

        // return contact list
        return callRecordList;
    }


}