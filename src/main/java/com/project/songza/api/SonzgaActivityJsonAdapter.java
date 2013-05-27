package com.project.songza.api;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class SonzgaActivityJsonAdapter implements JsonDeserializer<SongzaActivity> {

    public static final String NAME = "name";
    public static final String STATION_IDS = "station_ids";

    @Override
    public SongzaActivity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject asJsonObject = json.getAsJsonObject();
        String name = asJsonObject.get(NAME).getAsString();

        JsonArray stationIdsJson = asJsonObject.get(STATION_IDS).getAsJsonArray();
        List<String> stationIds = newArrayList();
        for(JsonElement stationName : stationIdsJson){
            stationIds.add(stationName.getAsString());
        }
        return new SongzaActivity(name, stationIds);
    }
}
