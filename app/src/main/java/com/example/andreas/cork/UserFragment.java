package com.example.andreas.cork;

import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

/**
 * Created by andreas on 11/06/2020.
 */

public class UserFragment extends Fragment {
    final String TAG = "CORK_N_FORK";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        final Button logOut = (Button) view.findViewById(R.id.logoutButton);
        Button myFavorites = (Button) view.findViewById(R.id.myFavorites);
        final TextView usernameDisplay = (TextView) view.findViewById(R.id.usernameTextView);

        //get username

        Log.d(TAG, "username from preference: "+PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("username", "")+ " at activity: " + getActivity().toString());
        usernameDisplay.setText(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("username", ""));
            /*DocumentReference docRef = db.collection("users").document(mAuth.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()){
                            Log.d(TAG, "Username found");
                            usernameDisplay.setText(document.get("username").toString());
                        }
                        else{
                            Log.d(TAG, "No such document");
                        }
                    }else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });*/


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sign out
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();

                Toast.makeText(getActivity(), "You have been logged out.",
                        Toast.LENGTH_SHORT).show();
                //go back
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new LoginFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return view;
    }
}
