package com.project.songza.domain;

import com.google.common.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class Station implements Serializable {
    public static final Type LIST_TYPE = new TypeToken<List<Station>>(){}.getType();
    private String title;
    private final String url;
    private final List<String> artistNames;
    private final String description;

    public Station(String title, String url, List<String> artistNames, String description) {
        this.title = title;
        this.url = url;
        this.artistNames = artistNames;
        this.description = description;
    }

    public String title() {
        return title;
    }

    public String url() {
        return url;
    }

    public List<String> getArtistNames() {
        return artistNames;
    }

    public String description() {
        return description;
    }
}
