package com.example.abhishek.khata;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


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

        EditText etamount = (EditText) findViewById(R.id.etAmount);
        EditText etname = (EditText) findViewById(R.id.etName);
        MySQLiteHelper dbhelper = new MySQLiteHelper(this);
        String name = etname.getText().toString();
        float amount = Float.parseFloat(etamount.getText().toString());

        int newid = dbhelper.getEntryCount()+1;

        dataModel entry = new dataModel(newid,name,amount);

        dbhelper.addEntry(entry);

        finish();
    }
    public void takenEntry(View view) {

        EditText etamount = (EditText) findViewById(R.id.etAmount);
        EditText etname = (EditText) findViewById(R.id.etName);
        MySQLiteHelper dbhelper = new MySQLiteHelper(this);
        String name = etname.getText().toString();
        float amount = Float.parseFloat(etamount.getText().toString());
        amount = -1*amount;

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
