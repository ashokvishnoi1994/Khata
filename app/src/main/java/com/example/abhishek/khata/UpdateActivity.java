package com.example.abhishek.khata;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class UpdateActivity extends ActionBarActivity {


    private String identity;
    private int amount;
    private int his1;
    private int his2;
    private int his3;
    private String name;

    TextView username;
    TextView userBal;
    TextView userhis1;
    TextView userhis2;
    TextView userhis3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        MySQLiteHelper db = new MySQLiteHelper(this);

        username = (TextView) findViewById(R.id.tvHolderName);
        userBal = (TextView) findViewById(R.id.tvHolderBal);
        userhis1 = (TextView) findViewById(R.id.tvHis1);
        userhis2 = (TextView) findViewById(R.id.tvHis2);
        userhis3 = (TextView) findViewById(R.id.tvHis3);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        identity = message;
        dataModel user = db.getEntry(message);


        username.setText(user.getName());
        userBal.setText("Your Balance: " + String.valueOf(user.getAmount()));
        userhis1.setText(String.valueOf(user.getHis1()));
        userhis2.setText(String.valueOf(user.getHis2()));
        userhis3.setText(String.valueOf(user.getHis3()));



    }

    //update entry functions
    public void givenEntry(View view) {

        MySQLiteHelper db = new MySQLiteHelper(this);
        dataModel oldentry = db.getEntry(identity);
        String  newname = oldentry.getName();
        long newid = oldentry.getId();
        int newhis1 = oldentry.getAmount();
        int newhis2 = oldentry.getHis1();
        int newhis3 = oldentry.getHis2();
        int newamount = oldentry.getAmount();

        EditText etamnt = (EditText) findViewById(R.id.etAmount);
        int amount = Integer.parseInt(etamnt.getText().toString());
        newamount = newamount+amount;
        dataModel newentry = new dataModel(newid,newname,newamount,newhis1,newhis2,newhis3);
        db.updateEntry(newentry);
        Intent intent = getIntent();
        startActivity(intent);

    }
    public void takenEntry(View view) {

        MySQLiteHelper db = new MySQLiteHelper(this);
        dataModel oldentry = db.getEntry(identity);
        String  newname = oldentry.getName();
        long newid = oldentry.getId();
        int newhis1 = oldentry.getAmount();
        int newhis2 = oldentry.getHis1();
        int newhis3 = oldentry.getHis2();
        int newamount = oldentry.getAmount();

        EditText etamnt = (EditText) findViewById(R.id.etAmount);
        int amount = Integer.parseInt(etamnt.getText().toString());
        newamount = newamount-amount;
        dataModel newentry = new dataModel(newid,newname,newamount,newhis1,newhis2,newhis3);
        db.updateEntry(newentry);
        Intent intent = getIntent();
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {

        finish();
    }

    public void deleteEntry(View view) {
        MySQLiteHelper db = new MySQLiteHelper(this);
        db.deleteEntry(db.getEntry(identity));
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update, menu);
        return true;
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
