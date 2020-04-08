package com.example.olaassignment.view;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class RootApplication extends android.app.Application {

    static Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();
        InitPicasso();
    }

    private void InitPicasso() {
        File httpCacheDirecotory = new File(this.getCacheDir(), "http-cache1");
        Cache cache = new Cache(httpCacheDirecotory, 10 * 1024 * 1024);

        OkHttpClient okHttpClient =
                new OkHttpClient.Builder().addNetworkInterceptor(chain -> {
                    Response originalResponse = chain.proceed(chain.request());
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxAge(2, TimeUnit.HOURS)
                            .build();
                    return originalResponse.newBuilder().header
                            ("Cache-Control", cacheControl.toString())
                            .removeHeader("Pragma")
                            .build();
                }).cache(cache).build();
        OkHttp3Downloader okHttpDownloader = new OkHttp3Downloader(okHttpClient);
        picasso = new Picasso.Builder(this).downloader(okHttpDownloader).build();
        Picasso.setSingletonInstance(picasso);

    }

    public static Picasso GetPicasso() {
        return picasso;
    }
}
