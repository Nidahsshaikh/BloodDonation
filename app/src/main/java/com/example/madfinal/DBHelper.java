package com.example.madfinal;
import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends  SQLiteOpenHelper  {

    public DBHelper(Context context) {super(context, "donors.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table donor(name TEXT primary key,bloodgroup TEXT,dob TEXT,contact TEXT,address TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists donor");
    }
    public Boolean insertuserdata(String name, String bloodgroup,String dob, String contact,String address)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("bloodgroup", bloodgroup);
        contentValues.put("dob", dob);
        contentValues.put("contact", contact);
        contentValues.put("address", address);
        long result=DB.insert("donor", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateuserdata(String name,String bloodgroup,String dob, String contact,String address)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("bloodgroup", bloodgroup);
        contentValues.put("dob", dob);
        contentValues.put("contact", contact);
        contentValues.put("address", address);
        Cursor cursor = DB.rawQuery("Select * from donor where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("donor", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from donor where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("donor", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from donor", null);
        return cursor;
    }


    public Cursor getDonorsData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from donor where bloodgroup = ?", new String[]{GlobalVariable.bloodGroup});
        return cursor;
    }
}


