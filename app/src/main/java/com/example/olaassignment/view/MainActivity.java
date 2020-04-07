package com.example.olaassignment.view;

import android.app.Activity;
import android.os.Bundle;

import com.example.olaassignment.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        showHomeScreenFragment();
    }

    private void showHomeScreenFragment() {
        getFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out)
                .replace(R.id.base_frame_container,
                        HomeScreenFragment.NewInstance(),
                        HomeScreenFragment.class.getName())
                .addToBackStack(HomeScreenFragment.class.getName())
                .commit();
    }

    private void initViewModel() {
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
