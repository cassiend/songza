package com.project.songza;


import android.os.Bundle;
import com.project.songza.api.Station;
import roboguice.activity.RoboActivity;

public class StationDetailActivity extends RoboActivity{
    private Station station;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stations);
        station = (Station) getIntent().getSerializableExtra(StationsActivity.STATION);
        station.url();
    }

}
