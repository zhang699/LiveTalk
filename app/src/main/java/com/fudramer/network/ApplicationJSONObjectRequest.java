package com.fudramer.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxiongzhan on 2017/10/14.
 */

public class ApplicationJSONObjectRequest extends JsonObjectRequest {
    public ApplicationJSONObjectRequest(int method, String url, String requestBody, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    public ApplicationJSONObjectRequest(int method, String url, Response.Listener<JSONObject> success, Response.ErrorListener errorListener) {
        super(method, url, success, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>(super.getHeaders());
        headers.put("Authorization", fudreamer.com.livetalk.BuildConfig.API_TOKEN);
        return headers;
    }
}
