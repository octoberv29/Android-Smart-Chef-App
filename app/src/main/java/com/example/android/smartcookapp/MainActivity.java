package com.example.android.smartcookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mAllRecipesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAllRecipesTextView = findViewById(R.id.text_all_recipes);

        String[] dummyData = {
                "Pizza",
                "Burger"
        };

        for (String data : dummyData) {
            mAllRecipesTextView.append(data + "\n\n\n");
        }
    }
}
