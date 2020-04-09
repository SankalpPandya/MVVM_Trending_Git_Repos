package com.example.olaassignment.repository;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.olaassignment.MockTestUtil;
import com.example.olaassignment.model.RepoEntity;
import com.example.olaassignment.network.GithubApi;
import com.google.gson.JsonElement;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import retrofit2.Response;

@RunWith(MockitoJUnitRunner.class)
public class GithubRepositoryTest {

    GithubApi githubApiService;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private RepoRepository repository;

    @Before
    public void init() {
        repository = RepoRepository.getInstance(InstrumentationRegistry.getInstrumentation().getContext());
    }

    @Test
    public void loadPostsTest() {
        List<RepoEntity> mockRepositories = MockTestUtil.mockRepositories();
        Observable<Response<JsonElement>>
                data = repository.fetchTrendingRepos(true);
        TestObserver testObserver = new TestObserver();
        data.subscribe(testObserver);
        testObserver.assertNoErrors();
    }
}
