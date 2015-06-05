package com.android.sunshine.service;

import android.support.test.runner.AndroidJUnit4;

import com.android.sunshine.apiclient.ApiClient;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;

@RunWith(AndroidJUnit4.class)
public class ApiClientTest {
    @Test
    public void shouldFetchGetRequests() throws Exception {

        String response = new ApiClient().doGet("http://www.google.com", new HashMap<String, String>());

        System.out.println("response = " + response);
        Assert.assertThat(response.isEmpty(), is(Boolean.FALSE) );
    }
}