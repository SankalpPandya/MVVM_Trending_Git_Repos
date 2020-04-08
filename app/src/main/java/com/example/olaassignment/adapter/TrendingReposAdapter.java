package com.example.olaassignment.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;

import com.example.olaassignment.R;
import com.example.olaassignment.model.RepoEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrendingReposAdapter extends RecyclerView.Adapter<TrendingReposAdapter.CustomViewHolder> {
    private ArrayList<RepoEntity> data;
    private int position;

    public TrendingReposAdapter() {
        this.data = new ArrayList<>();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView txtTitle;
        private TextView subText;
        private TextView forkCount;
        private TextView language;
        private TextView starCount;
        private ImageView coverImage;
        private Group group;
        private int originalHeight = 0;
        private boolean isViewExpanded = false;

        CustomViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
            coverImage = itemView.findViewById(R.id.coverImage);
            group = itemView.findViewById(R.id.extraInfo);
            group.setVisibility(View.GONE);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            setPosition(getAdapterPosition());
            group.setVisibility(View.VISIBLE);
            notifyItemChanged(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onClick(v);
            return false;
        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("onCreateViewHolder", "onCreateViewHolder");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        view = layoutInflater.inflate(R.layout.trending_repo_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        if (data == null) return;
        holder.txtTitle.setText(data.get(position).getFullName());
        // holder.subText.setText(data.get(position).getFullName());
        String avatarUrl = data.get(position).getAvatarUrl();
        if (!TextUtils.isEmpty(avatarUrl)) {
            Picasso.get().load(avatarUrl).into(holder.coverImage);
        }
    }

    public RepoEntity getItem(int position) {
        return data.get(position);
    }


    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<RepoEntity> newData) {
        data.clear();
        data.addAll(newData);
    }

}