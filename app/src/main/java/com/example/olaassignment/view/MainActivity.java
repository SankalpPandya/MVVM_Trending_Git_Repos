package com.example.olaassignment.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.olaassignment.R;
import com.example.olaassignment.viewmodel.FeedsViewModel;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private FeedsViewModel feedsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        showHomeScreenFragment();
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
    }

    private void showHomeScreenFragment() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out)
                .replace(R.id.base_frame_container,
                        HomeScreenFragment.NewInstance(),
                        HomeScreenFragment.class.getName())
                .addToBackStack(HomeScreenFragment.class.getName())
                .commit();
    }

    private void initViewModel() {
        feedsViewModel = ViewModelProviders.of(this).get(FeedsViewModel.class);
        feedsViewModel.init();
    }

    public FeedsViewModel getMainViewModel() {
        return feedsViewModel;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
