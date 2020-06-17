package com.example.andreas.cork;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by andreas on 11/06/2020.
 */

public class ChooseMealtypeFragment extends Fragment {

    ImageView imageButton2ImageView;
    ImageView imageButton3ImageView;
    ImageView imageButton4ImageView;
    ImageView imageButton5ImageView;
    ImageView imageButton6ImageView;
    ImageView imageButton7ImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mealtypeselect, container, false);

        imageButton2ImageView = (ImageView) v.findViewById(R.id.imageButton2);
        imageButton3ImageView = (ImageView) v.findViewById(R.id.imageButton3);
        imageButton4ImageView = (ImageView) v.findViewById(R.id.imageButton4);
        imageButton5ImageView = (ImageView) v.findViewById(R.id.imageButton5);
        imageButton6ImageView = (ImageView) v.findViewById(R.id.imageButton6);
        imageButton7ImageView = (ImageView) v.findViewById(R.id.imageButton7);

        imageButton2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "cow");
                startActivity(startIntent);
            }
        });

        imageButton3ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "pork");
                startActivity(startIntent);
            }
        });

        imageButton4ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "chicken");
                startActivity(startIntent);
            }
        });

        imageButton5ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "vegan");
                startActivity(startIntent);
            }
        });

        imageButton6ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "fish");
                startActivity(startIntent);
            }
        });

        imageButton7ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "shellfish");
                startActivity(startIntent);
            }
        });


        return v;
    }
}