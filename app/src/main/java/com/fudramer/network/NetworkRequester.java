package com.fudramer.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

import fudreamer.com.livetalk.BuildConfig;

/**
 * Created by zhangxiongzhan on 2017/10/10.
 */

public class NetworkRequester {

    private static NetworkRequester mNetworkRequester;
    private final Context mContext;
    private final String mHost;
    RequestQueue mQueue ;

    private NetworkRequester(Context context) {
        VolleyLog.DEBUG = true;
        mContext = context;
        mQueue = Volley.newRequestQueue(mContext);
        mHost = String.valueOf(BuildConfig.API_HOST)+"/%s";
    }

    public static NetworkRequester getInstance(Context context){
        if (mNetworkRequester != null) {
            return mNetworkRequester;
        }else {
            mNetworkRequester =  new NetworkRequester(context);
        }
        return mNetworkRequester;

    }
    public void executePOSTRequest(String url, String json, Response.Listener<JSONObject> success, Response.ErrorListener failure) {
        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST,
                String.format(mHost, url),
                json,
                success,
                failure
        );
        mQueue.add(jsonObjectRequest );
    }

    public void exeuteAPIGETRequest(String url, Map<String, String> params, Response.Listener<JSONObject> success, Response.ErrorListener failure) {
        JsonObjectRequest jsonObjectRequest = new GETJSONObjectRequest(Request.Method.GET,
                String.format(mHost, url),
                params,
                success,
                failure);
        mQueue.add(jsonObjectRequest);
    }

}
