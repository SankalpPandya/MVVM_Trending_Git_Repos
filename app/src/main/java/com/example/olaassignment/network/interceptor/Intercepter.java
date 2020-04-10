package com.example.olaassignment.network.interceptor;

import android.content.Context;
import android.util.Log;

import com.example.olaassignment.utils.Constants;
import com.example.olaassignment.utils.Util;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class Intercepter {

    /**
     * Validate cache, return stream. Return cache if no network.
     */
    public static Interceptor getInterceptor(final Context context) {
        return chain -> {
            Request request = chain.request();
            Response response;
            String headers = request.header("Cache-Control");
            if (Util.isConnected(context) && (headers == null
                    || headers.contains("no-cache"))) {

                response = chain.proceed(request.newBuilder()
                        .removeHeader("Pragma")
                        .build());
            } else if (!Util.isConnected(context)) {
                if (headers == null || headers.contains("no-cache")) {
                    return null;
                }
                response =
                        chain.proceed(request.newBuilder()
                                .removeHeader("Pragma")
                                .header("Cache-Control",
                                        "public, only-if-cached, max-age=" + Constants.
                                                CacheRetentionTimeoutInSeconds)
                                .build());
            } else {
                response =
                        chain.proceed(request.newBuilder()
                                .removeHeader("Pragma")
                                .header("Cache-Control", "public, max-age=" + Constants.
                                        CacheRetentionTimeoutInSeconds)
                                .build());
            }

            if (response.cacheResponse() != null) {
                Log.d("Intercepter", "response from cache ...");
            }
            if (response.networkResponse() != null) {
                Log.d("Intercepter", "response from network ...");
            }
            return response;

        };
    }
}