package com.project.songza;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
        SongzaHttpClient httpClient = new SongzaHttpClient();
        ActivitiesRetrievalTask task = new ActivitiesRetrievalTask(this, httpClient);
        task.execute();
    }

    @Override
    public void onActivitiesReturned(List<SongzaActivity> activities) {
        final List<String> listOfTitles = newArrayList();

        for (SongzaActivity activity : activities) {
            listOfTitles.add(activity.title());
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_list_item, R.id.activity_item_title, listOfTitles);
        list.setAdapter(adapter);
    }
}
