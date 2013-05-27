package com.project.songza.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class JsonHelperTest {

    @Test
    public void testFromJson() throws Exception {
        List<SongzaActivity> activities = JsonHelper.fromJson("[{\"name\": \"Shopping At A Vintage Store\",\"station_ids\": [1644631,1497221,1410911]}]", SongzaActivity.LIST_TYPE);
        assertThat(activities.get(0).title(), is("Shopping At A Vintage Store"));
        assertThat(activities.get(0).getStationIds(), is("id=1644631&id=1497221&id=1410911&"));
    }
}
