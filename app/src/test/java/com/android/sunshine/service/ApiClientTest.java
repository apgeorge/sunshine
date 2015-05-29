package com.android.sunshine.service;

import com.android.sunshine.common.AppLogger;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class ApiClientTest {


    @Test @Ignore("This test fails bcoz of Uri class not available on JVM and should be ITed on actual sdk")
    public void testDoGet() throws Exception {
        AppLogger.setLogger(mock(AppLogger.class));

        String response = new ApiClient().doGet("http://www.google.com", new HashMap<String, String>());

        System.out.println("response = " + response);
        Assert.assertThat(response.isEmpty(), is(Boolean.FALSE) );
    }
}