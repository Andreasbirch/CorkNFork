package com.example.andreas.cork;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import temporary_datebase.WineDatabase;

public class DrinkChoiceActivity extends AppCompatActivity {
    WineDatabase wineDatabase;
    ListView myListView;
    TextView titleTextView;
    TextView descriptionTextView;
    ImageView iconImageView;
    Button food1;
    Button food2;
    Button food3;
    Button food4;
    Button food5;
    Button food6;



    String[] titles;
    String[] descriptions;
    String currentDirectory;
/*
    public void updateData(){

        //SETTING UP THE DATABASE
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        wineDatabase = WineDatabase.getInstance();
        db.child("drinks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // restart wine database
                wineDatabase.restartDatabase();
                for ( DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String name = snapshot.child("name").getValue(String.class);
                    int img = R.drawable.wine_fletris;
                    float rating = snapshot.child("rating").getValue(Float.class);
                    String type = snapshot.child("type").getValue(String.class);

                    wineDatabase.addWine(name, img, rating, type);
                }

                //Display data

                MealActivitiyAdapter mealActivitiyAdapter = new MealActivitiyAdapter(DrinkChoiceActivity.this, currentDirectory);
                myListView.setAdapter(mealActivitiyAdapter);

                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent showWineActivity = new Intent(getApplicationContext(), WineActivity.class);
                        String itemValue = (String) myListView.getItemAtPosition(i);
                        showWineActivity.putExtra("com.example.andreas.cork.WINE", itemValue);
                        startActivity(showWineActivity);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //END
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Database setup
        //updateData();

        setContentView(R.layout.activity_wine_meal);
        Resources res = getResources();
        titles = res.getStringArray(R.array.drink_activity_titles);
        descriptions = res.getStringArray(R.array.drink_activity_descriptions);

        titleTextView =  (TextView) findViewById(R.id.drinkTitleTextView);
        descriptionTextView = (TextView) findViewById(R.id.drinkDescriptionTextView);
        iconImageView = (ImageView) findViewById(R.id.drinkImageView);
        food1 = (Button) findViewById(R.id.food1);
        food2 = (Button) findViewById(R.id.food2);
        food3 = (Button) findViewById(R.id.food3);
        food4 = (Button) findViewById(R.id.food4);
        food5 = (Button) findViewById(R.id.food5);
        food6 = (Button) findViewById(R.id.food6);

        currentDirectory = getIntent().getExtras().getString("com.example.andreas.cork.DRINK_TYPE");

        if(currentDirectory.equals("redwine")) {
            titleTextView.setText(titles[0]);
            iconImageView.setImageResource(R.drawable.ic_drinktype_redwine);
            descriptionTextView.setText(descriptions[0]);
            food1.setVisibility(View.VISIBLE);

        } else if(currentDirectory.equals("whitewine")) {
            titleTextView.setText(titles[1]);
            iconImageView.setImageResource(R.drawable.ic_drinktype_whitewine);
            descriptionTextView.setText(descriptions[1]);
            food3.setVisibility(View.VISIBLE);
            food4.setVisibility(View.VISIBLE);
        } else if(currentDirectory.equals("champagne")) {
            titleTextView.setText(titles[2]);
            iconImageView.setImageResource(R.drawable.ic_drinktype_champagne);
            descriptionTextView.setText(descriptions[2]);
            food6.setVisibility(View.VISIBLE);


        } else if(currentDirectory.equals("rosewine")) {
            titleTextView.setText(titles[3]);
            iconImageView.setImageResource(R.drawable.ic_drinktype_rosewine);
            descriptionTextView.setText(descriptions[3]);
            food5.setVisibility(View.VISIBLE);

        } else if(currentDirectory.equals("whiskey")) {
            titleTextView.setText(titles[4]);
            iconImageView.setImageResource(R.drawable.ic_drinktype_whiskey);
            descriptionTextView.setText(descriptions[4]);
            food1.setVisibility(View.VISIBLE);

        }
    }

    /*@Override
    protected void onResume() {
        super.onResume();


    }*/
}