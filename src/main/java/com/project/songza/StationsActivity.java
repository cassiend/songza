package com.project.songza;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.project.songza.api.SongzaActivity;
import com.project.songza.api.Station;
import com.project.songza.api.StationsArrayAdapter;
import com.project.songza.api.SongzaHttpClient;
import com.project.songza.api.StationsRetrievalTask;
import roboguice.activity.RoboActivity;

import java.util.List;

public class StationsActivity extends RoboActivity implements StationsRetrievalTask.RetrieveStationsCallback{

    private static final String LOG_CLASS = "StationsActivity";
    public static final String STATION = "station";
    public static final String SONGZA_ACTIVITY = "songza_activity";
    private ListView list;
    private SongzaActivity songzaActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stations);
        list = (ListView) findViewById(R.id.stations_list);
        songzaActivity = (SongzaActivity) getIntent().getExtras().get(SONGZA_ACTIVITY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SongzaHttpClient httpClient = new SongzaHttpClient();
        StationsRetrievalTask task = new StationsRetrievalTask(this, httpClient, songzaActivity);

        task.execute();
    }

    @Override
    public void onActivitiesReturned(List<Station> stations) {
        if (stations.size() == 0) {
            list.setVisibility(View.GONE);
            TextView noActivities = (TextView) findViewById(R.id.no_stations_text);
            noActivities.setVisibility(View.VISIBLE);
            Log.i(LOG_CLASS, "There are no stations");
        } else {
            setupListOfStations(stations);
        }
    }

    @Override
    public void onError() {
        list.setVisibility(View.GONE);
        TextView error = (TextView) findViewById(R.id.stations_error_text);
        error.setVisibility(View.VISIBLE);
        Log.i(LOG_CLASS, "There was an error retrieving stations");
    }

    private void setupListOfStations(List<Station> stations) {
        final StationsArrayAdapter adapter = new StationsArrayAdapter(this);

        for (Station station : stations) {
            adapter.add(station);
        }

        list.setAdapter(adapter);
        list.setOnItemClickListener(setStationClickListener(this));

        Log.i(LOG_CLASS, "There are stations");
    }

    private AdapterView.OnItemClickListener setStationClickListener(final Activity activity) {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Station station = (Station) view.getTag();
                Intent stationsActivity = new Intent(activity, StationDetailActivity.class);
                stationsActivity.putExtra(STATION, station);
                activity.startActivity(stationsActivity);
            }
        };
    }
}
