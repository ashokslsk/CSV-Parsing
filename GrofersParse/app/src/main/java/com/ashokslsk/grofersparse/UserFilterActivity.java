package com.ashokslsk.grofersparse;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.gitonway.lee.niftynotification.lib.Effects;
import com.gitonway.lee.niftynotification.lib.NiftyNotificationView;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class UserFilterActivity extends AppCompatActivity {

    // List of strings
    List<String[]> scoreList;
    String temp;
    String User_id;
    // processed list
    List<String> ItemList;

    TextView ToolBarTitle;
    Toolbar toolbar;
    ScrollView sv;
    String text;
    TextView tv;
    ListView lv;
    LinearLayout layo;

    int count = 0;
    int GlobalCoounter = 0;
    NiftyDialogBuilder dialogBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_filter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ToolBarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        lv = (ListView) findViewById(R.id.listView);
        layo = (LinearLayout) findViewById(R.id.layo);
        User_id = getIntent().getExtras().getString("user");
        InputStream inputStream = getResources().openRawResource(R.raw.otdcaj);
        CSVFile csvFile = new CSVFile(inputStream);
        scoreList = csvFile.read();
        doit();
        for (String[] scoreData : scoreList) {
            List lList = Arrays.asList(scoreData);
            Iterator<String> iterator = lList.iterator();
            while (iterator.hasNext()) {
                String stemp = iterator.next();
                //    Log.d("data_recieved", stemp);
                if (stemp.equalsIgnoreCase(User_id)) {
                    for (int i = 0; i < lList.size(); i++) {
                        temp = (String) lList.get(i);
                        String[] Items = {temp};
                        for (String a : Items) {
                            count++;
                            if (count <9) {
                                tv = new TextView(this);
                                tv.setText(a);
                                layo.addView(tv);
                            }
                            else {
                                Log.d("prices", a );
                                tv.setText(a + "\n------------------------------------------------------------------");
                                count = 0;
                                GlobalCoounter++;
                            }
                        }
                    }

                }
            }
        }

        //textViewWithScroll is the name of our TextView on main.xml
//        List<String> items = Arrays.asList(Items);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, items);
//        lv.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

        //initializing a blank textview
        //so that we can just append a text later

        //display the text 10 times
        //so that it will exceed the device screen height
        //and be able to scroll
//        for (int x = 1; x < scoreList.size(); x++) {
//            tv.append("Hi there!n");
//            tv.append("Did you know that...n");
//            tv.append("I'm handsome? Hahaha! Just kidding.n");
//        }
//
    }

    public void LetsCheck(String[] data_String) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_filter, menu);
        return true;
    }

    public void calculate() {

    }

    public void doit() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                com.gitonway.lee.niftynotification.lib.Configuration cfg = new com.gitonway.lee.niftynotification.lib.Configuration.Builder()
                        .setAnimDuration(700)
                        .setDispalyDuration(3000)
                        .setBackgroundColor("#16a085")
                        .setTextColor("#ecf0f1")
                        .setIconBackgroundColor("#FFFFFFFF")
                        .setTextPadding(5)                      //dp
                        .setViewHeight(48)                      //dp
                        .setTextLines(2)                        //You had better use setViewHeight and setTextLines together
                        .setTextGravity(Gravity.CENTER)         //only text def  Gravity.CENTER,contain icon Gravity.CENTER_VERTICAL
                        .build();

                NiftyNotificationView.build(UserFilterActivity.this, "User ID :" + User_id + "\n" + "Total Purchases " + GlobalCoounter, Effects.thumbSlider, R.id.mLyout, cfg)
                        .setIcon(R.drawable.report)               //remove this line ,only text
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //add your code
                            }
                        })
                        .show();

            }
        }, 5000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
