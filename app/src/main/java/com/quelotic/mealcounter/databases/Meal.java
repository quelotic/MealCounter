package com.quelotic.mealcounter.databases;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meals")
public class Meal {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    //@ColumnInfo(name = "date")
    private String date;

    public Meal(String date) {
        this.date = date;
    }

    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public String getDate() {
        return this.date;
    }

    public void setId(int id) {
        this.id = id;
    }
}
