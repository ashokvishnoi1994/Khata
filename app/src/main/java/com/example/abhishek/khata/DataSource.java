package com.example.abhishek.khata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by abhishek on 1/2/15.
 */
public class DataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allcolumns = {MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_NAME,MySQLiteHelper.COLUMN_DATA,
            MySQLiteHelper.COLUMN_HIS1,MySQLiteHelper.COLUMN_HIS2,MySQLiteHelper.COLUMN_HIS3};

    public DataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }
    public void close() {
        dbHelper.close();
    }
    public dataModel addEntry(String name,int amount,int his1,int his2,int his3)
    {
       return null;
    }

}
