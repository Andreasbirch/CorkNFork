package com.example.andreas.cork;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firestore.v1.WriteResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andreas on 11/06/2020.
 */

public class SettingsFragment extends Fragment {
    final String TAG = "CORK_N_FORK";
    public boolean isVeganBool;
    public boolean isPescetarianBool;
    public boolean isAllergicToShellfishBool;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        final Switch isVeganSwitch = (Switch) view.findViewById(R.id.isVeganSwitch);
        final Switch isPescetarianSwitch = (Switch) view.findViewById(R.id.isPescetarianSwitch);
        final Switch isAllergicToShellfishSwitch = (Switch) view.findViewById(R.id.isAllergicToShellfishSwitch);

        final Spinner selectSort = (Spinner) view.findViewById(R.id.selectsort);



        ArrayAdapter<CharSequence> selectSortAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.select_sort_items,
                android.R.layout.simple_spinner_item
        );

        selectSortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectSort.setAdapter(selectSortAdapter);


        //Populate fields
        DocumentReference docRef = db.collection("users").document(mAuth.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                        Log.d(TAG, "Username found");
                        isVeganSwitch.setChecked((boolean) document.get("isVegan"));
                        isPescetarianSwitch.setChecked((boolean) document.get("isPescetarian"));
                        isAllergicToShellfishSwitch.setChecked((boolean) document.get("isAllergicToShellfish"));
                        selectSort.setSelection(Integer.parseInt(document.get("searchPreference").toString()));

                    }
                    else{
                        Log.d(TAG, "No such document");
                    }
                }else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


        isVeganSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isVeganBool = b;
            }
        });
        isPescetarianSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isPescetarianBool = b;
            }
        });
        isAllergicToShellfishSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isAllergicToShellfishBool = b;
            }
        });


        return view;
    }

    @Override
    public void onPause() {

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final Spinner selectSort = (Spinner) getView().findViewById(R.id.selectsort);
        int searchPreferenceIndex = (int) selectSort.getSelectedItemId();

        Map<String, Object> docData = new HashMap<>();
        docData.put("isVegan", isVeganBool);
        docData.put("isPescetarian", isPescetarianBool);
        docData.put("isAllergicToShellfish", isAllergicToShellfishBool);
        docData.put("searchPreference", searchPreferenceIndex);

        db.collection("users").document(mAuth.getUid()).set(docData, SetOptions.merge());


        super.onPause();
    }
}
