package com.example.andreas.cork;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import temporary_datebase.Drink;
import temporary_datebase.WineDatabase;

public class SearchFragment extends Fragment {
String TAG = "CorkNFork";
Drink drink;
    DatabaseReference db;
    WineDatabase wineDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searchwine, container, false);
        final AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        /*String[] drinks = new String[]{"Specific redwine 1", "Specific redwine 2", "Whiskey", "Schnapps"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,drinks);
        autoCompleteTextView.setAdapter(adapter);*/

        db = FirebaseDatabase.getInstance().getReference();
        final ArrayAdapter<String> drinkOptions = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1);
        db.child("drinks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()){

                    String suggestion = suggestionSnapshot.child("name").getValue(String.class);

                    drinkOptions.add(suggestion);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        autoCompleteTextView.setAdapter(drinkOptions);
        wineDatabase = WineDatabase.getInstance();
        db.child("drinks").addValueEventListener(new ValueEventListener() {
                                                     @Override
                                                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                         // restart wine database
                                                         wineDatabase.restartDatabase();
                                                         for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                             String name = snapshot.child("name").getValue(String.class);
                                                             float rating = snapshot.child("rating").getValue(Float.class);
                                                             String type = snapshot.child("type").getValue(String.class);
                                                             int id = snapshot.child("id").getValue(Integer.class);
                                                             String description = snapshot.child("description").getValue(String.class);
                                                             String country = snapshot.child("country").getValue(String.class);
                                                             int price = snapshot.child("price").getValue(Integer.class);
                                                             int ratingAmount = snapshot.child("ratingAmount").getValue(Integer.class);

                                                             //TODO max sry addWine skal nu tage alle 8 fields
                                                             wineDatabase.addWine(name, type, country, rating, price, ratingAmount, id, description);
                                                         }

                                                     }

                                                     @Override
                                                     public void onCancelled(@NonNull DatabaseError databaseError) {

                                                     }
                                                 });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showWineActivity = new Intent(getActivity(), WineActivity.class);
                String itemValue = (String) adapterView.getItemAtPosition(i);
                showWineActivity.putExtra("com.example.andreas.cork.WINE", itemValue);
                startActivity(showWineActivity);
            }
        });


        return view;
    }


}
