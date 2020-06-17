package com.example.andreas.cork;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by andreas on 11/06/2020.
 */

public class ChooseMealtypeFragment extends Fragment {

    Button selectBeef;
    Button selectPork;
    Button selectPoultry;
    Button selectVegan;
    Button selectFish;
    Button selectShellfish;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mealtypeselect, container, false);

        selectBeef = (Button) v.findViewById(R.id.select_beef);
        selectPork = (Button) v.findViewById(R.id.select_pork);
        selectPoultry = (Button) v.findViewById(R.id.select_poultry);
        selectVegan = (Button) v.findViewById(R.id.select_vegan);
        selectFish = (Button) v.findViewById(R.id.select_fish);
        selectShellfish = (Button) v.findViewById(R.id.select_shellfish);

        selectBeef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "cow");
                startActivity(startIntent);
            }
        });

        selectPork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "pork");
                startActivity(startIntent);
            }
        });

        selectPoultry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "chicken");
                startActivity(startIntent);
            }
        });

        selectVegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "vegan");
                startActivity(startIntent);
            }
        });

        selectFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "fish");
                startActivity(startIntent);
            }
        });

        selectShellfish.setOnClickListener(new View.OnClickListener() {
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