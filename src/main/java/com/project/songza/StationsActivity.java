package com.project.songza;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.project.songza.api.SongzaHttpClient;
import com.project.songza.api.StationsArrayAdapter;
import com.project.songza.domain.SongzaActivity;
import com.project.songza.domain.Station;
import com.project.songza.task.StationsRetrievalTask;
import roboguice.activity.RoboActivity;

import java.util.List;

public class StationsActivity extends RoboActivity implements StationsRetrievalTask.RetrieveStationsCallback {

    private static final String LOG_CLASS = "StationsActivity";
    public static final String STATION = "station";
    public static final String SONGZA_ACTIVITY = "songza_activity";
    private ListView list;
    private SongzaActivity songzaActivity;
    private StationsArrayAdapter stationsArrayAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stations);
        list = (ListView) findViewById(R.id.stations_list);
        songzaActivity = (SongzaActivity) getIntent().getExtras().get(SONGZA_ACTIVITY);

        SongzaHttpClient httpClient = new SongzaHttpClient();
        StationsRetrievalTask task = new StationsRetrievalTask(this, httpClient, songzaActivity);

        task.execute();
    }

    @Override
    public void onStationsReturned(List<Station> stations) {
        if (stations.size() == 0) {
            list.setVisibility(View.GONE);
            TextView noStationsText = (TextView) findViewById(R.id.no_stations_text);
            noStationsText.setVisibility(View.VISIBLE);
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
        stationsArrayAdapter = new StationsArrayAdapter(this);
        stationsArrayAdapter.setNotifyOnChange(true);

        for (Station station : stations) {
            stationsArrayAdapter.add(station);
        }

        list.setAdapter(stationsArrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Station station = (Station) view.getTag();
                Intent stationsActivity = new Intent(StationsActivity.this, StationDetailActivity.class);
                stationsActivity.putExtra(STATION, station);
                startActivityForResult(stationsActivity, 0);
            }
        });

        list.setOnItemLongClickListener(setOnLongClickListener(this));

        Log.i(LOG_CLASS, "There are stations");
    }

    private AdapterView.OnItemLongClickListener setOnLongClickListener(final Activity activity) {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, final View view, int i, long l) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setTitle("Favorite?");
                alertDialogBuilder
                        .setMessage("Click yes to set as favorite")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                view.setBackgroundColor(Color.BLUE);
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                view.setBackgroundColor(Color.TRANSPARENT);
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;
            }
        };
    }
}
