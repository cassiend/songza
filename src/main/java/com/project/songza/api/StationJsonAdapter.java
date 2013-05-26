package com.project.songza.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

public class StationJsonAdapter implements JsonDeserializer<Station> {

    public static final String ID = "id";

    @Override
    public Station deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject object = json.getAsJsonObject();
        Station station = null;
        station.setId(object.get(ID).getAsLong());
        return station;
    }
}
