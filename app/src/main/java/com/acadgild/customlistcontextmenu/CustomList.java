package com.acadgild.customlistcontextmenu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CustomList extends ArrayAdapter<String> {
    private String [] name;
    private String [] phone_no;
    private Activity context;

    public CustomList(Activity context, String[] name,String[] phone_no) {
        super(context,R.layout.custom_listview,name);//Setting the layout for custom list
        this.context=context;
        this.name=name;
        this.phone_no=phone_no;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //inflate the custom list
        View listViewItem = inflater.inflate(R.layout.custom_listview, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textView);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.textView1);
        textViewName.setText(name[position]);//setting name at its position
        textViewDesc.setText(phone_no[position]);//setting phone-no at its position
        return  listViewItem;
    }
}
