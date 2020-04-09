package com.example.olaassignment.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.olaassignment.R;
import com.example.olaassignment.adapter.TrendingReposAdapter;
import com.example.olaassignment.utils.ApiResponse;
import com.example.olaassignment.utils.Util;
import com.example.olaassignment.viewmodel.TrendingReposViewModel;
import com.google.gson.JsonElement;

import java.util.Objects;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class HomeScreenFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerViewFeeds;
    private ProgressDialog progressDialog;
    private TrendingReposViewModel mViewModel;
    private TrendingReposAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ConstraintLayout mErorStateLayout;

    static Fragment NewInstance() {
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
        mViewModel = ((MainActivity) Objects.requireNonNull(getActivity())).getMainViewModel();
        mViewModel.init(getActivity().getApplicationContext());
        mViewModel.geTrendingApiResponse().observe(this, this::processResponse);
        RefreshData(false);
    }

    private void initViews(View view) {
        recyclerViewFeeds = view.findViewById(R.id.recycle_view_trending);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        Button mRetryButton = view.findViewById(R.id.retry);
        mErorStateLayout = view.findViewById(R.id.error_state_screen);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRetryButton.setOnClickListener(v -> RefreshData(true));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initProgressBar();
        adapter = new TrendingReposAdapter();
        RecyclerView.LayoutManager layoutManagerTrending = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerViewFeeds.setLayoutManager(layoutManagerTrending);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewFeeds.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerViewFeeds.addItemDecoration(dividerItemDecoration);
        ((SimpleItemAnimator) recyclerViewFeeds.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerViewFeeds.setAdapter(adapter);
        recyclerViewFeeds.setHasFixedSize(true);
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
                ShowErrorState();
                break;
            default:
                break;
        }
    }

    private void ShowErrorState() {
        recyclerViewFeeds.setVisibility(View.GONE);
        mErorStateLayout.setVisibility(View.VISIBLE);
    }

    private void renderSuccessResponse(JsonElement response) {

        if (!response.isJsonNull()) {
            mErorStateLayout.setVisibility(View.GONE);
            recyclerViewFeeds.setVisibility(View.VISIBLE);
            mSwipeRefreshLayout.setRefreshing(false);
            adapter.setData(Util.GetRepoEntitiesFromJsonElements(response));
            adapter.notifyDataSetChanged();
        } else {
            Log.e(this.getClass().getName(), "Failed to parse response " + response);
            ShowErrorState();
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
        RefreshData(true);
    }

    private void RefreshData(boolean isForceFetch) {
        mViewModel.hitGitHubApi(isForceFetch);
    }
}


