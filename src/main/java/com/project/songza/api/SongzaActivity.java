package com.project.songza.api;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SongzaActivity {
    private final String title;

    public static final Type LIST_TYPE = new TypeToken<List<SongzaActivity>>(){}.getType();

    public SongzaActivity(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }
}
