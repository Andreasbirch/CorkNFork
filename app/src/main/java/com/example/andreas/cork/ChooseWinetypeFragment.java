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

public class ChooseWinetypeFragment extends Fragment {
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_winetypeselect, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        ImageButton searchDrinks = (ImageButton) view.findViewById(R.id.searchDrinkButton);
        ImageButton redwine = (ImageButton) view.findViewById(R.id.imageButton2);

        searchDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new SearchFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        redwine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drink drink = new Drink("Wine1","red","Country");
                databaseReference.child("drinks").child("Wine1").setValue(drink);

            }
        });
        return view;
    }
}
