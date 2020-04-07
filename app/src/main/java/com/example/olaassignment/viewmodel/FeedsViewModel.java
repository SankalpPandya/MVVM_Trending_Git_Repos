package com.example.olaassignment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.olaassignment.repository.RepoRepository;

import io.reactivex.disposables.CompositeDisposable;

public class FeedsViewModel extends ViewModel {

    private RepoRepository repoRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public void init() {

        if (repoRepository != null) {
            return;
        }
        repoRepository = RepoRepository.getInstance();
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
