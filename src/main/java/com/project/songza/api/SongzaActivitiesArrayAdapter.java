package com.project.songza.api;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.inject.Inject;
import com.project.songza.R;

public class SongzaActivitiesArrayAdapter extends ArrayAdapter<SongzaActivity> {

    @Inject
    public SongzaActivitiesArrayAdapter(Activity context) {
        super(context, R.layout.activity_list_item, R.id.activity_item_title);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView activityTextTitle = (TextView) view.findViewById(R.id.activity_item_title);

        SongzaActivity activity = getItem(position);
        view.setTag(activity);
        activityTextTitle.setText(activity.title());
        return view;
    }

}
