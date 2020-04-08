package com.example.olaassignment.repository;

import android.content.Context;

import com.example.olaassignment.network.GitHubAPIService;
import com.example.olaassignment.network.GithubApi;
import com.google.gson.JsonElement;

import io.reactivex.Observable;

public class RepoRepository {

    private static RepoRepository newsRepository;
    private GithubApi service;

    public static RepoRepository getInstance(Context context) {
        if (newsRepository == null) {
            newsRepository = new RepoRepository(context);
        }
        return newsRepository;
    }

    private RepoRepository(Context context) {
        service = GitHubAPIService.createService(GithubApi.class, context);
    }

    public Observable<JsonElement> fetchTrendingRepos(boolean isForceRefresh) {
        return service.getTrendingRepos("", ",", "");
    }

}
