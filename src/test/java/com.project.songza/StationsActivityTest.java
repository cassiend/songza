package com.project.songza;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.project.songza.domain.SongzaActivity;
import com.project.songza.domain.Station;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class StationsActivityTest {

    private StationsActivity stationsActivity;
    private View listView;

    @Mock
    private SongzaActivity songzaActivity;

    @Before
    public void setUp() {
        initMocks(this);

        stationsActivity = new StationsActivity();

        Intent someIntent = new Intent();
        someIntent.putExtra(StationsActivity.SONGZA_ACTIVITY, songzaActivity);
        stationsActivity.setIntent(someIntent);

        stationsActivity.onCreate(null);
        listView = stationsActivity.findViewById(R.id.stations_list);
    }

    @Test
    public void shouldShowErrorMessageIfCannotRetrieveActivities() {
        stationsActivity.onError();

        TextView error = (TextView) stationsActivity.findViewById(R.id.stations_error_text);
        assertThat(error.getVisibility(), is(View.VISIBLE));
        assertThat(listView.getVisibility(), is(View.GONE));
    }

    @Test
    public void shouldShowNoActivitiesMessageIfNoActivities() {
        List<Station> stations = newArrayList();

        stationsActivity.onStationsReturned(stations);

        TextView error = (TextView) stationsActivity.findViewById(R.id.no_stations_text);
        assertThat(error.getVisibility(), is(View.VISIBLE));
        assertThat(listView.getVisibility(), is(View.GONE));
    }
}