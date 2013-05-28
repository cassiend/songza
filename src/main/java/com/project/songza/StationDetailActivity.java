package com.project.songza;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.project.songza.domain.Station;
import roboguice.activity.RoboActivity;

import java.util.List;

public class StationDetailActivity extends RoboActivity{
    private static final String LOG_CLASS = "StationDetailActivity";

    private Station station;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_detail);
        listView = (ListView) findViewById(R.id.featured_artist_list);

        station = (Station) getIntent().getSerializableExtra(StationsActivity.STATION);
        onArtistsReturned(station.getArtistNames());
    }

    public void onArtistsReturned(List<String> artists) {
        if (artists.size() == 0) {
            listView.setVisibility(View.GONE);
            TextView noArtistsText = (TextView) findViewById(R.id.no_artists_text);
            noArtistsText.setVisibility(View.VISIBLE);
            Log.i(LOG_CLASS, "There are no artists");
        } else {

        }
    }
}
