package com.example.abhishek.khata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by abhishek on 1/2/15.
 */
public class MySimpleArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] names;
    private final String[] balance;

    public MySimpleArrayAdapter(Context context, String[] names,String[] balance)
    {
        super(context,R.layout.activity_main,names);
        this.context = context;
        this.names = names;
        this.balance = balance;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_main,parent,false);

        TextView nameview = (TextView) rowView.findViewById(R.id.nameView);
        TextView balview = (TextView) rowView.findViewById(R.id.balView);


        nameview.setText(names[position]);
        balview.setText(balance[position]);


        return rowView;

    }

}
