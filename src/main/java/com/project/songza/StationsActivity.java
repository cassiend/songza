package com.project.songza;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.project.songza.api.*;
import roboguice.activity.RoboActivity;

import java.util.List;

public class StationsActivity extends RoboActivity implements StationsRetrievalTask.RetrieveStationsCallback{

    private static final String LOG_CLASS = "StationsActivity";
    public static final String STATION_IDS = "station_ids";
    private ListView list;
    private String station_ids;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stations);
        list = (ListView) findViewById(R.id.stations_list);
        station_ids = (String) getIntent().getExtras().get(STATION_IDS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SongzaHttpClient httpClient = new SongzaHttpClient();
        StationsRetrievalTask task = new StationsRetrievalTask(this, httpClient, station_ids);

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
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Station station = (Station) view.getTag();
//                Intent stationsActivity = new Intent(StationsActivity.this, St.class);
//                stationsActivity.putExtra("activity_id", songzaActivity.id());
            }
        });

        Log.i(LOG_CLASS, "There are stations");
    }
}
