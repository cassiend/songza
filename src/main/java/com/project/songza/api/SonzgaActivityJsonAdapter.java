package com.project.songza.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

public class SonzgaActivityJsonAdapter implements JsonDeserializer<SongzaActivity> {

    public static final String NAME = "name";

    @Override
    public SongzaActivity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject object = json.getAsJsonObject();
        return new SongzaActivity(object.get(NAME).getAsString());
    }
}
