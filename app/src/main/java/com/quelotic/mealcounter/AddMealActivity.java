package com.quelotic.mealcounter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddMealActivity extends AppCompatActivity {

    public static final String MEAL_ADDED = "new meal";

    private EditText date;

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_meal);

        date = findViewById(R.id.date);
        Button button = findViewById(R.id.add_meal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();

                if (TextUtils.isEmpty(date.toString())) {
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String meal = date.toString();
                    resultIntent.putExtra(MEAL_ADDED, meal);
                    setResult(RESULT_OK, resultIntent);
                }
                finish();
            }
        });
    }

    public void showDatePicker(View view) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        date.setText(dayOfMonth + R.string.slash + (monthOfYear + 1) + R.string.slash + year);

                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }

}
