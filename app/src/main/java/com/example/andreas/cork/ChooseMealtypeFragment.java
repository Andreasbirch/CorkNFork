package com.example.andreas.cork;

import android.content.Intent;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Table;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Set;

import javax.xml.datatype.Duration;

/**
 * Created by andreas on 11/06/2020.
 */

public class ChooseMealtypeFragment extends Fragment {
    final String TAG = "CORK_N_FORK";
    Button selectBeef;
    Button selectPork;
    Button selectPoultry;
    Button selectVegan;
    Button selectFish;
    Button selectShellfish;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mealtypeselect, container, false);
       // final Switch isVegan = (Switch) v.findViewById(R.id.isVeganSwitch);
       // final Switch isPescetarian = (Switch) v.findViewById(R.id.isPescetarianSwitch);
       // final Switch isAllergicToShellfish = (Switch) v.findViewById(R.id.isAllergicToShellfishSwitch);

        selectFish = (Button) v.findViewById(R.id.select_fish);
        selectBeef = (Button) v.findViewById(R.id.select_beef);
        selectPork = (Button) v.findViewById(R.id.select_pork);
        selectPoultry = (Button) v.findViewById(R.id.select_poultry);
        selectShellfish = (Button) v.findViewById(R.id.select_shellfish);
        selectVegan = (Button) v.findViewById(R.id.select_vegan);
        TableLayout tl = (TableLayout) v.findViewById(R.id.tableLayout);
        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nothing, so error doesnt occour when pressing background
            }
        });
       if (PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean( "isVegan", false)){
            selectBeef.setVisibility(View.INVISIBLE);
            selectFish.setVisibility(View.INVISIBLE);
            selectPork.setVisibility(View.INVISIBLE);
            selectPoultry.setVisibility(View.INVISIBLE);
            selectShellfish.setVisibility(View.INVISIBLE);
           selectBeef.setEnabled(false);
           selectPork.setEnabled(false);
           selectPoultry.setEnabled(false);
           selectShellfish.setEnabled(false);
           selectFish.setEnabled(false);

           selectBeef.setClickable(false);
           selectPork.setClickable(false);
           selectPoultry.setClickable(false);
           selectShellfish.setClickable(false);
           selectFish.setClickable(false);
        }
        if (PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean( "isPescetarian", false)){
            selectBeef.setVisibility(View.INVISIBLE);
            selectPork.setVisibility(View.INVISIBLE);
            selectPoultry.setVisibility(View.INVISIBLE);
            selectBeef.setEnabled(false);
            selectPork.setEnabled(false);
            selectPoultry.setEnabled(false);

            selectFish.setClickable(false);
        }
        if (PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean( "isAllergicToShellfish", false)){
            selectShellfish.setVisibility(View.INVISIBLE);
            selectShellfish.setEnabled(false);
            selectFish.setClickable(false);
        }



        //Populate fields
       /* DocumentReference docRef = db.collection("users").document(mAuth.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                        Log.d(TAG, "Username found");
                        if((boolean) document.get("isVegan")){
                            selectBeef.setVisibility(View.INVISIBLE);
                            selectFish.setVisibility(View.INVISIBLE);
                            selectPork.setVisibility(View.INVISIBLE);
                            selectPoultry.setVisibility(View.INVISIBLE);
                            selectShellfish.setVisibility(View.INVISIBLE);
                        } else if((boolean) document.get("isPescetarian")){
                            selectBeef.setVisibility(View.INVISIBLE);
                            selectPork.setVisibility(View.INVISIBLE);
                            selectPoultry.setVisibility(View.INVISIBLE);
                        }
                        if((boolean) document.get("isAllergicToShellfish")){
                            selectShellfish.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        Log.d(TAG, "No such document");
                    }
                }else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });*/








        selectBeef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "cow");
                startActivity(startIntent);
            }
        });

        selectPork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "pork");
                startActivity(startIntent);
            }
        });

        selectPoultry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "chicken");
                startActivity(startIntent);
            }
        });

        selectVegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "vegan");
                startActivity(startIntent);
            }
        });

        selectFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "fish");
                startActivity(startIntent);
            }
        });

        selectShellfish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), MealActivity.class);
                startIntent.putExtra("com.example.andreas.cork.MEAL_TYPE", "shellfish");
                startActivity(startIntent);
            }
        });

        return v;

    }


}