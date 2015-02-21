package com.example.shafiab.redditreader;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by shafiab on 9/19/14.
 */
public class DetailActivity extends Activity {
    String[] url;
    int idItem;
    String urlLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        Intent intent = getIntent();
        url = intent.getStringArrayExtra("url");
        idItem = intent.getIntExtra("idItem", 0);
        urlLink  = url[idItem];

        DetailFragment detailDisplay = new DetailFragment();
        detailDisplay.initialize(urlLink);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_detail, detailDisplay, "detail display");
        ft.commit();
    }
}
