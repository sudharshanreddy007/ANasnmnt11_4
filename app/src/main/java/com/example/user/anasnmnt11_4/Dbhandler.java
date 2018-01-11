package com.example.user.anasnmnt11_4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by IIS 5 on 10-09-2017.
 */
//creating the class name as dbhanler which extends the SQLiteopen helper

public  class Dbhandler extends SQLiteOpenHelper {

    //Create a helper object to create, open, and/or manage a database.
    public Dbhandler(Context context) {
        super(context, "students1.db", null, 5);

    }
    //created oncreate methid for sqlite database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = ("CREATE TABLE TABLE_STUDENTS(COLUMN_ID TEXT,COLUMN_ID_NUM TEXT,COLUMN_FIRSTNAME TEXT,COLUMN_FIRSTNAME_VALUE TEXT,COLUMN_LASTNAME TEXT,COLUMN_LASTNAME_VALUE TEXT)");
        Log.e("Dbhandler", "Table created");
        db.execSQL(query);


    }
  /*Called when the database needs to be upgraded.
     The implementation should use this method to drop tables, add tables,
    or do anything else it needs to upgrade to the new schema version*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = ("DROP TABLE IF EXISTS TABLE_STUDENTS");
        db.execSQL(query);
        onCreate(db);

    }
    //creating addvalues methos to insert the values in the form of columns
    public boolean addvalues(Students students) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("COLUMN_ID", students.id);
        contentValues.put("COLUMN_ID_NUM",students.idnum);
        contentValues.put("COLUMN_FIRSTNAME", students.firstname);
        contentValues.put("COLUMN_FIRSTNAME_VALUE", students.firstnamevalue);
        contentValues.put("COLUMN_LASTNAME", students.lastname);
        contentValues.put("COLUMN_LASTNAME_VALUE", students.lastnamevalue);
        long row = db.insert("TABLE_STUDENTS", null, contentValues);
        Log.e("Dbhandler", "value is inserted in row num " + row);
        db.close();
        return true;
    }
    //creating an arraylist of students  and declaring the object of the arraylist TO getstring
    public ArrayList<Students> databasetoarray() {
        {

            ArrayList<Students> studentslist = new ArrayList<Students>();
            String selectQuery = "SELECT * FROM TABLE_STUDENTS";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()&&!cursor.isAfterLast()) {
                do {
                    Students students = new Students();
                    students.setid(cursor.getString(0));
                    students.setidnum(cursor.getString(1));
                    students.setfirstname(cursor.getString(2));
                    students.setfirstnamevalue(cursor.getString(3));
                    students.setlastname(cursor.getString(4));
                    students.setlastnamevalue(cursor.getString(5));
                    studentslist.add(students);
                } while (cursor.moveToNext());
            }
            db.close();
            return studentslist;
        }

    }

}