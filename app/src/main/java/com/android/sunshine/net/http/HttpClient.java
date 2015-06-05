package com.android.sunshine.net.http;

import java.util.HashMap;

public interface HttpClient {
    String doGet(String baseURL, HashMap<String, String> urlParams);
}
