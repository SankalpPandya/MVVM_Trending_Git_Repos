package com.example.olaassignment.network;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubApi {

    @GET("/repositories")
    Observable<JsonElement> getTrendingRepos(@Query("language") String language,
                                             @Query("since") String since,
                                             @Query("spoken_language_code") String spoken_language);

}
