package com.example.andreas.cork;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import temporary_datebase.Drink;


/**
 * Created by andreas on 11/06/2020.
 */

public class ChooseDrinktypeFragment extends Fragment {
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drinktypeselect, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Button searchDrinks = (Button) view.findViewById(R.id.select_search);
        Button select_redwine = (Button) view.findViewById(R.id.select_redwine);
        Button select_whitewine = (Button) view.findViewById(R.id.select_whitewine);
        Button select_rosewine = (Button) view.findViewById(R.id.select_rosewine);
        Button select_champagne = (Button) view.findViewById(R.id.select_champagne);
        Button select_whiskey = (Button) view.findViewById(R.id.select_whiskey);


        searchDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new SearchFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        select_redwine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
}
