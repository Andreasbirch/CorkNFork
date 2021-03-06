package com.example.andreas.cork;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.WriteResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;


/**
 * Created by andreas on 11/06/2020.
 */

public class LoginFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    final String TAG = "CORK_N_FORK";

    public void userLogin(String email, String password) {
        Log.d(TAG, "signIn:" + email);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, and go to userfragment
                            Log.d(TAG, "signInWithEmail:success");

                            DocumentReference docRef = db.collection("users").document(mAuth.getUid());
                            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()){
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()){
                                            Log.d(TAG, "Username found: "+ document.get("username").toString() + " at activity: " + getActivity().toString());
                                            PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("username", document.get("username").toString()).commit();


                                        }
                                        else{
                                            Log.d(TAG, "No such document");
                                        }

                                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                        fragmentTransaction.replace(R.id.fragment_container, new UserFragment());
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.commit();
                                    }

                                    else {
                                        Log.d(TAG, "get failed with ", task.getException());
                                    }
                                }
                            });


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), R.string.toast_authentication_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button signUpHere = (Button) view.findViewById(R.id.goToSignUp);
        Button login = (Button) view.findViewById(R.id.login);
        final EditText email = (EditText) view.findViewById(R.id.editTextTextEmailAddress);
        final EditText password = (EditText) view.findViewById(R.id.editTextTextPassword);
        signUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new SignUpFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Try log in
               if (mAuth.getCurrentUser() == null){
                   if (!email.getText().toString().equals("") && !password.getText().toString().equals("")){
                       userLogin(email.getText().toString(), password.getText().toString());
                   }
               }
               else Toast.makeText(getActivity(), R.string.toast_already_logged_in,
                       Toast.LENGTH_SHORT).show();

            }
        });

        return view;

    }


}
