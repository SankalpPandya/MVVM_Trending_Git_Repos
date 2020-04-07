package com.example.olaassignment.view;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.olaassignment.R;
import com.example.olaassignment.viewmodel.FeedsViewModel;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class HomeScreenFragment extends Fragment {

    private RecyclerView recyclerViewFeeds;
    ProgressDialog progressDialog;
    private FeedsViewModel mViewModel;

    public static android.app.Fragment NewInstance() {
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
    }

    private void initViews(View view) {
        recyclerViewFeeds = view.findViewById(R.id.recycle_view_trending);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        initProgressBar();
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

}


