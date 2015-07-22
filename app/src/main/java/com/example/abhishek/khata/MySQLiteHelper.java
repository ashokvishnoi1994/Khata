package com.example.abhishek.khata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 1/2/15.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "khata.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "acc_data";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_HIS1 = "history1";
    public static final String COLUMN_HIS2 = "history2";
    public static final String COLUMN_HIS3 = "history3";
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String DATA_TYPE = " INTEGER";
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + TEXT_TYPE+COMMA_SEP+COLUMN_DATA+ " INTEGER"+COMMA_SEP+
            COLUMN_HIS1+DATA_TYPE+COMMA_SEP+COLUMN_HIS2+DATA_TYPE+COMMA_SEP+
            COLUMN_HIS3+DATA_TYPE + " )";


    public MySQLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addEntry(dataModel data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,data.getName());
        values.put(COLUMN_DATA,data.getAmount());
        values.put(COLUMN_HIS1,data.getHis1());
        values.put(COLUMN_HIS2,data.getHis2());
        values.put(COLUMN_HIS3,data.getHis3());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public dataModel getEntry(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{COLUMN_ID,COLUMN_NAME,COLUMN_DATA,COLUMN_HIS1,COLUMN_HIS2,COLUMN_HIS3},
                COLUMN_NAME+" = ?",
                new String[]{name},null,null,null,null);
        if (cursor!=null)
            cursor.moveToFirst();
        dataModel newentry = new dataModel(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Float.parseFloat(cursor.getString(2)),
                Float.parseFloat(cursor.getString(3)),Float.parseFloat(cursor.getString(4)),Float.parseFloat(cursor.getString(5)));
        return newentry;
    }

    public List<dataModel> getAllEntries() {
        List<dataModel> dataModelList = new ArrayList<dataModel>();
        String selectQuery= "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()) {
            do {
                dataModel entry = new dataModel();
                entry.setId(Integer.parseInt(cursor.getString(0)));
                entry.setName(cursor.getString(1));
                entry.setAmount(Float.parseFloat(cursor.getString(2)));
                entry.setHis1(Float.parseFloat(cursor.getString(3)));
                entry.setHis2(Float.parseFloat(cursor.getString(4)));
                entry.setHis3(Float.parseFloat(cursor.getString(5)));
                dataModelList.add(entry);
            } while(cursor.moveToNext());
        }
        return dataModelList;
    }

    public int updateEntry(dataModel entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,entry.getName());
        values.put(COLUMN_DATA,entry.getAmount());
        values.put(COLUMN_HIS1,entry.getHis1());
        values.put(COLUMN_HIS2,entry.getHis2());
        values.put(COLUMN_HIS3,entry.getHis3());
        return db.update(TABLE_NAME,values,COLUMN_ID+" = ?",new String[] {String.valueOf(entry.getId())});
    }

    public void deleteEntry(dataModel entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN_ID+" = ?",new String[]{String.valueOf(entry.getId())});
        db.close();
    }
    public int getEntryCount() {
        String countQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);

        return cursor.getCount();
    }


}
