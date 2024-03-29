package com.project.songza.domain;

import com.google.common.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class SongzaActivity implements Serializable {
    private final String title;
    private final List<String> stationIds;

    public static final Type LIST_TYPE = new TypeToken<List<SongzaActivity>>(){}.getType();

    public SongzaActivity(String title, List stationIds) {
        this.title = title;
        this.stationIds = stationIds;
    }

    public String title() {
        return title;
    }

    public String getStationIds() {
        String setOfStationIdsForCall = StringUtils.EMPTY;
        for(String stationId : stationIds){
            setOfStationIdsForCall = new StringBuilder()
                    .append(setOfStationIdsForCall)
                    .append("id=")
                    .append(stationId)
                    .append("&")
                    .toString();
        }
        return setOfStationIdsForCall;
    }
}
