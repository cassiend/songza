package com.project.songza;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
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
public class StationDetailActivityTest {

    private StationDetailActivity stationDetailActivity;

    @Mock
    private Station station;
    private ListView listView;

    @Before
    public void setUp() {
        initMocks(this);

        stationDetailActivity = new StationDetailActivity();

        Intent someIntent = new Intent();
        someIntent.putExtra(StationsActivity.STATION, station);
        stationDetailActivity.setIntent(someIntent);

        stationDetailActivity.onCreate(null);
        listView = (ListView) stationDetailActivity.findViewById(R.id.featured_artist_list);
    }

    @Test
    public void shouldShowNoActivitiesMessageIfNoActivities() {
        List<String> artists = newArrayList();

        stationDetailActivity.onArtistsReturned(artists);

        TextView error = (TextView) stationDetailActivity.findViewById(R.id.no_artists_text);
        assertThat(error.getVisibility(), is(View.VISIBLE));
        assertThat(listView.getVisibility(), is(View.GONE));
    }
}