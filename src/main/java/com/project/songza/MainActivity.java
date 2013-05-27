package com.project.songza;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.project.songza.api.ActivitiesRetrievalTask;
import com.project.songza.api.SongzaActivity;
import com.project.songza.api.SongzaHttpClient;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class MainActivity extends Activity implements ActivitiesRetrievalTask.RetrieveActivitiesCallback {

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
            final List<String> listOfTitles = newArrayList();

            for (SongzaActivity activity : activities) {
                listOfTitles.add(activity.title());
            }

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_list_item, R.id.activity_item_title, listOfTitles);
            list.setAdapter(adapter);
            Log.i(LOG_CLASS, "There are activities");
        }
    }

    @Override
    public void onError() {
        list.setVisibility(View.GONE);
        TextView error = (TextView) findViewById(R.id.error_text);
        error.setVisibility(View.VISIBLE);
        Log.i(LOG_CLASS, "There was an error retrieving activities");
    }
}
