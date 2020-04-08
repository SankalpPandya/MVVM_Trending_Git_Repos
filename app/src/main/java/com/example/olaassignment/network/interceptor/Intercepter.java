package com.example.olaassignment.network.interceptor;

import android.content.Context;

import com.example.olaassignment.utils.Util;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class Intercepter {

    /**
     * Validate cache, return stream. Return cache if no network.
     *
     * @param context
     * @return
     */
    public static Interceptor getOnlineInterceptor(final Context context) {
        Interceptor interceptor = chain -> {
            Response response = chain.proceed(chain.request());

            if (Util.isConnected(context)) {
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + 7200)
                        .build();
            } else {
                return response;
            }
        };

        return interceptor;
    }

    /**
     * Get me cache.
     *
     * @param context
     * @return
     */
    public static Interceptor getOfflineInterceptor(final Context context) {
        Interceptor interceptor = chain -> {
            Request request = chain.request();
            if (!Util.isConnected(context)) {
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + 7200)
                        .removeHeader("Pragma")
                        .build();
            }
            return chain.proceed(request);
        };
        return interceptor;
    }


}