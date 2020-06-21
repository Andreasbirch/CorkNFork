package com.example.andreas.cork;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import temporary_datebase.Wine;
import temporary_datebase.WineDatabase;

import static java.util.logging.Logger.global;

public class WineActivity extends AppCompatActivity {

    final String TAG = "CORK_N_FORK";

    WineDatabase wineDatabase;
    TextView titleWineTextView;
    TextView wineDescriptionText, wineTypeText, wineVolumeText, wineCountryText;
    ImageView wineImageView;
    RatingBar wineRatingBar;
    Button wineBtn;
    Wine wine;
    CheckBox checkboxFavorite;

    boolean phoneHasRatedBefore = false;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);
        wineDatabase = WineDatabase.getInstance();
        checkboxFavorite = findViewById(R.id.checkbox_favorite);

        titleWineTextView = (TextView) findViewById(R.id.titleWineTextView);

        wineDescriptionText = (TextView) findViewById(R.id.wineDescriptionTextView);
        wineTypeText = (TextView) findViewById(R.id.wineTypeTextView);
        wineVolumeText = (TextView) findViewById(R.id.wineVolumeTextView);
        wineCountryText = (TextView) findViewById(R.id.wineCountryTextView);


        wineImageView = (ImageView) findViewById(R.id.wineImageView);
        wineRatingBar = (RatingBar) findViewById(R.id.wineRatingBar);

        String wineTitle = getIntent().getExtras().getString("com.example.andreas.cork.WINE");
        wine = wineDatabase.getWine(wineTitle);




        if(wine != null) {
            titleWineTextView.setText(wineTitle);
            wineDescriptionText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ultricies felis ipsum, nec suscipit purus tempor at. ");
            wineImageView.setImageResource(wine.img);
            wineRatingBar.setRating(wine.rating);
            wineTypeText.setText(wine.type);
            wineVolumeText.setText("75cl");
            wineCountryText.setText(wine.country);

            db.collection("users").document(mAuth.getUid()).collection("favorites").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    if (document.getId().equals(wine.getName())){
                                        checkboxFavorite.setChecked(true);
                                    }
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }




    }

    public void onCheckboxClicked(final View view){
        final boolean checked = ((CheckBox) view).isChecked();
        if (view.getId() == checkboxFavorite.getId()){
            Map<String, Object> data = new HashMap<>();
            data.put("drink", wine); //TODO change to drink
            if (checked){
                //add fav to firestore

                db.collection("users").document(mAuth.getUid()).collection("favorites").document(wine.getName()).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Toast to user.
                        Toast.makeText(WineActivity.this, "Drink added to favorites", Toast.LENGTH_LONG).show();

                    }
                });
            }
            else {
                db.collection("users").document(mAuth.getUid()).collection("favorites").document(wine.getName()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Toast to user.
                        Toast.makeText(WineActivity.this, "Drink removed from favorites", Toast.LENGTH_LONG).show();


                    }
                });
            }

        }
    }




    int count = 0;
    float rating = 0;
    FirebaseDatabase realtime = FirebaseDatabase.getInstance();
    public void updateRating(final Wine wine){
        count = 0;
        rating = 0;


        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (final QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                DocumentReference ref = db.collection("users").document(document.getId()).collection("ratings").document(wine.getName());
                                if (ref != null){
                                    ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if (task.getResult().exists()){
                                                DocumentSnapshot doc = task.getResult();
                                                Log.d(TAG, doc.getId() + " => " + doc.getData()+ "| ratings");
                                                count ++;
                                                rating += ((double)doc.get("rating"));


                                                Log.d(TAG, "Rating set: " + rating + "/" + count);
                                                wineRatingBar.setRating((rating/count));
                                                DatabaseReference myRef = realtime.getReference("drinks");

                                                Map<String, Object> wines = new HashMap<>();
                                                wine.setRating((rating/count));
                                                wines.put("rating", (rating/count));
                                                wines.put("name", wine.getName());
                                                wines.put("img", wine.getImg());
                                                wines.put("type", wine.getType());
                                                myRef.child(wine.getName()).setValue(wines);
                                            }

                                        }
                                    });

                                }
                            }



                            Toast.makeText(WineActivity.this, "Thanks for rating this wine", Toast.LENGTH_LONG).show();

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });



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

    public void setRatingButton(View view){

    }

    public void startRatingPopup(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup_rating,null);
        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        final RatingBar rateableBar = (RatingBar) popupView.findViewById(R.id.popupRatingBar);
        Button saveRatingBtn = (Button) popupView.findViewById(R.id.saveRatingBtn);
        saveRatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float rating = rateableBar.getRating();
                Map<String, Object> data = new HashMap<>();
                data.put("rating", rating);
                data.put("name", wine.getName());

                if (mAuth.getCurrentUser() != null){
                    db.collection("users").document(mAuth.getUid()).collection("ratings").document(wine.getName()).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            updateRating(wine);
                        }
                    });
                }
                else{

                }
                popupWindow.dismiss();
            }
        });
    }


}