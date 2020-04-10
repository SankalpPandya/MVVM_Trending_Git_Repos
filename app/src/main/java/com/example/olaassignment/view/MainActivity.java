package com.example.olaassignment.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.olaassignment.R;
import com.example.olaassignment.viewmodel.TrendingReposViewModel;


public class MainActivity extends AppCompatActivity {

    private TrendingReposViewModel trendingReposViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        showHomeScreenFragment();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void initViewModel() {
        trendingReposViewModel = ViewModelProviders.of(this).get(TrendingReposViewModel.class);
    }

    public TrendingReposViewModel getMainViewModel() {
        return trendingReposViewModel;
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
