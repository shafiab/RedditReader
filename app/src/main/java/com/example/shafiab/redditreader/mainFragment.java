package com.example.shafiab.redditreader;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shafiab on 9/17/14.
 */
public class mainFragment extends Fragment
{
    onItemSelectMainFragment myOnItemSelect;
    View myView;
    ArrayList<String> myList;
    ArrayList<String> myListThumbnail;
    AdapterView.OnItemClickListener myListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        myView =  inflater.inflate(R.layout.main_fragment,container,false);
        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();
        populateListView();
        myListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String info = ((TextView) view).getText().toString();
                //Toast.makeText(getActivity(), "Item " + info, Toast.LENGTH_LONG).show();
                myOnItemSelect.onItemSelect(i);


            }
        };

        ListView myTitleView = (ListView) myView.findViewById(R.id.listView);
        myTitleView.setOnItemClickListener(myListener);

    }

    public interface onItemSelectMainFragment {
        void onItemSelect(int i);
    }



    void initialize(ArrayList<String> myList, ArrayList<String> myListThumbnail)
    {
        this.myList = myList;
        this.myListThumbnail = myListThumbnail;
    }

    void populateListView()
    {
        ListView myTitleView = (ListView) myView.findViewById(R.id.listView);
        MyCustomAdapter customlistAdapter = new MyCustomAdapter();
        customlistAdapter.setAdapter(getActivity().getApplicationContext(), myList, myListThumbnail);
        myTitleView.setAdapter(customlistAdapter);

     /*   ArrayAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, titleList);
        ListView myTitleView = (ListView) myView.findViewById(R.id.listView);
        myTitleView.setAdapter(listAdapter);*/

    }


    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        try {
            myOnItemSelect = (onItemSelectMainFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnKeyPressListener");
        }
    }
}
