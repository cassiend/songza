package com.project.songza.api;

import com.google.gson.*;
import com.project.songza.domain.Station;

import java.lang.reflect.Type;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class StationJsonAdapter implements JsonDeserializer<Station> {

    public static final String NAME = "name";
    public static final String URL = "cover_url";
    public static final String FEATURED_ARTISTS = "featured_artists";

    @Override
    public Station deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject asJsonObject = json.getAsJsonObject();
        String title = asJsonObject.get(NAME).getAsString();
        String url = asJsonObject.get(URL).getAsString();

        JsonArray asJsonArray = asJsonObject.get(FEATURED_ARTISTS).getAsJsonArray();

        List<String> artistNames = newArrayList();
        for(int i = 0; i < asJsonArray.size(); i++){
            JsonObject artistName = asJsonArray.get(i).getAsJsonObject();
            artistNames.add(artistName.get("name").getAsString());
        }
        return new Station(title, url, artistNames);
    }
}
