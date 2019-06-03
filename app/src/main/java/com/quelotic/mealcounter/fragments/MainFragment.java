package com.quelotic.mealcounter.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quelotic.mealcounter.R;

public class MainFragment extends Fragment {

    TextView remaining;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        remaining = view.findViewById(R.id.remaining);

        // sql and subtract
        int remainingPlates = 5;
        remaining.setText(String.valueOf(remainingPlates));

        return view;
    }
}