package com.quelotic.mealcounter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class LogDialog extends AppCompatDialogFragment {

    private List<Meal> meals = new ArrayList<>();
    private RecyclerView recyclerView;
    private MealsAdapter mAdapter;
    protected LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.log, container, false);
        recyclerView = view.findViewById(R.id.rview);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        mAdapter = new MealsAdapter(meals);
        recyclerView.setAdapter(mAdapter);

        populateMealsDetails();
        mAdapter.notifyDataSetChanged();
        return view;

    }
    private void populateMealsDetails() {

//        InputStream inputStream = getResources().openRawResource(R.raw.data);
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(inputStream, Charset.forName("UTF-8"))
//        );
//
//        String line = "";
//
//        try {
//            while ((line = reader.readLine()) != null) {
//                // Split by commas
//                String[] tokens = line.split(",");
//                // Read the data
//                Meal meal = new Meal();
//                meal.setId(Integer.parseInt(tokens[0]));
//                meal.setDate(tokens[1]);
//                meal.setPlates(Integer.parseInt(tokens[2]));
//                meals.add(meal);
//            }
//        } catch (IOException e) {
//            Log.wtf("MealCounter", "Error reading data file on line: " + line, e);
//            e.printStackTrace();
//        }
    }
}
