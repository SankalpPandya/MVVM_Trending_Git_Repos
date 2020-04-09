package com.example.olaassignment.network.interceptor;

import android.content.Context;

import com.example.olaassignment.utils.Constants;
import com.example.olaassignment.utils.Util;

import okhttp3.Interceptor;
import okhttp3.Response;

public final class Intercepter {

    /**
     * Validate cache, return stream. Return cache if no network.
     *
     * @param context
     * @return
     */
    public static Interceptor getOnlineInterceptor(final Context context) {
        return chain -> {
            Response response = chain.proceed(chain.request());
            String headers = response.header("Cache-Control");
            if (Util.isConnected(context) && (headers == null
                    || headers.contains("no-store")
                    || headers.contains("no-cache"))) {
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .build();
            } else if (!Util.isConnected(context)) {
                return response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + Constants.
                                CacheRetentionTimeoutInSeconds)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + Constants.
                                CacheRetentionTimeoutInSeconds)
                        .build();
            }
        };
    }
}