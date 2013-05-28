package com.project.songza.api;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.inject.Inject;
import com.project.songza.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StationsArrayAdapter extends ArrayAdapter<Station> {

    private ExecutorService threadPool = Executors.newFixedThreadPool(4);

    @Inject
    public StationsArrayAdapter(Activity context) {
        super(context, R.layout.station_list_item, R.id.station_item_title);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        TextView stationItemTitle = (TextView) view.findViewById(R.id.station_item_title);
        final ImageView stationItemImage = (ImageView) view.findViewById(R.id.station_item_image);

        final Station station = getItem(position);
        view.setTag(station);
        stationItemTitle.setText(station.title());

        setImage(stationItemImage, station);

        return view;
    }

    private void setImage(ImageView stationItemImage, Station station) {
        ImageTask imageTask = new ImageTask(station.url());
        imageTask.setOnCompleteHandler(new OnCompleteHandler(stationItemImage));

        threadPool.execute(imageTask);
    }

    static class OnCompleteHandler extends Handler {
        private final ImageView view;

        public OnCompleteHandler(ImageView imageView){
            this.view = imageView;
        }

        @Override
        public void handleMessage(Message message) {
            Drawable image = (Drawable) message.obj;
            view.setImageDrawable(image);
        }
    }
}
