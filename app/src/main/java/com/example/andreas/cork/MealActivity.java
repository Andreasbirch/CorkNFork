package com.example.andreas.cork;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

import temporary_datebase.Wine;
import temporary_datebase.WineDatabase;

public class MealActivity extends AppCompatActivity {
    WineDatabase wineDatabase;
    ListView myListView;
    TextView titleTextView;
    TextView descriptionTextView;
    ImageView iconImageView;

    String[] titles;
    String[] descriptions;
    String currentDirectory;

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

                MealActivitiyAdapter mealActivitiyAdapter = new MealActivitiyAdapter(MealActivity.this, currentDirectory);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Database setup
        updateData();

        setContentView(R.layout.activity_meal);
        Resources res = getResources();
        titles = res.getStringArray(R.array.meal_activity_titles);
        descriptions = res.getStringArray(R.array.meal_activity_descriptions);

        myListView = findViewById(R.id.myListView);

        titleTextView =  (TextView) findViewById(R.id.titleTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        iconImageView = (ImageView) findViewById(R.id.iconImageView);

        currentDirectory = getIntent().getExtras().getString("com.example.andreas.cork.MEAL_TYPE");

        if(currentDirectory.equals("cow")) {
            titleTextView.setText(titles[0]);
            iconImageView.setImageResource(R.drawable.ic_mealtype_beef);
            descriptionTextView.setText(descriptions[0]);
        } else if(currentDirectory.equals("pork")) {
            titleTextView.setText(titles[1]);
            iconImageView.setImageResource(R.drawable.ic_mealtype_pork);
            descriptionTextView.setText(descriptions[1]);
        } else if(currentDirectory.equals("chicken")) {
            titleTextView.setText(titles[2]);
            iconImageView.setImageResource(R.drawable.ic_mealtype_poultry);
            descriptionTextView.setText(descriptions[2]);
        } else if(currentDirectory.equals("vegan")) {
            titleTextView.setText(titles[3]);
            iconImageView.setImageResource(R.drawable.ic_mealtype_vegan);
            descriptionTextView.setText(descriptions[3]);
        } else if(currentDirectory.equals("fish")) {
            titleTextView.setText(titles[4]);
            iconImageView.setImageResource(R.drawable.ic_mealtype_fish);
            descriptionTextView.setText(descriptions[4]);
        } else {
            titleTextView.setText(titles[5]);
            iconImageView.setImageResource(R.drawable.ic_mealtype_shellfish);
            descriptionTextView.setText(descriptions[5]);
        }
    }

    /*@Override
    protected void onResume() {
        super.onResume();


    }*/
}