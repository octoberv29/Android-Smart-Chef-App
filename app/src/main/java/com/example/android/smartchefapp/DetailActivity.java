package com.example.android.smartchefapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private String mRecipe;
    private TextView mRecipeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mRecipeTextView = findViewById(R.id.text_display_recipe);

        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra(Intent.EXTRA_TEXT)) {
                mRecipe = intent.getStringExtra(Intent.EXTRA_TEXT);
                mRecipeTextView.setText(mRecipe);
            }
        }
    }
}
