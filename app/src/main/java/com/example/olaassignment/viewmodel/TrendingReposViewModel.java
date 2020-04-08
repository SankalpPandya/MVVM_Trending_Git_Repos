package com.example.olaassignment.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.olaassignment.repository.RepoRepository;
import com.example.olaassignment.utils.ApiResponse;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TrendingReposViewModel extends ViewModel {

    private RepoRepository repoRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> trendingApiResponse = new MutableLiveData<>();

    public void init(Context context) {

        if (repoRepository != null) {
            return;
        }
        repoRepository = RepoRepository.getInstance(context);
    }

    public void hitGitHubApi(boolean isForceFetch) {
        disposables.add(repoRepository.fetchTrendingRepos(isForceFetch)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .doOnSubscribe((d) -> trendingApiResponse.postValue(ApiResponse.loading()))
                .subscribe(
                        result -> trendingApiResponse.postValue(ApiResponse.success(result)),
                        throwable -> trendingApiResponse.postValue(ApiResponse.error(throwable))
                ));
    }

    public MutableLiveData<ApiResponse> geTrendingApiResponse() {
        return trendingApiResponse;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
