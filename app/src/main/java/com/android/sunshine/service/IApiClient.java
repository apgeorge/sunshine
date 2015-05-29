package com.android.sunshine.service;

import java.util.HashMap;

public interface IApiClient {
    String doGet(String baseURL, HashMap<String, String> urlParams);
}
