package com.udacity.sandwichclub.utils;

import android.widget.Toast;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject("name");

            String mainName = name.getString("mainName");

            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList();
            int mainNameArrayLength = alsoKnownAsArray.length();
            if (mainNameArrayLength != 0){
                for (int i = 0; i < mainNameArrayLength; i++){
                    alsoKnownAs.add(alsoKnownAsArray.get(i).toString());
                }
            } else {
                alsoKnownAs.add("No other names");
            }

            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            if (placeOfOrigin.equals("")){
                placeOfOrigin = "Location was not found";
            }

            String description = jsonObject.getString("description");

            String image = jsonObject.getString("image");

            JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList();
            int ingredientsLength = ingredientsArray.length();
            if (ingredientsLength != 0){
                for (int i = 0; i < ingredientsLength; i++){
                    ingredients.add(ingredientsArray.get(i).toString());
                }
            } else {
                ingredients.add("Ingredients not present.");
            }


            Sandwich result = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            return result;


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
