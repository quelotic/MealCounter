package com.quelotic.mealcounter.databases;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MealViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private MealDao mealDao;
    private MealRoomDatabase mealDB;

    public MealViewModel(@NonNull Application application) {
        super(application);

        mealDB = MealRoomDatabase.getDatabase(application);
        mealDao = mealDB.mealDao();
    }

    public void insert(Meal meal) {
        new InsertAsyncTask(mealDao).execute(meal);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG,"ViewModel Destroyed");
    }

    private class InsertAsyncTask extends AsyncTask<Meal, Void, Void> {

        MealDao mealDao;

        public InsertAsyncTask(MealDao mealDao) {
            this.mealDao = mealDao;
        }

        @Override
        protected Void doInBackground(Meal... meals) {
            mealDao.insert(meals[0]);
            return null;
        }
    }

}
