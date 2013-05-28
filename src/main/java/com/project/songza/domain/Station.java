package com.project.songza.domain;

import com.google.common.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class Station implements Serializable {
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
