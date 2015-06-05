package com.android.sunshine.apiclient;

import java.util.HashMap;

public interface IApiClient {
    String doGet(String baseURL, HashMap<String, String> urlParams);
}
