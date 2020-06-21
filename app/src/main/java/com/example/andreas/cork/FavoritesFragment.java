package com.example.andreas.cork;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import temporary_datebase.Drink;

public class FavoritesFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        final ListView myListView = (ListView) view.findViewById(R.id.mListViewFav);
        final String TAG = "CORK_N_FORK";

        super.onCreate(savedInstanceState);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();






        //Get list of favorites s
        db.collection("users").document(mAuth.getUid()).collection("favorites").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<Drink> favorites = new  ArrayList<Drink>();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        favorites.add(document.toObject(Drink.class));
                        Log.d(TAG, "name " +document.toObject(Drink.class).getName());
                    }

                    MealActivitiyAdapter mealActivitiyAdapter = new MealActivitiyAdapter(getContext(),favorites);
                    myListView.setAdapter(mealActivitiyAdapter);

                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });



        return view;
    }
}
