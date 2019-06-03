package com.quelotic.mealcounter.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Meal.class, version = 1, exportSchema = false)
public abstract class MealRoomDatabase extends RoomDatabase {

    public abstract MealDao mealDao();

    private static volatile MealRoomDatabase mealRoomInstance;

    static MealRoomDatabase getDatabase(final Context context){
        if (mealRoomInstance == null){
            synchronized (MealRoomDatabase.class){
                if (mealRoomInstance == null) {
                    mealRoomInstance = Room.databaseBuilder(context.getApplicationContext(), MealRoomDatabase.class, "meal_database").build();
                }
            }
        }
        return mealRoomInstance;
    }

}
