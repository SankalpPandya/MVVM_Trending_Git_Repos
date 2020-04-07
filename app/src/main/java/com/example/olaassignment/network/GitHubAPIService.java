package com.example.olaassignment.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubAPIService {

    private static final String BASE_URL = "https://github-trending-api.now.sh";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static <s> s createService(Class<s> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
