package com.example.olaassignment.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.olaassignment.R;
import com.example.olaassignment.adapter.TrendingReposAdapter;
import com.example.olaassignment.model.RepoEntity;
import com.example.olaassignment.utils.ApiResponse;
import com.example.olaassignment.viewmodel.FeedsViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class HomeScreenFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerViewFeeds;
    ProgressDialog progressDialog;
    private FeedsViewModel mViewModel;
    TrendingReposAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static Fragment NewInstance() {
        return new HomeScreenFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen_layout, container, false);
        initViews(view);
        return view;
    }

    private void initViewModel() {
        mViewModel = ((MainActivity) getActivity()).getMainViewModel();
        mViewModel.init();
        mViewModel.geTrendingApiResponse().observe(this, this::processResponse);
        RefreshData();
    }

    private void initViews(View view) {
        recyclerViewFeeds = view.findViewById(R.id.recycle_view_trending);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initProgressBar();
        adapter = new TrendingReposAdapter();
        RecyclerView.LayoutManager layoutManagerTrending = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerViewFeeds.setLayoutManager(layoutManagerTrending);
        recyclerViewFeeds.setAdapter(adapter);
        initViewModel();
    }

    private void processResponse(ApiResponse apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                progressDialog.show();
                break;
            case SUCCESS:
                progressDialog.dismiss();
                renderSuccessResponse(apiResponse.data);
                break;
            case ERROR:
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Failed to load feeds.. please check internet connection ",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void renderSuccessResponse(JsonElement response) {

        if (!response.isJsonNull()) {
            mSwipeRefreshLayout.setRefreshing(false);
            Gson gson = new Gson();
            JsonElement jsonArray = response.getAsJsonArray();
            ArrayList<RepoEntity> repositories = gson.fromJson(jsonArray, new TypeToken<ArrayList<RepoEntity>>() {
            }.getType());
            adapter.setData(repositories);
            adapter.notifyDataSetChanged();
        } else {
            Log.e(this.getClass().getName(), "Failed to parse response " + response);
        }
    }

    private void initProgressBar() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("loading ...");
        progressDialog.setCancelable(false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onRefresh() {
        RefreshData();
    }
    
    private void RefreshData() {
        mViewModel.hitGitHubApi();
    }

}


