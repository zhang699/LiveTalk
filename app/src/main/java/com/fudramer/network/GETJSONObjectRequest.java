package com.fudramer.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxiongzhan on 2017/10/10.
 */

public class GETJSONObjectRequest extends JsonObjectRequest {

    private final Response.Listener<JSONObject> mSuccessListener;
    private final Map<String, String> mParams;

    public GETJSONObjectRequest(int method, String url, Map<String, String> params, Response.Listener<JSONObject> success, Response.ErrorListener errorListener) {
        super(method, url, success, errorListener);
        this.mSuccessListener = success;
        this.mParams = params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>(super.getHeaders());
        headers.put("Authorization", fudreamer.com.livetalk.BuildConfig.API_TOKEN);
        return headers;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
}
