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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import temporary_datebase.WineDatabase;

public class DrinkChoiceActivity extends AppCompatActivity {
    WineDatabase wineDatabase;
    ListView myListView;
    TextView titleTextView, descriptionTextView;
    ImageView iconImageView;
    Button leftButton, rightButton;

    TableLayout tableLayout;
    TableRow row1;

    String[] titles, descriptions;
    String currentDirectory;

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
        leftButton = (Button) findViewById(R.id.btn_left);
        rightButton = (Button) findViewById(R.id.btn_right);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        row1 = (TableRow) findViewById(R.id.tableRow1);

        currentDirectory = getIntent().getExtras().getString("com.example.andreas.cork.DRINK_TYPE");

        if(currentDirectory.equals("redwine")) {
            titleTextView.setText(titles[0]);
            iconImageView.setImageResource(R.drawable.ic_drinktype_redwine);

            leftButton = (Button) findViewById(R.id.btn_left);
            leftButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mealtype_beef, 0 ,0);
            rightButton = (Button) findViewById(R.id.btn_right);
            rightButton.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_mealtype_pork,0,0);
            rightButton.setText("Pork");
            leftButtonListener(descriptions[0]);
            rightButtonListener(descriptions[1]);
            rightButton.setVisibility(View.VISIBLE);

        } else if(currentDirectory.equals("whitewine")) {
            titleTextView.setText(titles[1]);
            iconImageView.setImageResource(R.drawable.ic_drinktype_whitewine);

            leftButton = (Button) findViewById(R.id.btn_left);
            leftButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mealtype_fish, 0 ,0);
            leftButton.setText(R.string.mealtype_fish);
            leftButtonListener(descriptions[2]);

            rightButton = (Button) findViewById(R.id.btn_right);
            rightButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mealtype_poultry, 0 ,0);
            rightButton.setText(R.string.mealtype_poultry);
            rightButtonListener(descriptions[3]);
            rightButton.setVisibility(View.VISIBLE);

        } else if(currentDirectory.equals("champagne")) {
            titleTextView.setText(titles[2]);
            iconImageView.setImageResource(R.drawable.ic_drinktype_champagne);

            leftButton = (Button) findViewById(R.id.btn_left);
            leftButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mealtype_shellfish, 0 ,0);
            leftButton.setText(R.string.mealtype_shellfish);
            leftButtonListener(descriptions[4]);

        } else if(currentDirectory.equals("rosewine")) {
            titleTextView.setText(titles[3]);
            iconImageView.setImageResource(R.drawable.ic_drinktype_rosewine);

            leftButton = (Button) findViewById(R.id.btn_left);
            leftButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mealtype_vegan, 0 ,0);
            leftButton.setText(R.string.mealtype_vegan);
            leftButtonListener(descriptions[5]);

        } else if(currentDirectory.equals("whiskey")) {
            titleTextView.setText(titles[4]);
            iconImageView.setImageResource(R.drawable.ic_drinktype_whiskey);

            leftButton = (Button) findViewById(R.id.btn_left);
            leftButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mealtype_beef, 0 ,0);
            leftButton.setText(R.string.mealtype_beef);
            leftButtonListener(descriptions[6]);

        }
    }

    private void leftButtonListener(final String inputString){
        descriptionTextView.setText(inputString);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descriptionTextView.setText(inputString);
            }
        });
    }

    private void rightButtonListener(final String inputString){
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descriptionTextView.setText(inputString);
            }
        });
    }
}