package com.example.abhishek.khata;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
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
   // private ActionMode mActionMode;
    public final static String EXTRA_MESSAGE = "com.example.abhishek.khata.MESSAGE";

   /* private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_long_press, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_edit:

                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.action_delete:

                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }

    }; */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySQLiteHelper db = new MySQLiteHelper(this);

        List<dataModel> entries =  db.getAllEntries();
        List<String> namelist = new ArrayList<String>();
        List<String> amountlist = new ArrayList<String>();

        for(dataModel en:entries) {
            namelist.add(en.getName());
            amountlist.add(String.valueOf(en.getAmount().getAmount()));
        }

        names = new String[namelist.size()];
        balance = new String[amountlist.size()];
        namelist.toArray(names);
        amountlist.toArray(balance);
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this,names,balance);

        setListAdapter(adapter);
       /* View myView = findViewById(R.id.nameView);
        myView.setOnLongClickListener(new View.OnLongClickListener() {
            // Called when the user long-clicks on someView
            public boolean onLongClick(View view) {
                if (mActionMode != null) {
                    return false;
                }

                // Start the CAB using the ActionMode.Callback defined above
                mActionMode = MainActivity.this.startActionMode(mActionModeCallback);
                view.setSelected(true);
                return true;
            }
        }); */

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
