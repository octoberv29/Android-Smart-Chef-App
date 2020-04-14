package com.example.android.smartchefapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.smartchefapp.utilities.NetworkUtils;
import com.example.android.smartchefapp.utilities.OpenRecipeJsonUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mAllRecipesTextView;
    private EditText mQueryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAllRecipesTextView = findViewById(R.id.text_all_recipes);
        mQueryEditText = findViewById(R.id.edit_query);

    }

    private void searchQuery() {
        String toQuery = mQueryEditText.getText().toString();
        new RecipeQueryTask().execute(toQuery);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            searchQuery();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private class RecipeQueryTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {

            if (params.length == 0) return null;

            String toQuery = params[0];
            URL recipeRequestUrl = NetworkUtils.buildUrl(toQuery);

            try {
                String jsonRecipeResponse = NetworkUtils.getResponseFromHttpUrl(recipeRequestUrl);

                String[] simpleJsonRecipeData = OpenRecipeJsonUtils
                        .getSimpleRecipeStringsFromJson(MainActivity.this, jsonRecipeResponse);

                return simpleJsonRecipeData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] recipeData) {
            if (recipeData != null) {
                for (String item : recipeData) {
                    mAllRecipesTextView.append(item + "\n\n\n");
                }
            }
        }
    }


}
