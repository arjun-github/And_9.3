package com.acadgild.customlistcontextmenu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    //defining two string array
    private String name[] = {
            "Rohit",
            "Shivam",
            "Abhishek",
            "Tarun"
    };

    private String phone_no[] = {
            "0123456789",
            "1122335678",
            "5676577656",
            "4577778686"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//loading the main layout
        //binding name and phone-no with custom list
        CustomList customList = new CustomList(this, name, phone_no);
        listView = (ListView) findViewById(R.id.list);
        registerForContextMenu(listView);
        listView.setAdapter(customList);//setting the adapter

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");//Setting the title
        menu.add(0, v.getId(), 0, "Call");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Send SMS");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        //Getting the position of clicked item and using position to fetch phone no from string array
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int itemId = itemInfo.position;
        String ph= phone_no[itemId];


        if(item.getTitle()=="Call"){
            // Intent for call
            Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ph+""));
            try {
                startActivity(in);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
            }

          }
          else if(item.getTitle()=="Send SMS"){
            //Intent for sending sms
            Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address",ph);
            startActivity(smsIntent);
          }else{
              return false;
          }
          return true;
      }


}

