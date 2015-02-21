package com.example.shafiab.redditreader;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shafiab on 9/2/14.
 */
public class fragment_test extends Fragment {
    String LOG_TAG = "Shafi Fragment_timeDisplay";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(LOG_TAG, "inside onCreateView()");
        View view = inflater.inflate(R.layout.main_fragment, container,false);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
    }


}
