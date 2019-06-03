package com.quelotic.mealcounter.databases;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface MealDao {

    @Insert
    void insert(Meal meal);

}
