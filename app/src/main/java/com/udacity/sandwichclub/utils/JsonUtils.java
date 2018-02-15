package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

         Sandwich sandwich = new Sandwich();
         String mainName;
         ArrayList<String> ingredientList = new ArrayList<>();
         String placeOfOrigin;
         String description;
         String image;
         ArrayList<String> alsoKnownAsList = new ArrayList<>();


         if (json != null){
             try{
                 //Retrieve data from JSON
                 JSONObject jsonObject = new JSONObject(json);
                 JSONObject nameObject = jsonObject.getJSONObject("name");
                 mainName = nameObject.getString("mainName");

                 JSONArray alsoKnownAs = nameObject.getJSONArray("alsoKnownAs");
                 for (int i = 0; i < alsoKnownAs.length(); i++){
                     alsoKnownAsList.add(alsoKnownAs.getString(i));
                 }

                 placeOfOrigin = jsonObject.getString("placeOfOrigin");
                 description = jsonObject.getString("description");
                 image = jsonObject.getString("image");

                 JSONArray ingredients = jsonObject.getJSONArray("ingredients");
                 for (int i = 0; i < ingredients.length(); i++){
                     ingredientList.add(ingredients.getString(i));
                 }

                 //Save retrieved data to Sandwich object
                 sandwich.setMainName(mainName);
                 sandwich.setIngredients(ingredientList);
                 sandwich.setPlaceOfOrigin(placeOfOrigin);
                 sandwich.setDescription(description);
                 sandwich.setImage(image);
                 sandwich.setAlsoKnownAs(alsoKnownAsList);

             }catch (JSONException e) {
                 e.printStackTrace();
             }
         }
        return sandwich;
    }
}
