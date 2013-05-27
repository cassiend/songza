package com.project.songza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.project.songza.api.ActivitiesRetrievalTask;
import com.project.songza.api.SongzaActivitiesArrayAdapter;
import com.project.songza.api.SongzaActivity;
import com.project.songza.api.SongzaHttpClient;
import roboguice.activity.RoboActivity;

import java.util.List;

public class MainActivity extends RoboActivity implements ActivitiesRetrievalTask.RetrieveActivitiesCallback {

    private static final String LOG_CLASS = "MainActivity";
    private ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list = (ListView) findViewById(R.id.activities_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SongzaHttpClient httpClient = new SongzaHttpClient();
        ActivitiesRetrievalTask task = new ActivitiesRetrievalTask(this, httpClient);
        task.execute();
    }

    @Override
    public void onActivitiesReturned(List<SongzaActivity> activities) {
        if (activities.size() == 0) {
            list.setVisibility(View.GONE);
            TextView noActivities = (TextView) findViewById(R.id.no_activities_text);
            noActivities.setVisibility(View.VISIBLE);
            Log.i(LOG_CLASS, "There are no activities");
        } else {
            setupListOfActivities(activities);
        }
    }

    @Override
    public void onError() {
        list.setVisibility(View.GONE);
        TextView error = (TextView) findViewById(R.id.error_text);
        error.setVisibility(View.VISIBLE);
        Log.i(LOG_CLASS, "There was an error retrieving activities");
    }

    private void setupListOfActivities(List<SongzaActivity> activities) {
        final SongzaActivitiesArrayAdapter adapter = new SongzaActivitiesArrayAdapter(this);

        for (SongzaActivity activity : activities) {
            adapter.add(activity);
        }

        list.setAdapter(adapter);
        list.setOnItemClickListener(getStation_ids(this));

        Log.i(LOG_CLASS, "There are activities");
    }

    private AdapterView.OnItemClickListener getStation_ids(final Activity activity) {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SongzaActivity songzaActivity = (SongzaActivity) view.getTag();
                Intent stationsActivity = new Intent(activity, StationsActivity.class);
                stationsActivity.putExtra("station_ids", songzaActivity.getStationIds());
                activity.startActivity(stationsActivity);
            }
        };
    }
}
