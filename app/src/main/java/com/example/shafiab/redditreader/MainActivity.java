package com.example.shafiab.redditreader;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity implements mainFragment.onItemSelectMainFragment{

    String [] title;
    String [] url;
    String [] thumbnail;
    List<String[]> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get data from web
        FetchData newData = new FetchData();
        try{
           myList = newData.execute().get();
        }
        catch (Exception e)
        {

        }

        title = myList.get(0);
        url = myList.get(1);
        thumbnail = myList.get(2);


        ArrayList<String> titleList = new ArrayList<String>();
        titleList.addAll(Arrays.asList(title));
        ArrayList<String> titleThumbnail = new ArrayList<String>();
        titleThumbnail.addAll(Arrays.asList(thumbnail));





        // create fragment
        fragment_test test = new fragment_test();
        mainFragment display_main = new mainFragment();


        // add fragment
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.mainFrame, display_main, "main display");
        ft.commit();

        display_main.initialize(titleList, titleThumbnail);

        //mainFragmentUI myUI = new mainFragmentUI(this, display_main);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelect(int id)
    {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("idItem", id);
        intent.putExtra("url",url);

        startActivity(intent);
    }

}
