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

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import temporary_datebase.Drink;
import temporary_datebase.WineDatabase;

public class MealActivitiyAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    WineDatabase wineDatabase;

    String[] titles;
    String[] winesImg;
    float[] ratings;
    ArrayList<Drink> winesForCurrentDirectory;

    public MealActivitiyAdapter(Context context, String currentDirectory) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        wineDatabase = WineDatabase.getInstance();
        winesForCurrentDirectory = wineDatabase.getWinesThatGoWith(currentDirectory);

        titles = new String[winesForCurrentDirectory.size()];
        winesImg = new String[winesForCurrentDirectory.size()];
        ratings = new float[winesForCurrentDirectory.size()];

        for(int i = 0; i < winesForCurrentDirectory.size(); i++) {
            titles[i] = winesForCurrentDirectory.get(i).name;
            winesImg[i] = winesForCurrentDirectory.get(i).img;
            ratings[i] = winesForCurrentDirectory.get(i).rating;
        }
    }

<<<<<<< HEAD
=======
    public MealActivitiyAdapter(Context context, ArrayList<Drink> favorites){
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        titles = new String[favorites.size()];
        winesImg = new int[favorites.size()];
        ratings = new float[favorites.size()];

        for(int i = 0; i < favorites.size(); i++) {
            titles[i] = favorites.get(i).name;
            winesImg[i] = favorites.get(i).img;
            ratings[i] = favorites.get(i).rating;
        }
>>>>>>> 56c00bbba52c7ec3f2ea31993ea2f313c329e10b
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
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(winesImg[i]);
        Glide.with(view).load(storageReference).into(wineImageView);
        titleTextView.setText(titles[i]);

        return v;
    }
}
