package com.project.songza.api;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.inject.Inject;
import com.project.songza.R;

public class StationsArrayAdapter extends ArrayAdapter<Station> {

    @Inject
    public StationsArrayAdapter(Activity context) {
        super(context, R.layout.station_list_item, R.id.station_item_title);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView stationItemTitle = (TextView) view.findViewById(R.id.station_item_title);

        Station station = getItem(position);
        view.setTag(station);
        stationItemTitle.setText(station.title());
        return view;
    }

}
