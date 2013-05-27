package com.project.songza.api;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Station {
    public static final Type LIST_TYPE = new TypeToken<List<Station>>(){}.getType();
    private String title;

    public Station(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }
}
