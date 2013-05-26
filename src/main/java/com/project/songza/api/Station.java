package com.project.songza.api;

public abstract class Station {
    private final String title;
    private String description;
    private Long id;

    protected Station(String title, String description) {
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
