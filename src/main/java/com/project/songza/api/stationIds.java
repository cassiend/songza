package com.project.songza.api;

import android.util.Log;
import com.google.inject.Inject;
import roboguice.util.SafeAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class stationIds extends SafeAsyncTask<List<Station>> {

    private static final String LOG_CLASS = "StationsRetrievalTask";
    public static final String GET_STATIONS_CALL = "http://dev3.songza.com/api/1/gallery/tag/activities";

    private final RetrieveStationsCallback callback;
    private final SongzaHttpClient songzaHttpClient;
    private final String activityId;

    @Inject
    public stationIds(RetrieveStationsCallback callback, SongzaHttpClient songzaHttpClient, String activityId) {
        super();
        this.callback = callback;
        this.songzaHttpClient = songzaHttpClient;
        this.activityId = activityId;
    }

    @Override
    public List<Station> call() throws Exception {
        List<Station> list = new ArrayList<Station>();

        ApiRequest request = ApiRequest.createGetRequest(GET_STATIONS_CALL);

        SongzaHttpClient.Response response = songzaHttpClient.get(request.url(), request.headers());

        if (response.isSuccess()) {
            list = response.getBodyAs(Station.LIST_TYPE);
        } else {
            Log.e(LOG_CLASS, "Retrieve events from server not success");
        }
        return list;
    }

    @Override
    protected void onSuccess(List<Station> stations) throws Exception {
        callback.onActivitiesReturned(stations);
    }

    @Override
    protected void onException(Exception e) throws RuntimeException {
        callback.onError();
    }

    public interface RetrieveStationsCallback {
        void onActivitiesReturned(List<Station> stations);
        void onError();
    }
}
