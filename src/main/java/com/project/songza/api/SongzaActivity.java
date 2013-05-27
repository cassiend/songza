package com.project.songza.api;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public abstract class SongzaActivity {
    private final String title;
    private String description;
    private Long id;

    public static final Type LIST_TYPE = new TypeToken<List<SongzaActivity>>(){}.getType();

    protected SongzaActivity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public ContentValues asValues() {
//        ContentValues values = new ContentValues();
//        values.put(TableColumn._id.name(), id);
//        values.put(TableColumn.type.name(), getType().toString());
//        values.put(TableColumn.content.name(), asJson());
//        values.put(TableColumn.play_order.name(), getPlayOrder());
//        values.put(TableColumn.shared_on.name(), sharedOn == null ? null : sharedOn.getTime());
//        return values;
//    }
}
