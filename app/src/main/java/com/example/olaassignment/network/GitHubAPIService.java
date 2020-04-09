package com.example.olaassignment.network;

import android.content.Context;

import com.example.olaassignment.utils.Constants;
import com.example.olaassignment.utils.Util;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubAPIService {

    private static Retrofit retrofit;

    private static void buildRetrofitInstance(OkHttpClient client) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static <s> s createService(Class<s> serviceClass, Context context) {
        buildRetrofitInstance(Util.getOkHttpClient(context));
        return retrofit.create(serviceClass);
    }
}
