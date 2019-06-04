package com.quelotic.mealcounter.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.quelotic.mealcounter.AddMealActivity;
import com.quelotic.mealcounter.R;
import com.quelotic.mealcounter.databases.Meal;
import com.quelotic.mealcounter.databases.MealViewModel;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class LogFragment extends Fragment {

    private static final int NEW_MEAL_ACTIVITY_REQUEST_CODE = 1;
    private String TAG = this.getClass().getSimpleName();
    private MealViewModel mealViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_log, container, false);

        Button addButton = rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddMealActivity.class);
                startActivityForResult(intent, NEW_MEAL_ACTIVITY_REQUEST_CODE);
            }
        });

        mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_MEAL_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            Meal meal = new Meal(data.getStringExtra(AddMealActivity.MEAL_ADDED));
            mealViewModel.insert(meal);

            Toast.makeText(
                    Objects.requireNonNull(getActivity()).getApplicationContext(),
                    R.string.saved,
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    Objects.requireNonNull(getActivity()).getApplicationContext(),
                    R.string.notSaved,
                    Toast.LENGTH_LONG).show();
        }
    }
}