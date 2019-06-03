package com.quelotic.mealcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMealActivity extends AppCompatActivity {

    public static final String MEAL_ADDED = "new meal";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        Button button = findViewById(R.id.add_meal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();

                if (TextUtils.isEmpty(editText.getText())){
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String meal = editText.getText().toString();
                    resultIntent.putExtra(MEAL_ADDED, meal);
                    setResult(RESULT_OK, resultIntent);
                }
                finish();
            }
        });


    }
}
