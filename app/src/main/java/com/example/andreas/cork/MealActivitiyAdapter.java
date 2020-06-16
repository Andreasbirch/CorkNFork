package com.example.andreas.cork;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MealActivitiyAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    ListView myListView;
    int[] winesImg;
    String[] titles;

    public MealActivitiyAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        titles = new String[]{"Sauvignon Blanc", "Cuvée Vieilles Vignes", "Reserve Zinfandel"};
        winesImg = new int[]{R.drawable.wine_sauvignon_blanc, R.drawable.wine_reserve_zinfandel, R.drawable.wine_sauvignon_blanc};
    }


    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
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

        wineImageView.setImageResource(winesImg[i]);
        titleTextView.setText(titles[i]);

        return v;
    }
}
