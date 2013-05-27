package com.project.songza.api;

import android.util.Log;
import com.google.inject.Inject;
import roboguice.util.SafeAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesRetrievalTask extends SafeAsyncTask<List<SongzaActivity>> {

    private static final String LOG_CLASS = "ActivitiesRetrievalTask";
    public static final String GET_ACTIVITIES_CALL = "http://dev3.songza.com/api/1/gallery/tag/activities";

    private final RetrieveActivitiesCallback callback;

    @Inject
    public ActivitiesRetrievalTask(RetrieveActivitiesCallback callback) {
        super();
        this.callback = callback;
    }

    @Override
    public List<SongzaActivity> call() throws Exception {
        List<SongzaActivity> list = new ArrayList<SongzaActivity>();

        ApiRequest request = ApiRequest.createGetRequest(GET_ACTIVITIES_CALL);
        
        SongzaHttpClient.Response response = new SongzaHttpClient().get(request.url(), request.headers());

        if (response.isSuccess()) {
            list = response.getBodyAs(SongzaActivity.LIST_TYPE);
        } else {
            Log.e(LOG_CLASS, "Retrieve events from server not success");
        }
        return list;
    }

    @Override
    protected void onSuccess(List<SongzaActivity> activities) throws Exception {
        callback.onActivitiesReturned(activities);
    }

    public interface RetrieveActivitiesCallback{
        void onActivitiesReturned(List<SongzaActivity> activities);
    }
}
