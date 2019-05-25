package com.quelotic.mealcounter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.CustomViewHolder> {

    private List<Meal> meals;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView id, date, plates;

        public CustomViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.id);
            date = view.findViewById(R.id.date);
            plates = view.findViewById(R.id.plates);
        }
    }

    public MealsAdapter(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meals, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Meal item = meals.get(position);
        holder.id.setText(String.valueOf(item.getId()));
        holder.date.setText(item.getDate());
        holder.plates.setText(String.valueOf(item.getPlates()));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

}