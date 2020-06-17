package com.example.andreas.cork;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by andreas on 11/06/2020.
 */

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Spinner selectSort = (Spinner) view.findViewById(R.id.selectsort);

        ArrayAdapter<CharSequence> selectSortAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.select_sort_items,
                android.R.layout.simple_spinner_item
        );

        selectSortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectSort.setAdapter(selectSortAdapter);



        return view;
    }
}
