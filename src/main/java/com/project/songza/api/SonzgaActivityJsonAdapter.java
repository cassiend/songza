package com.project.songza.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;

public class SonzgaActivityJsonAdapter implements JsonDeserializer<SongzaActivity> {

    public static final String NAME = "name";

    @Override
    public SongzaActivity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return new SongzaActivity(json.getAsJsonObject().get(NAME).getAsString());
    }
}
