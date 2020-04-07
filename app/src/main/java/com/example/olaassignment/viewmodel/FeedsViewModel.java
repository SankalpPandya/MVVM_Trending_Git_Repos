package com.example.olaassignment.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.olaassignment.repository.RepoRepository;
import com.example.olaassignment.utils.ApiResponse;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FeedsViewModel extends ViewModel {

    private RepoRepository repoRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> trendingApiResposne = new MutableLiveData<>();

    public void init() {

        if (repoRepository != null) {
            return;
        }
        repoRepository = RepoRepository.getInstance();
    }

    public void hitGitHubApi() {
        disposables.add(repoRepository.fetchTrendingRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .doOnSubscribe((d) -> trendingApiResposne.postValue(ApiResponse.loading()))
                .subscribe(
                        result -> {
                            Log.d("casd", "resukt " + result);
                            trendingApiResposne.postValue(ApiResponse.success(result));
                        },
                        throwable -> trendingApiResposne.postValue(ApiResponse.error(throwable))
                ));
    }

    public MutableLiveData<ApiResponse> geTrendingApiResponse() {
        return trendingApiResposne;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
