package com.example.olaassignment.api;

import com.example.olaassignment.network.GithubApi;
import com.google.gson.JsonArray;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Response;

public class GithubApiServiceTest extends ApiTestHelper<GithubApi> {

    private GithubApi service;

    @Before
    public void initService() {
        this.service = createService(GithubApi.class);
    }

    @Test
    public void fetchPostsTest() throws IOException {
        pushResponse("ola_testJson.json");
        Response response = service.getTrendingRepos
                ("", "", "", "").blockingLast();
        Assert.assertEquals(true, response.isSuccessful());

        JsonArray apiResponse = (JsonArray) response.body();
        Assert.assertEquals(true, !apiResponse.isJsonNull());
    }
}
