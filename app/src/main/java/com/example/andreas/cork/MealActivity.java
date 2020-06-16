package com.example.andreas.cork;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MealActivity extends AppCompatActivity {

    ListView myListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Resources res = getResources();
        myListView = findViewById(R.id.myListView);

        MealActivitiyAdapter mealActivitiyAdapter = new MealActivitiyAdapter(this);
        myListView.setAdapter(mealActivitiyAdapter);
    }
}