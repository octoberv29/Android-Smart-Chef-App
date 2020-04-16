package com.example.android.smartchefapp.utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class OpenRecipeJsonUtils {

    // Strings for extracting JSON Objects, Arrays, etc.
    private static final String RESULTS = "results";
    private static final String ID = "id";
    private static final String TITLE = "title";

    // https://api.spoonacular.com/recipes/search
    public static String[] getSimpleRecipeStringsFromJson(Context context, String recipeJsonString) throws JSONException {
        JSONObject root = new JSONObject(recipeJsonString);
        JSONArray results = root.getJSONArray(RESULTS);

        String[] parsedRecipeData = new String[results.length()];
        long id;
        String title;

        for (int i=0; i<results.length(); i++) {
            JSONObject currentRecipe = results.getJSONObject(i);
            id = currentRecipe.getLong(ID);
            title = currentRecipe.getString(TITLE);

            parsedRecipeData[i] = title;
        }
        return parsedRecipeData;
    }
}
