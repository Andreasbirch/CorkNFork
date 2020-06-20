package com.example.andreas.cork;

import android.os.Bundle;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andreas on 11/06/2020.
 */

public class SignUpFragment extends Fragment {
    final String TAG = "CORK_N_FORK";
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public void signUp(String email, String password, final String username){



        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            //create username in cloud
                            Map<String, Object> data = new HashMap<>();
                            data.put("username", username);
                            data.put("isPescetarian", false);
                            data.put("isVegan", false);
                            data.put("isAllergicToShellfish", false);
                            data.put("searchPreference", 0);

                            db.collection("users").document(mAuth.getUid()).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "Username successfully written!");

                                    // go to login page and reset current user.
                                    mAuth.signOut();

                                    Toast.makeText(getActivity(), "User created successfully.",
                                            Toast.LENGTH_SHORT).show();
                                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, new LoginFragment());
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();
                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error writing user", e);

                                            Toast.makeText(getActivity(), "Error occurred, try agian.",
                                                    Toast.LENGTH_SHORT).show();
                                            //delete user from auth
                                            mAuth.getCurrentUser().delete();
                                        }
                                    });

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       final View view =  inflater.inflate(R.layout.fragment_signup, container, false);

        final EditText emailText = (EditText) view.findViewById(R.id.editTextTextEmailAddress);
        final EditText passwordText = (EditText) view.findViewById(R.id.editTextTextPassword);
        final EditText usernameText = (EditText) view.findViewById(R.id.editTextTextUsername);

        Button signUp = (Button) view.findViewById(R.id.signUp);
        Button loginHere = (Button) view.findViewById(R.id.goToLogin);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameText.getText().toString();
                final String password = passwordText.getText().toString();
                final String email = emailText.getText().toString();
                Log.d(TAG, username + password + email);
                if (!username.equals("") && !passwordText.equals("") && !emailText.equals("")){
                    //username check
                    db.collection("users")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        boolean usernameCheck = true;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d(TAG, document.getId() + " => " + document.getData());
                                            if(document.getData().get("username").equals(username)){
                                                usernameCheck = false;
                                                //message user
                                                Toast.makeText(getActivity(), "Username taken.",
                                                        Toast.LENGTH_SHORT).show();
                                                break;
                                            }
                                        }
                                        if (usernameCheck){
                                            signUp(email, password, username);
                                        }
                                    } else {
                                        Log.w(TAG, "Error user data.", task.getException());
                                    }
                                }
                            });

                }
            }
        });
        loginHere.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new LoginFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return view;
    }
}
