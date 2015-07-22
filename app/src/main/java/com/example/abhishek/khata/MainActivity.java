package com.example.abhishek.khata;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements ActionBar.OnNavigationListener  {

    private String[] names;
    private String[] balance;
    public final static String EXTRA_MESSAGE = "com.example.abhishek.khata.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySQLiteHelper db = new MySQLiteHelper(this);

        List<dataModel> entries =  db.getAllEntries();
        List<String> namelist = new ArrayList<String>();
        List<String> amountlist = new ArrayList<String>();

        for(dataModel en:entries) {
            namelist.add(en.getName());
            amountlist.add(String.valueOf(en.getAmount()));
        }

        names = new String[namelist.size()];
        balance = new String[amountlist.size()];
        namelist.toArray(names);
        amountlist.toArray(balance);
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this,names,balance);

        setListAdapter(adapter);



    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this,com.example.abhishek.khata.UpdateActivity.class);
        Log.e("positon",String.valueOf(position));
        Log.e("id",String.valueOf(id));
        String message = names[position];
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.action_new) {

            Intent intent = new Intent(this,AddUser.class);
            startActivity(intent);
        }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRestart() {
        onCreate(null);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return false;
    }
}
