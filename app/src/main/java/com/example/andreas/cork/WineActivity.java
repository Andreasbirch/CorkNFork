package com.example.andreas.cork;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import temporary_datebase.Wine;
import temporary_datebase.WineDatabase;

public class WineActivity extends AppCompatActivity {

    WineDatabase wineDatabase;
    TextView titleWineTextView;
    TextView wineDescriptionText;
    ImageView wineImageView;
    RatingBar wineRatingBar;
    Button wineBtn;
    Wine wine;
    boolean phoneHasRatedBefore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);
        wineDatabase = WineDatabase.getInstance();

        titleWineTextView = (TextView) findViewById(R.id.titleWineTextView);
        wineDescriptionText = (TextView) findViewById(R.id.wineDescriptionTextView);
        wineImageView = (ImageView) findViewById(R.id.wineImageView);
        wineRatingBar = (RatingBar) findViewById(R.id.wineRatingBar);
        wineBtn = (Button) findViewById(R.id.wineBtn);

        String wineTitle = getIntent().getExtras().getString("com.example.andreas.cork.WINE");
        wine = wineDatabase.getWine(wineTitle);

        if(wine != null) {
            titleWineTextView.setText(wineTitle);
            wineDescriptionText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ultricies felis ipsum, nec suscipit purus tempor at. ");
            wineImageView.setImageResource(wine.img);
            wineRatingBar.setRating(wine.rating);
        }
    }

    public void onClickBtn(View v) {
        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        if(!phoneHasRatedBefore) {
            wine.addRatingToWine(wineRatingBar.getRating());
            wineRatingBar.setRating(wine.rating);

            phoneHasRatedBefore=true;
            wineRatingBar.setIsIndicator(true);
            wine.addPhoneIdentifier(androidId);
            wineBtn.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Thanks for rating this wine", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "This device has already voted for this wine", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        if(wine.searchIdentifiers(androidId)) {
            phoneHasRatedBefore = true;
            wineRatingBar.setIsIndicator(true);
            wineBtn.setVisibility(View.INVISIBLE);
        }
    }
}