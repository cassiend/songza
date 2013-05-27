package com.project.songza.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;



@RunWith(RobolectricTestRunner.class)
public class ActivitiesRetrievalTaskTest {

    private ActivitiesRetrievalTask task;

    @Mock
    private SongzaHttpClient.Response response;

    @Mock
    private SongzaHttpClient songzaHttpClient;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        given(songzaHttpClient.get(anyString(), anyMap())).willReturn(response);

        task = new ActivitiesRetrievalTask(null);
    }

    @Test
    public void shouldCallOnExecute() throws Exception {
        task.call();

        verify(songzaHttpClient).get(anyString(), anyMap());
    }


    @Test
    public void shouldReturnListOfActivitiesIfResponseIsSuccessful() throws Exception {
        List<SongzaActivity> activities = getEvents();
        when(response.getBodyAs(SongzaActivity.LIST_TYPE)).thenReturn(activities);
        when(response.isSuccess()).thenReturn(true);

        List<SongzaActivity> actualActivities = task.call();

        assertThat(actualActivities.get(0).title(), is("Title1"));
        assertThat(actualActivities.get(1).title(), is("Title2"));
    }

    private List<SongzaActivity> getEvents() {
        return newArrayList(new SongzaActivity("Title1"), new SongzaActivity("Title2"));
    }

    @Test
    public void shouldReturnEmptyListResonseNotSuccessful()  throws Exception {
        when(response.isSuccess()).thenReturn(false);

        List<SongzaActivity> actualActivities = task.call();

        assertThat(actualActivities.size(), is(0));
    }
}
