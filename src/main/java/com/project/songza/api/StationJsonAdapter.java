package com.project.songza.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

public class StationJsonAdapter implements JsonDeserializer<Station> {

    public static final String NAME = "name";

    @Override
    public Station deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject asJsonObject = json.getAsJsonObject();
        String title = asJsonObject.get(NAME).getAsString();
        return new Station(title);
    }
}
