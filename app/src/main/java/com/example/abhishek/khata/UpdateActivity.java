package com.example.abhishek.khata;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UpdateActivity extends ActionBarActivity {


    private String identity;
    private float amount;
    private float his1;
    private float his2;
    private float his3;
    private float his4;
    private float his5;
    private String name;

    TextView username;
    TextView userBal;
    TextView userhis1;
    TextView userhis2;
    TextView userhis3;
    TextView userhis4;
    TextView userhis5;

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
        userhis4 = (TextView) findViewById(R.id.tvHis4);
        userhis5 = (TextView) findViewById(R.id.tvHis5);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        identity = message;
        dataModel user = db.getEntry(message);


        username.setText(user.getName());
        //String amount = user.getAmount().getComment();
        //if(amount.length()>10) amount = amount.substring(0,10);
        //String his1Cmt = user.getHis1().getComment();
        //if(amount.length()>10) his1C
        NumberFormat formatter = new DecimalFormat("#0.00");
        userBal.setText("Your Balance: " + String.valueOf(formatter.format(user.getAmount().getAmount())+"\n"+user.getAmount().getComment()));
        userhis1.setText(String.valueOf(formatter.format(user.getHis1().getAmount())+" "+user.getHis1().getComment()));
        userhis2.setText(String.valueOf(formatter.format(user.getHis2().getAmount())+" "+user.getHis2().getComment()));
        userhis3.setText(String.valueOf(formatter.format(user.getHis3().getAmount())+" "+user.getHis3().getComment()));
        userhis4.setText(String.valueOf(formatter.format(user.getHis4().getAmount())+" "+user.getHis4().getComment()));
        userhis5.setText(String.valueOf(formatter.format(user.getHis5().getAmount())+" "+user.getHis5().getComment()));
    }

    //update entry functions
    public void givenEntry(View view) {

        MySQLiteHelper db = new MySQLiteHelper(this);
        dataModel oldentry = db.getEntry(identity);
        String  newname = oldentry.getName();
        long newid = oldentry.getId();
        AmountDescription newhis1 = oldentry.getAmount();
        AmountDescription newhis2 = oldentry.getHis1();
        AmountDescription newhis3 = oldentry.getHis2();
        AmountDescription newhis4 = oldentry.getHis3();
        AmountDescription newhis5 = oldentry.getHis4();
        AmountDescription newamount = new AmountDescription();

        String etamnt = ((EditText) findViewById(R.id.etAmount)).getText().toString();
        String etcmnt = ((EditText) findViewById(R.id.etComment)).getText().toString();
        if(etamnt.length()==0 || etamnt==null ) {
            if(etcmnt==null || etcmnt.length()==0)
            return;
            else etamnt = "0";
        }
        if(etcmnt.length()==0 || etcmnt==null)
            etcmnt = "";
        String date = (new SimpleDateFormat("E dd.MM.yyyy 'at' HH:mm")).format(new Date());
        newamount.setComment(date+" "+etcmnt);
        newamount.setAmount(oldentry.getAmount().getAmount() + Float.parseFloat(etamnt));
        dataModel newentry = new dataModel(newid,newname,newamount,newhis1,newhis2,newhis3,newhis4,newhis5);
        db.updateEntry(newentry);
        dataModel s = db.getEntry(newentry.getName());
        Intent intent = getIntent();
        startActivity(intent);

    }
    public void takenEntry(View view) {

        MySQLiteHelper db = new MySQLiteHelper(this);
        dataModel oldentry = db.getEntry(identity);
        String  newname = oldentry.getName();
        long newid = oldentry.getId();
        AmountDescription newhis1 = oldentry.getAmount();
        AmountDescription newhis2 = oldentry.getHis1();
        AmountDescription newhis3 = oldentry.getHis2();
        AmountDescription newhis4 = oldentry.getHis3();
        AmountDescription newhis5 = oldentry.getHis4();
        AmountDescription newamount = new AmountDescription();

        String etamnt = ((EditText) findViewById(R.id.etAmount)).getText().toString();
        String etcmnt = ((EditText) findViewById(R.id.etComment)).getText().toString();
        if(etamnt.length()==0 || etamnt==null ){
            if(etcmnt==null || etcmnt.length()==0)
                return;
            else etamnt = "0";
        }
        if(etcmnt.length()==0 || etcmnt==null)
            etcmnt = "";
        String date = (new SimpleDateFormat("E dd.MM.yyyy 'at' HH:mm")).format(new Date());
        newamount.setComment(date+" "+etcmnt);
        newamount.setAmount(oldentry.getAmount().getAmount() - Float.parseFloat(etamnt));
        dataModel newentry = new dataModel(newid,newname,newamount,newhis1,newhis2,newhis3,newhis4,newhis5);
        db.updateEntry(newentry);
        Intent intent = getIntent();
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {

        finish();
    }

    public void deleteEntry(View view) {
        final android.content.Context myContext = this;
        MySQLiteHelper db = new MySQLiteHelper(myContext);
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Do you want to delete account of " + db.getEntry(identity).getName() + " ?");
        dlgAlert.setTitle("Delete");
        dlgAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MySQLiteHelper dBase = new MySQLiteHelper(myContext);
                dBase.deleteEntry(dBase.getEntry(identity));
                finish();
            }
        });
        dlgAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
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
