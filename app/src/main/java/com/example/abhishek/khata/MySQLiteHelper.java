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

    public static final String DATABASE_NAME = "khataV2.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "account_data";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_HIS1 = "history1";
    public static final String COLUMN_HIS2 = "history2";
    public static final String COLUMN_HIS3 = "history3";
    public static final String COLUMN_HIS4 = "history4";
    public static final String COLUMN_HIS5 = "history5";
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + TEXT_TYPE+COMMA_SEP+COLUMN_DATA+TEXT_TYPE+COMMA_SEP+
            COLUMN_HIS1+TEXT_TYPE+COMMA_SEP+COLUMN_HIS2+TEXT_TYPE+COMMA_SEP+
            COLUMN_HIS3+TEXT_TYPE +COMMA_SEP+COLUMN_HIS4+TEXT_TYPE+COMMA_SEP+COLUMN_HIS5+TEXT_TYPE + " )";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
            db.execSQL("ALTER TABLE TABLE_NAME ADD COLUMN COLUMN_HIS4 INTEGER DEFAULT 0");
            db.execSQL("ALTER TABLE TABLE_NAME ADD COLUMN COLUMN_HIS5 INTEGER DEFAULT 0");
    }


    public void addEntry(dataModel data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,data.getName());
        values.put(COLUMN_DATA,String.valueOf(data.getAmount().getAmount())+"+"+data.getAmount().getComment());
        values.put(COLUMN_HIS1,String.valueOf(data.getHis1().getAmount())+"+"+data.getHis1().getComment());
        values.put(COLUMN_HIS2,String.valueOf(data.getHis2().getAmount())+"+"+data.getHis2().getComment());
        values.put(COLUMN_HIS3, String.valueOf(data.getHis3().getAmount()) + "+" + data.getHis3().getComment());
        values.put(COLUMN_HIS4, String.valueOf(data.getHis4().getAmount()) + "+" + data.getHis4().getComment());
        values.put(COLUMN_HIS5, String.valueOf(data.getHis5().getAmount()) + "+" + data.getHis5().getComment());


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    private String getAmountFromDB(String amtDes){
        if(amtDes!=null && amtDes.length()>1 && amtDes.contains("+"))
        {
            return amtDes.split("\\+",2)[0];
        }
        else return "0";
    }
    private String getCommentFromDB(String amtDes){
        if(amtDes!=null && amtDes.length()>1 && amtDes.contains("+"))
        {
            return amtDes.split("\\+",2)[1];
        }
        else return "";
    }
    public dataModel getEntry(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_DATA, COLUMN_HIS1, COLUMN_HIS2,
                        COLUMN_HIS3, COLUMN_HIS4, COLUMN_HIS5},
                COLUMN_NAME + " = ?",
                new String[]{name}, null, null, null, null);
        if (cursor!=null)
            cursor.moveToFirst();
        String amountStr = cursor.getString(2);
        String his1Str = cursor.getString(3);
        String his2Str = cursor.getString(4);
        String his3Str = cursor.getString(5);
        String his4Str = cursor.getString(6);
        String his5Str = cursor.getString(7);
        dataModel newentry = new dataModel(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                new AmountDescription(getCommentFromDB(amountStr),Float.parseFloat(getAmountFromDB(amountStr))),
                new AmountDescription(getCommentFromDB(his1Str),Float.parseFloat(getAmountFromDB(his1Str))),
                new AmountDescription(getCommentFromDB(his2Str),Float.parseFloat(getAmountFromDB(his2Str))),
                new AmountDescription(getCommentFromDB(his3Str),Float.parseFloat(getAmountFromDB(his3Str))),
                new AmountDescription(getCommentFromDB(his4Str),Float.parseFloat(getAmountFromDB(his4Str))),
                new AmountDescription(getCommentFromDB(his5Str),Float.parseFloat(getAmountFromDB(his5Str)))
                );
        return newentry;
    }

    public List<dataModel> getAllEntries() {
        List<dataModel> dataModelList = new ArrayList<>();
        String selectQuery= "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()) {
            do {
                dataModel entry = new dataModel();
                entry.setId(Integer.parseInt(cursor.getString(0)));
                entry.setName(cursor.getString(1));
                String amountStr = cursor.getString(2);
                String his1Str = cursor.getString(3);
                String his2Str = cursor.getString(4);
                String his3Str = cursor.getString(5);
                String his4Str = cursor.getString(6);
                String his5Str = cursor.getString(7);
                entry.setAmount(new AmountDescription(getCommentFromDB(amountStr),Float.parseFloat(getAmountFromDB(amountStr))));
                entry.setHis1(new AmountDescription(getCommentFromDB(his1Str), Float.parseFloat(getAmountFromDB(his1Str))));
                entry.setHis1(new AmountDescription(getCommentFromDB(his2Str),Float.parseFloat(getAmountFromDB(his2Str))));
                entry.setHis1(new AmountDescription(getCommentFromDB(his3Str),Float.parseFloat(getAmountFromDB(his3Str))));
                entry.setHis1(new AmountDescription(getCommentFromDB(his4Str),Float.parseFloat(getAmountFromDB(his4Str))));
                entry.setHis1(new AmountDescription(getCommentFromDB(his5Str),Float.parseFloat(getAmountFromDB(his5Str))));
                dataModelList.add(entry);
            } while(cursor.moveToNext());
        }
        return dataModelList;
    }

    public void updateName(dataModel entry,String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, newName);
        values.put(COLUMN_DATA, String.valueOf(entry.getAmount().getAmount()) + "+" + entry.getAmount().getComment());
        values.put(COLUMN_HIS1, String.valueOf(entry.getHis1().getAmount()) + "+" + entry.getHis1().getComment());
        values.put(COLUMN_HIS2, String.valueOf(entry.getHis2().getAmount()) + "+" + entry.getHis2().getComment());
        values.put(COLUMN_HIS3, String.valueOf(entry.getHis3().getAmount()) + "+" + entry.getHis3().getComment());
        values.put(COLUMN_HIS4, String.valueOf(entry.getHis4().getAmount()) + "+" + entry.getHis4().getComment());
        values.put(COLUMN_HIS5, String.valueOf(entry.getHis5().getAmount()) + "+" + entry.getHis5().getComment());
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(entry.getId())});
    }
    public int updateEntry(dataModel entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,entry.getName());
        values.put(COLUMN_DATA,String.valueOf(entry.getAmount().getAmount())+"+"+entry.getAmount().getComment());
        values.put(COLUMN_HIS1,String.valueOf(entry.getHis1().getAmount())+"+"+entry.getHis1().getComment());
        values.put(COLUMN_HIS2,String.valueOf(entry.getHis2().getAmount())+"+"+entry.getHis2().getComment());
        values.put(COLUMN_HIS3,String.valueOf(entry.getHis3().getAmount())+"+"+entry.getHis3().getComment());
        values.put(COLUMN_HIS4,String.valueOf(entry.getHis4().getAmount())+"+"+entry.getHis4().getComment());
        values.put(COLUMN_HIS5, String.valueOf(entry.getHis5().getAmount()) + "+" + entry.getHis5().getComment());
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
