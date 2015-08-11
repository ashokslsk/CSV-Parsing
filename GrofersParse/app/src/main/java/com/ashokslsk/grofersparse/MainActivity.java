package com.ashokslsk.grofersparse;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ashokslsk.grofersparse.sliding.drawer.NavDrawerItem;
import com.ashokslsk.grofersparse.sliding.drawer.NavDrawerListAdapter;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;
    TextView ToolBarTitle;
    Toolbar toolbar;
    String line = null;

    NiftyDialogBuilder dialogBuilder;



    // Navigation Drawer Setup Requirements
    public DrawerLayout mDrawerLayout;
    public ListView mDrawerList;
    public ActionBarDrawerToggle mDrawerToggle;
    public CharSequence mDrawerTitle;
    public CharSequence mTitle;
    NavDrawerListAdapter adapter;
    List<NavDrawerItem> dataList;

    // List of strings
    List<String[]> scoreList;
    String temp;


    // processed list
    List<String> ItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ToolBarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.listView);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);
        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);
        InputStream inputStream = getResources().openRawResource(R.raw.otdcaj);
        CSVFile csvFile = new CSVFile(inputStream);

        scoreList = csvFile.read();

        // Filtering the list


    for(String[] scoreData:scoreList ) {
            itemArrayAdapter.add(scoreData);
            LetsCheck(scoreData);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] messge = scoreList.get(position);
                line = "User ID :"+messge[0] +"\n" + "Sign up ts :"+ messge [1] +"\n" +
                        "Cart ID : "+ messge[2] + "\n" + "Order Placed time stamp :" +messge[3] +"\n" + "Order scheduled timestamp :"+messge[4] + "\n"+
                          "Product id :"+messge[5] +"\n" + "Product name :" + messge[6] +"\n" +"Unit price :"+messge[7]+"\n" +"Quntity :"+messge[8];
                String user_id = messge[0];
                String product_id = messge[5];
                    dialogBring(line,messge,user_id,product_id);
            }
        });

        // navigation Bar setup
        // Initializing
        dataList = new ArrayList<NavDrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        // Add Drawer Item to dataList
        dataList.add(new NavDrawerItem("User ", R.drawable.user));
        dataList.add(new NavDrawerItem("Products", R.drawable.msrlsr));
        dataList.add(new NavDrawerItem("Date", R.drawable.time_based));
        dataList.add(new NavDrawerItem("About Us", R.drawable.about_us));

        adapter = new NavDrawerListAdapter(this, R.layout.drawer_list_item,
                dataList);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.app_name,
                R.string.app_name) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle("");
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("");
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        if (savedInstanceState == null) {
            SelectItem(0);
        }
    }

    public void SelectItem(int possition) {

        switch (possition) {
            case 0:

                break;
            default:
                break;
        }
    }

    public void LetsCheck(String[] data_String) {
        List lList = Arrays.asList(data_String);
        Iterator<String> iterator = lList.iterator();
        while (iterator.hasNext()) {
            String stemp = iterator.next();
            if (stemp.equalsIgnoreCase("11631")) {
            //    Log.d("data_recieved", stemp);
                for (int i = 0; i < lList.size(); i++) {
                    temp = (String) lList.get(i);
                    String[] Items = {temp};
                     ItemList = Arrays.asList(Items);
                        Log.d("data_recieved", ItemList.get(0) + "");
                }
            }
        }
    }

    public void dialogBring(String message, final String[] user, final String user_id, final String product_id){
        Log.d("called", message.toString());
        dialogBuilder = NiftyDialogBuilder.getInstance(MainActivity.this);
        dialogBuilder
                .withTitle("info")
                .withMessage(message)
                        //.withTitle(null)  no title
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#9b59b6")
                .withEffect(Effectstype.Fliph)
                .withButton1Text("Product Report")
                .withButton2Text("User Report")
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, UserFilterActivity.class);
                        i.putExtra("user",user_id);
                        startActivity(i);
                    }
                })
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                        Intent i = new Intent(MainActivity.this, UserFilterActivity.class);
                        i.putExtra("user",product_id);
                        startActivity(i);
                    }
                }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.screentwo) {
            Intent ui = new Intent(MainActivity.this, NextMonthdataActivity.class);
            startActivity(ui);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            SelectItem(position);

            if (position == 0) {
                Intent i = new Intent(MainActivity.this, NextMonthdataActivity.class);
                startActivity(i);
            }
        }

    }
}
