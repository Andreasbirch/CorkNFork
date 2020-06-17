package com.example.andreas.cork;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Rating;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import temporary_datebase.Wine;
import temporary_datebase.WineDatabase;

public class MealActivitiyAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    ListView myListView;
    WineDatabase wineDatabase;

    String[] titles;
    int[] winesImg;
    float[] ratings;
    String currentDirectory;
    ArrayList<Wine> winesForCurrentDirectory;

    public MealActivitiyAdapter(Context context, String currentDirectory) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        wineDatabase = WineDatabase.getInstance();
        winesForCurrentDirectory = wineDatabase.getWinesThatGoWith(currentDirectory);

        titles = new String[winesForCurrentDirectory.size()];
        winesImg = new int[winesForCurrentDirectory.size()];
        ratings = new float[winesForCurrentDirectory.size()];

        for(int i = 0; i < winesForCurrentDirectory.size(); i++) {
            titles[i] = winesForCurrentDirectory.get(i).title;
            winesImg[i] = winesForCurrentDirectory.get(i).img;
            ratings[i] = winesForCurrentDirectory.get(i).rating;
        }


    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public String getItem(int i) {
        return titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       View v = mInflater.inflate(R.layout.activity_meal_listview_item, null);
        ImageView wineImageView = (ImageView) v.findViewById(R.id.wineImageView);
        TextView titleTextView = (TextView) v.findViewById(R.id.titleTextView);
        RatingBar staticRatingBar = (RatingBar) v.findViewById(R.id.staticRatingBar);

        staticRatingBar.setRating(ratings[i]);
        wineImageView.setImageResource(winesImg[i]);
        titleTextView.setText(titles[i]);

        return v;
    }
}
