package com.example.andreas.cork;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
       final DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("drinks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // restart wine database
                wineDatabase.restartDatabase();
                for ( DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String name = snapshot.child("name").getValue(String.class);
                    float rating = snapshot.child("rating").getValue(Float.class);
                    String type = snapshot.child("type").getValue(String.class);
                    int id = snapshot.child("id").getValue(Integer.class);
                    String description = snapshot.child("description").getValue(String.class);
                    String country = snapshot.child("country").getValue(String.class);
                    int price = snapshot.child("price").getValue(Integer.class);
                    int ratingAmount = snapshot.child("ratingAmount").getValue(Integer.class);
                    String goesWithMeals = snapshot.child("goesWithMeals").getValue(String.class);

                    char[] c = goesWithMeals.toCharArray();
                    List<String> goesWith = new ArrayList<>();

                    if(c[0]== '1'){
                        goesWith.add("Beef");
                    }
                    if(c[1]== '1'){
                        goesWith.add("Pork");
                    }
                    if(c[2]== '1'){
                        goesWith.add("Poultry");
                    }
                    if(c[3]== '1'){
                        goesWith.add("Vegetarian");
                    }
                    if(c[4]== '1'){
                        goesWith.add("Fish");
                    }
                    if(c[5]== '1'){
                        goesWith.add("Shellfish");
                    }

                    wineDatabase.addWine(name, type, country, rating, price, ratingAmount, id, description, goesWith, goesWithMeals);
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
        wineDatabase = WineDatabase.getInstance();
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