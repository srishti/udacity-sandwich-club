package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        final String nameKey = "name";
        final String mainNameKey = "mainName";
        final String alsoKnownAsKey = "alsoKnownAs";

        final String placeOfOriginKey = "placeOfOrigin";
        final String descriptionKey = "description";
        final String imageKey = "image";
        final String ingredientsKey = "ingredients";

        Sandwich sandwich = null;

        String mainName = null;
        List<String> alsoKnownAs = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredients = null;

        JSONObject jsonObject = new JSONObject(json);

        if (!jsonObject.has(nameKey)) {
            throw new JSONException("Could not find key 'name'");
        }

        JSONObject nameJsonObject = jsonObject.getJSONObject(nameKey);

        if (!nameJsonObject.has(mainNameKey)) {
            throw new JSONException("Could not find key 'mainName'");
        }

        mainName = getValue(nameJsonObject, mainNameKey);

        if (!nameJsonObject.has(alsoKnownAsKey)) {
            throw new JSONException("Could not find key 'alsoKnownAs'");
        }

        JSONArray alsoKnownAsArray = nameJsonObject.getJSONArray(alsoKnownAsKey);
        alsoKnownAs = new ArrayList<>();

        for (int i = 0; i < alsoKnownAsArray.length(); i++) {
            alsoKnownAs.add(alsoKnownAsArray.getString(i));
        }

        if (!jsonObject.has(placeOfOriginKey)) {
            throw new JSONException("Could not find key 'placeOfOrigin'");
        }

        placeOfOrigin = getValue(jsonObject, placeOfOriginKey);

        description = getValue(jsonObject, descriptionKey);

        image = getValue(jsonObject, imageKey);

        if (!jsonObject.has(ingredientsKey)) {
            throw new JSONException("Could not find key 'ingredients'");
        }

        JSONArray ingredientsArray = jsonObject.getJSONArray(ingredientsKey);
        ingredients = new ArrayList<>();

        for (int i = 0; i < ingredientsArray.length(); i++) {
            ingredients.add(ingredientsArray.getString(i));
        }

        sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        return sandwich;
    }

    private static String getValue(JSONObject jsonObject, String key) throws JSONException {
        if (!jsonObject.has(key)) {
            throw new JSONException("Could not find key " + "'" + key.replace("Key", "") + "'");
        }

        return jsonObject.getString(key);
    }
}
