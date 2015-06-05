package com.android.sunshine.net;

import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

public class UrlMaker {
    public UrlMaker() {
    }

    public String getUrl(String baseUrl, HashMap<String, String> urlParams) {
        Uri.Builder builder = Uri.parse(baseUrl).buildUpon();

        for (Map.Entry<String,String> urlParam: urlParams.entrySet()){
            builder.appendQueryParameter(urlParam.getKey(),urlParam.getValue());
        }

        return builder.build().toString();
    }
}
