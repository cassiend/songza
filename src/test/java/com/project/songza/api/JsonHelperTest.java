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
        List<SongzaActivity> activities = JsonHelper.fromJson("[{ \"name\" : \"json name\" }]", SongzaActivity.LIST_TYPE);
        assertThat(activities.get(0).title(), is("json name"));
    }
}
