package com.example.olaassignment.repository;

import com.example.olaassignment.network.GitHubAPIService;
import com.example.olaassignment.network.GithubApi;
import com.google.gson.JsonElement;

import io.reactivex.Observable;

public class RepoRepository {

    private static RepoRepository newsRepository;
    private GithubApi service;

    public static RepoRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new RepoRepository();
        }
        return newsRepository;
    }

    public RepoRepository() {
        service = GitHubAPIService.createService(GithubApi.class);
    }


    public Observable<JsonElement> fetchTrendingRepos() {
        return service.getTrendingRepos("", ",", "");
    }
}
