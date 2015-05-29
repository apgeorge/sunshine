package com.android.sunshine.service;

import com.android.sunshine.common.AppLogger;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class ApiClientTest {

    @Test
    public void testDoGet() throws Exception {
        AppLogger.setLogger(mock(AppLogger.class));

        String response = new ApiClient().doGet("http://www.google.com");

        System.out.println("response = " + response);
        Assert.assertThat(response.isEmpty(), is(Boolean.FALSE) );
    }
}