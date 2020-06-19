package com.example.andreas.cork;

import android.graphics.drawable.Drawable;
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
    TextView wineDescriptionText;
    ImageView wineImageView;
    RatingBar wineRatingBar;
    Button wineBtn;
    Wine wine;
    boolean phoneHasRatedBefore = false;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                                                wine.setRating(rating);
                                                wines.put("rating", rating);
                                                wines.put("name", wine.getName());
                                                wines.put("img", wine.getImg());
                                                wines.put("type", wine.getType());
                                                myRef.child(wine.getName()).setValue(wines);


                                                for (int i = 0; i <20; i++){
                                                    Map<String, Object> winex = new HashMap<>();
                                                    winex.put("rating", rating);
                                                    winex.put("name", "wine" + i);
                                                    winex.put("img", R.drawable.ic_winetype_redwine);
                                                    winex.put("type", "red");
                                                    myRef.child("wine" + i).setValue(winex);
                                                }
                                                for (int i = 0; i <20; i++){
                                                    Map<String, Object> winex = new HashMap<>();
                                                    winex.put("rating", rating);
                                                    winex.put("name", "wine" + i+"0");
                                                    winex.put("img", R.drawable.ic_winetype_whitewine);
                                                    winex.put("type", "white");
                                                    myRef.child("wine" + i+"0").setValue(winex);
                                                }
                                                for (int i = 0; i <20; i++){
                                                    Map<String, Object> winex = new HashMap<>();
                                                    winex.put("rating", rating);
                                                    winex.put("name", "wine" + i + "00");
                                                    winex.put("img", (R.drawable.wine_fletris));
                                                    winex.put("type", "rosé");
                                                    myRef.child("wine" + i+ "00").setValue(winex);
                                                }
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

    public void onClickBtn(View v) {

        float rating = wineRatingBar.getRating();
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
            Toast.makeText(this, "You have to be logged in to submit a review", Toast.LENGTH_LONG).show();
        }



       /* if(!phoneHasRatedBefore) {
            wine.addRatingToWine(wineRatingBar.getRating());
            wineRatingBar.setRating(wine.rating);

            phoneHasRatedBefore=true;
            wineRatingBar.setIsIndicator(true);
            wine.addPhoneIdentifier(androidId);
            wineBtn.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Thanks for rating this wine", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "This device has already voted for this wine", Toast.LENGTH_LONG).show();
        }*/
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