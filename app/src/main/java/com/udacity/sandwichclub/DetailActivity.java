package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView alsoKnownAsTv = findViewById(R.id.also_known_tv);
        TextView descriptionTv = findViewById(R.id.description_tv);
        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        TextView placeOfOriginTv = findViewById(R.id.origin_tv);
        TextView imageTitleTv = findViewById(R.id.image_title_text_view);

        //Im using TextUtils.join as alternative to proper java String.join

        if (sandwich.getAlsoKnownAs().size()>0){
        alsoKnownAsTv.setText(TextUtils.join(", ", sandwich.getAlsoKnownAs()));
        }else {
            alsoKnownAsTv.setText(R.string.not_available);
        }

        if (sandwich.getDescription().length()>0){
            descriptionTv.setText(sandwich.getDescription());
        }else {
            descriptionTv.setText(R.string.not_available);
        }

        if(sandwich.getIngredients().size()>0){
            ingredientsTv.setText(TextUtils.join(", ", sandwich.getIngredients()));
        }else{
            ingredientsTv.setText(R.string.not_available);
        }

        if (sandwich.getPlaceOfOrigin().length()>0){
            placeOfOriginTv.setText(sandwich.getPlaceOfOrigin());
        }else{
            placeOfOriginTv.setText(R.string.not_available);
        }

        if(sandwich.getMainName().length()>0){
            imageTitleTv.setText(sandwich.getMainName());
        }else{
            imageTitleTv.setText(R.string.not_available);
        }


    }
}
