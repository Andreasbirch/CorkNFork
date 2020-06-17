package com.example.andreas.cork;

import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import temporary_datebase.Wine;
import temporary_datebase.WineDatabase;

public class WineActivity extends AppCompatActivity {

    WineDatabase wineDatabase;
    TextView titleWineTextView;
    TextView wineDescriptionText;
    ImageView wineImageView;
    RatingBar wineRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);
        wineDatabase = WineDatabase.getInstance();

        titleWineTextView = (TextView) findViewById(R.id.titleWineTextView);
        wineDescriptionText = (TextView) findViewById(R.id.wineDescriptionTextView);
        wineImageView = (ImageView) findViewById(R.id.wineImageView);
        wineRatingBar = (RatingBar) findViewById(R.id.wineRatingBar);

        String wineTitle = getIntent().getExtras().getString("com.example.andreas.cork.WINE");
        Wine wine = wineDatabase.getWine(wineTitle);

        if(wine != null) {
            titleWineTextView.setText(wineTitle);
            wineDescriptionText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ultricies felis ipsum, nec suscipit purus tempor at. ");
            wineImageView.setImageResource(wine.img);
            wineRatingBar.setRating(wine.rating);
        }


    }
}