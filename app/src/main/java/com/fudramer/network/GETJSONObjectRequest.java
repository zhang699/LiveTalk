package com.fudramer.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by zhangxiongzhan on 2017/10/10.
 */

public class GETJSONObjectRequest extends ApplicationJSONObjectRequest {

    private final Response.Listener<JSONObject> mSuccessListener;
    private final Map<String, String> mParams;

    public GETJSONObjectRequest(int method, String url, Map<String, String> params, Response.Listener<JSONObject> success, Response.ErrorListener errorListener) {
        super(method, url, success, errorListener);
        this.mSuccessListener = success;
        this.mParams = params;
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
}
