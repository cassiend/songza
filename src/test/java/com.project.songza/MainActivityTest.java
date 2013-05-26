package com.project.songza;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void shouldTestFirstTest() {
        String appName = new MainActivity().getResources().getString(R.string.app_name);
        assertThat(appName, is("songza"));
    }
}
