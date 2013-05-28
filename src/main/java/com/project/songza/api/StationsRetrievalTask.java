package com.project.songza.api;

import android.util.Log;
import com.google.inject.Inject;
import roboguice.util.SafeAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class StationsRetrievalTask extends SafeAsyncTask<List<Station>> {

    private static final String LOG_CLASS = "StationsRetrievalTask";
    public static final String GET_STATIONS_CALL = "http://dev3.songza.com/api/1/station/multi?";

    private final RetrieveStationsCallback callback;
    private final SongzaHttpClient songzaHttpClient;
    private final SongzaActivity songzaActivity;

    @Inject
    public StationsRetrievalTask(RetrieveStationsCallback callback, SongzaHttpClient songzaHttpClient, SongzaActivity songzaActivity) {
        super();
        this.callback = callback;
        this.songzaHttpClient = songzaHttpClient;
        this.songzaActivity = songzaActivity;
    }

    @Override
    public List<Station> call() throws Exception {
        List<Station> list = new ArrayList<Station>();

        String getStations = String.valueOf(new StringBuilder(GET_STATIONS_CALL).append(songzaActivity.getStationIds()));
        ApiRequest request = ApiRequest.createGetRequest(getStations);

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
