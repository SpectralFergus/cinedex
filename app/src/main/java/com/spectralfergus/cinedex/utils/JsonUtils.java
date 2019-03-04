package com.spectralfergus.cinedex.utils;

import com.spectralfergus.cinedex.data.Flick;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    // Parses a JSONObject of Flicks into a List

    public static List<Flick> parseFlicksFromJson(String jsonString) throws JSONException, IOException {
        JSONObject flicksJSON = new JSONObject(jsonString);
        JSONArray jsonArray = flicksJSON.getJSONArray("results");
        List<Flick> parsedFlicks = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonFlick = jsonArray.getJSONObject(i);
            parsedFlicks.add(pluckJsonFlick(jsonFlick));
        }

        return parsedFlicks;
    }

    // Converts JSONObject -> Flick
    private static Flick pluckJsonFlick(JSONObject flick) throws JSONException {
        return new Flick(
                flick.getInt("vote_count"),
                flick.getInt("id"),
                flick.getBoolean("video"),
                flick.getDouble("vote_average"),
                flick.getString("title"),
                flick.getDouble("popularity"),
                flick.getString("poster_path"),
                flick.getString("original_language"),
                flick.getString("original_title"),
                flick.getJSONArray("genre_ids").toString(),
                flick.getString("backdrop_path"),
                flick.getBoolean("adult"),
                flick.getString("overview"),
                flick.getString("release_date")
        );
    }
}
