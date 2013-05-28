package com.project.songza.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.songza.domain.SongzaActivity;
import com.project.songza.domain.Station;

import java.lang.reflect.Type;

public class JsonHelper {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(SongzaActivity.class, new SonzgaActivityJsonAdapter())
            .registerTypeAdapter(Station.class, new StationJsonAdapter())
            .create();

    public static <T> T fromJson(String s, Type typeOfT) {
        return GSON.fromJson(s, typeOfT);
    }
}
