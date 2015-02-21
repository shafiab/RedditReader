package com.example.shafiab.redditreader;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by shafiab on 9/17/14.
 */
public class mainFragmentUI {
    Context context;
    mainFragment myDisplay;
    public mainFragmentUI(Context context, mainFragment myDisplay)
    {
        this.context = context;
        this.myDisplay = myDisplay;
    }

    void showList(String[] title)
    {
        ArrayList<String> titleList = new ArrayList<String>();
        titleList.addAll(Arrays.asList(title));
        //ArrayAdapter listAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, title);



//        myDisplay.populateListView(titleList,context);

    }
}
