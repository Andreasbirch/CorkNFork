package com.example.andreas.cork;

import android.os.Bundle;
<<<<<<< HEAD
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
=======

>>>>>>> 246976917caca6aa6ac8909ab411222e8cb82612
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

<<<<<<< HEAD
=======
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

>>>>>>> 246976917caca6aa6ac8909ab411222e8cb82612
public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searchwine, container, false);
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        String[] drinks = new String[]{"Specific redwine 1", "Specific redwine 2", "Whiskey", "Schnapps"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,drinks);
        autoCompleteTextView.setAdapter(adapter);

        return view;
    }


}
