package com.project.songza.api;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Station {
    public static final Type LIST_TYPE = new TypeToken<List<Station>>(){}.getType();
    private String title;
    private final String url;

    public Station(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String title() {
        return title;
    }

    public String url() {
        return url;
    }
}
