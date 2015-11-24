package com.example.abhishek.khata;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.ListActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements ActionBar.OnNavigationListener  {

    private String[] names;
    private String[] balance;
    private ActionMode mActionMode;
    private String identityStr;
    int selectedPosition;
    long selectedID;
    String selectedName;
    View selectedView;

    private android.content.Context myContext;
    public final static String EXTRA_MESSAGE = "com.example.abhishek.khata.MESSAGE";

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

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
                    AlertDialog.Builder dlgName = new AlertDialog.Builder(myContext);
                    LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                    dlgName.setView(inflater.inflate(R.layout.name_change, null))
                            .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    MySQLiteHelper mySQLH = new MySQLiteHelper(myContext);
                                    dataModel dm = (mySQLH.getEntry(selectedName));
                                    String newName = "ashok";
                                   // String newName = ((EditText) findViewById(R.id.nameUpdate)).getText().toString();
                                    mySQLH.updateName(dm, newName);
                                    onRestart();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    dlgName.setMessage("Type the new name for selected account.");
                    EditText newNameTV = (EditText)findViewById(R.id.nameUpdate);
                   // newNameTV.setText(selectedName);
                    //newNameTV.setCursorVisible(true);



                    dlgName.setCancelable(true);
                    dlgName.create().show();
                    mode.finish();
                    return true;
                case R.id.action_delete:

                    MySQLiteHelper db = new MySQLiteHelper(myContext);
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(myContext);
                    dlgAlert.setMessage("Do you want to delete account of " + db.getEntry(identityStr).getName() + " ?");
                    dlgAlert.setTitle("Delete");
                    dlgAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            MySQLiteHelper dBase = new MySQLiteHelper(myContext);
                            dBase.deleteEntry(dBase.getEntry(identityStr));
                            Toast.makeText(MainActivity.this, "Account deleted succesfully.", Toast.LENGTH_LONG).show();
                            onRestart();
                        }
                    });
                    dlgAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

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

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySQLiteHelper db = new MySQLiteHelper(this);
        myContext = this;
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
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {            // Called when the user long-clicks on someView
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (mActionMode != null) {
                    return false;
                }

                identityStr = names[position];
                selectedID = id;
                selectedPosition = position;
                selectedView = view;
                selectedName = names[selectedPosition];
                mActionMode = MainActivity.this.startActionMode(mActionModeCallback);
                view.setSelected(true);
                return true;
            }
});

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
        super.onRestart();
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return false;
    }
}
