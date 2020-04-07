package com.example.olaassignment.repository;

import com.example.olaassignment.network.GitHubAPIService;
import com.example.olaassignment.network.GithubApi;

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


}
