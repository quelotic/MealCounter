package com.quelotic.mealcounter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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