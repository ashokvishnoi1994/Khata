package com.example.abhishek.khata;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AddUser extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_user, menu);
        return true;
    }



    public void addEntry(View view) {

        String etamount = ((EditText) findViewById(R.id.etAmount)).getText().toString();
        String etname = ((EditText) findViewById(R.id.etName)).getText().toString();
        String etcomment = ((EditText) findViewById(R.id.etComment)).getText().toString();
        MySQLiteHelper dbhelper = new MySQLiteHelper(this);
        String name = etname;
        if(etamount.length()==0 || etamount==null)
            etamount = "0";
        if(etcomment==null)
            etcomment = "";
        String date = (new SimpleDateFormat("E dd.MM.yyyy 'at' HH:mm")).format(new Date());
        AmountDescription amount = new AmountDescription(date +" "+etcomment,
                Float.parseFloat(etamount));

        int newid = dbhelper.getEntryCount()+1;

        dataModel entry = new dataModel(newid,name,amount);

        dbhelper.addEntry(entry);

        finish();
    }
    public void takenEntry(View view) {

        String etamount = ((EditText) findViewById(R.id.etAmount)).getText().toString();
        String etname = ((EditText) findViewById(R.id.etName)).getText().toString();
        String etcomment = ((EditText) findViewById(R.id.etComment)).getText().toString();
        MySQLiteHelper dbhelper = new MySQLiteHelper(this);
        String name = etname;
        if(etamount.length()==0 || etamount==null)
            etamount = "0";
        if(etcomment==null)
            etcomment = "";
        String date = (new SimpleDateFormat("E dd.MM.yyyy 'at' HH:mm")).format(new Date());
        AmountDescription amount = new AmountDescription(date+" "+etcomment,
                Float.parseFloat(etamount));
        amount.setAmount(-1*amount.getAmount());

        int newid = dbhelper.getEntryCount()+1;

        dataModel entry = new dataModel(newid,name,amount);

        dbhelper.addEntry(entry);

        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
