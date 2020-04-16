package com.example.android.smartchefapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private String[] mRecipeItems;
    private Context mContext;

    public RecipeAdapter() { }

    // Inflate a layout from XML and return the holder
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        // Inflate the custom layout
        View recipeView = inflater.inflate(R.layout.list_item, parent, false);

        // Return a new holder instance
        RecipeViewHolder viewHolder = new RecipeViewHolder(recipeView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        String recipe = mRecipeItems[position];
        holder.mRecipeNameTextView.setText(recipe);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        if (mRecipeItems == null) return 0;
        return mRecipeItems.length;
    }

    // instead of using constructor
    public void setRecipeData(String[] recipeData) {
        mRecipeItems = recipeData;
        notifyDataSetChanged();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable for any view that will be set as you render a row
        private TextView mRecipeNameTextView;

        // We also create a constructor that accepts the entire item row and does the view lookups to find each subview
        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            mRecipeNameTextView = itemView.findViewById(R.id.text_recipe_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                        Toast.makeText(mContext, mRecipeNameTextView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
