package com.example.shafiab.redditreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shafiab on 9/20/14.
 */
public class MyCustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> title;
    ArrayList<String> thumbnail;

    void setAdapter(Context context, ArrayList<String> title, ArrayList<String> thumbnail)
    {
        this.context = context;
        this.title = title;
        this.thumbnail = thumbnail;
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int idx, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_row, viewGroup, false);

        TextView titleView = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.thumbnail);

        titleView.setText(title.get(idx));
        DownloadAndShowImage downloadAndShowImage = new DownloadAndShowImage(imageView);

        String th = thumbnail.get(idx);
        if (th.equals(""))
            imageView.setImageResource(R.drawable.reddit_logo);
        else
            downloadAndShowImage.execute(thumbnail.get(idx));



        return rowView;

    }
}
