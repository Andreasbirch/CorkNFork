package com.example.andreas.cork;


import android.os.Bundle;
<<<<<<< HEAD
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
=======

>>>>>>> 246976917caca6aa6ac8909ab411222e8cb82612
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

<<<<<<< HEAD
=======
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

>>>>>>> 246976917caca6aa6ac8909ab411222e8cb82612

/**
 * Created by andreas on 11/06/2020.
 */

public class ChooseWinetypeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_winetypeselect, container, false);

        ImageButton searchDrinks = (ImageButton) view.findViewById(R.id.searchDrinkButton);
        searchDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new SearchFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
