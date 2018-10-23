package com.example.padcc.visitorsapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by padcc on 29/09/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "VisitorDB";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TABLE_VISITORS = "visitors";

    // User Table Columns names
    private static final String KEY_VISITOR_ID = "visitor_id";
    private static final String KEY_VISITOR_FIRSTNAME = "visitor_firstname";
    private static final String KEY_VISITOR_LASTNAME = "visitor_lastname";
    private static final String KEY_PHONE = "Phone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TECHNIQUE= "technique";
    private static final String KEY_GENDER= "Gender";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_VISITOR_TABLE = "CREATE TABLE " + TABLE_VISITORS + "("
                + KEY_VISITOR_ID + " INTEGER Primary key,"
                + KEY_VISITOR_FIRSTNAME + " TEXT,"
                + KEY_VISITOR_LASTNAME + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_TECHNIQUE + " TEXT,"
                + KEY_GENDER + " TEXT"+")";
        sqLiteDatabase.execSQL(CREATE_VISITOR_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Long insertVisitor (Visitor visitor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

      //  contentValues.put(KEY_VISITOR_ID,visitor.getVisitorId());
        contentValues.put(KEY_VISITOR_FIRSTNAME, visitor.getVfirstnName());
        contentValues.put(KEY_VISITOR_LASTNAME,visitor.getVLastName());
        contentValues.put(KEY_PHONE,visitor.getVPhone());
        contentValues.put(KEY_EMAIL,visitor.getVEmail());
        contentValues.put(KEY_TECHNIQUE,visitor.getVTechnique());
        contentValues.put(KEY_GENDER,visitor.getVgender());

        Long result= db.insert(TABLE_VISITORS, null, contentValues);
        return result;
    }

    public int deleteVisitors(int visitorid){
        String query="delete from "+ TABLE_VISITORS+ " WHERE "+KEY_VISITOR_ID+"="+ visitorid;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
      //  db.execSQL("DELETE FROM " + TABLE_VISITORS+ " WHERE "+KEY_VISITOR_ID+"='"+visitor.getVisitorId()+"'");
        return 0;
    }


    public int UpdateVisitor (Visitor visitor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        contentValues.put(KEY_VISITOR_FIRSTNAME, visitor.getVfirstnName());
        contentValues.put(KEY_VISITOR_LASTNAME,visitor.getVLastName());
        contentValues.put(KEY_PHONE,visitor.getVPhone());
        contentValues.put(KEY_EMAIL,visitor.getVEmail());
        contentValues.put(KEY_TECHNIQUE,visitor.getVTechnique());
        contentValues.put(KEY_GENDER,visitor.getVgender());

        // Long result= db.update(TABLE_VISITORS, contentValues,KEY_VISITOR_ID+"="+ visitor.getVisitorId(), );

        int result=db.update(TABLE_VISITORS, contentValues,KEY_VISITOR_ID+"="+ visitor.getVisitorId(),null);

        //int result= db.update(TABLE_VISITORS,contentValues,KEY_VISITOR_ID + " = ?",new String[]{String.valueOf(visitor.getVisitorId())});
        return result;
    }


    public ArrayList<Visitor> getAllVisitors() {
        ArrayList<Visitor> visitors = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VISITORS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Visitor visitor = new Visitor();
                visitor.setVisitorId(cursor.getInt(cursor.getColumnIndex(KEY_VISITOR_ID)));
                visitor.setVfirstnName(cursor.getString(cursor.getColumnIndex(KEY_VISITOR_FIRSTNAME)));
                visitor.setVLastName(cursor.getString(cursor.getColumnIndex(KEY_VISITOR_LASTNAME)));
                visitor.setVPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
                visitor.setVEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                visitor.setVTechnique(cursor.getString(cursor.getColumnIndex(KEY_TECHNIQUE)));
                visitor.setVgender(cursor.getString(cursor.getColumnIndex(KEY_GENDER)));

                visitors.add(visitor);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        // return notes list
        return visitors;
    }
}
