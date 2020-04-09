package com.example.olaassignment.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
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
        holder.updateFields(position);
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

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView txtTitle;
        private TextView subText;
        private TextView forkCount;
        private TextView description;
        private TextView language;
        private TextView starCount;
        private ImageView coverImage;
        private ImageView languageImage;
        private Group group;
        RepoEntity repo;

        CustomViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
            coverImage = itemView.findViewById(R.id.coverImage);
            subText = itemView.findViewById(R.id.subtext);
            group = itemView.findViewById(R.id.extraInfo);
            forkCount = itemView.findViewById(R.id.fork);
            starCount = itemView.findViewById(R.id.star);
            description = itemView.findViewById(R.id.description);
            language = itemView.findViewById(R.id.language);
            languageImage = itemView.findViewById(R.id.languageimage);

            GradientDrawable drawable = (GradientDrawable) itemView.getResources().getDrawable(R.drawable.ic_circle);
            drawable.setColor(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimary));
            languageImage.setImageDrawable(drawable);
            group.setVisibility(View.GONE);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        public void updateFields(int position) {

            if (position <= RecyclerView.NO_POSITION || position >= data.size()) return;

            repo = data.get(position);
            boolean expanded = repo.isExpanded();
            group.setVisibility(expanded ? View.VISIBLE : View.GONE);

            txtTitle.setText(repo.getFullName());
            subText.setText(repo.getName());
            description.setText(repo.getDescription());
            forkCount.setText(repo.getForks().toString());
            starCount.setText(repo.getStarsCount().toString());
            language.setText(repo.getLanguage());
            String avatarUrl = data.get(position).getAvatarUrl();
            if (!TextUtils.isEmpty(avatarUrl)) {
                Picasso.get().load(avatarUrl).into(coverImage);
            }
        }

        @Override
        public void onClick(View v) {
            boolean expanded = repo.isExpanded();
            repo.setExpanded(!expanded);
            notifyItemChanged(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onClick(v);
            return false;
        }
    }

}