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
public class StationsRetrievalTaskTest {

    private StationsRetrievalTask task;

    @Mock
    private SongzaHttpClient.Response response;
    @Mock
    private SongzaHttpClient songzaHttpClient;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        given(songzaHttpClient.get(anyString(), anyMap())).willReturn(response);

        task = new StationsRetrievalTask(null, songzaHttpClient, "stationIds");
    }

    @Test
    public void shouldCallOnExecute() throws Exception {
        task.call();

        verify(songzaHttpClient).get(anyString(), anyMap());
    }


    @Test
    public void shouldReturnListOfStationsIfResponseIsSuccessful() throws Exception {
        List<Station> stations = newArrayList(new Station("Station Title1", "some url"), new Station("Station Title2", "some url"));
        when(response.getBodyAs(Station.LIST_TYPE)).thenReturn(stations);
        when(response.isSuccess()).thenReturn(true);

        List<Station> actualStations = task.call();

        assertThat(actualStations.get(0).title(), is("Station Title1"));
        assertThat(actualStations.get(1).title(), is("Station Title2"));
        assertThat(actualStations.get(1).url(), is("some url"));
    }

    @Test
    public void shouldReturnEmptyListResponseNotSuccessful()  throws Exception {
        when(response.isSuccess()).thenReturn(false);

        List<Station> actualStations = task.call();

        assertThat(actualStations.size(), is(0));
    }
}
