package com.example.olaassignment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.olaassignment.model.RepoEntity;
import com.example.olaassignment.network.interceptor.Intercepter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class Util {

    public static List<RepoEntity> GetRepoEntitiesFromJsonElements(JsonElement jsonElement) {
        ArrayList<RepoEntity> repositories = new ArrayList<>();
        try {
            Gson gson = new Gson();
            JsonElement jsonArray = jsonElement.getAsJsonArray();
            repositories = gson.fromJson(jsonArray, new TypeToken<ArrayList<RepoEntity>>() {
            }.getType());

        } catch (IllegalStateException e) {

        }
        return repositories;
    }


    public static boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo connection = manager != null ? manager.getActiveNetworkInfo() : null;
        return connection != null && connection.isConnectedOrConnecting();
    }

    public static OkHttpClient getOkHttpClient(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "http-cache");
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(Intercepter.getInterceptor(context))
                .cache(cache)
                .build();
    }

}
