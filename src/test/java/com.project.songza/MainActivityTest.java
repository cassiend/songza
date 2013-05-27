package com.project.songza;

import android.view.View;
import android.widget.TextView;
import com.project.songza.api.SongzaActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mainActivity;
    private View listView;

    @Before
    public void setUp() {
        initMocks(this);
        mainActivity = new MainActivity();
        mainActivity.onCreate(null);
        listView = mainActivity.findViewById(R.id.activities_list);
    }

    @Test
    public void shouldShowErrorMessageIfCannotRetrieveActivities() {
        mainActivity.onError();

        TextView error = (TextView) mainActivity.findViewById(R.id.error_text);
        assertThat(error.getVisibility(), is(View.VISIBLE));
        assertThat(listView.getVisibility(), is(View.GONE));
    }
    
    @Test
    public void shouldShowNoActivitiesMessageIfNoActivities() {
        List<SongzaActivity> activities = newArrayList();

        mainActivity.onActivitiesReturned(activities);

        TextView error = (TextView) mainActivity.findViewById(R.id.no_activities_text);
        assertThat(error.getVisibility(), is(View.VISIBLE));
        assertThat(listView.getVisibility(), is(View.GONE));

    }
}
