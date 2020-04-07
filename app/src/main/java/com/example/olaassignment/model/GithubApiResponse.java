package com.example.olaassignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GithubApiResponse implements Parcelable {

    public GithubApiResponse() {
        this.items = new ArrayList<>();
    }

    @SerializedName("total_count")
    private Long totalCount;

    private List<RepoEntity> items;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<RepoEntity> getItems() {
        return items;
    }

    public void setItems(List<RepoEntity> items) {
        this.items = items;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.totalCount);
        dest.writeTypedList(this.items);
    }

    protected GithubApiResponse(Parcel in) {
        this.totalCount = (Long) in.readValue(Long.class.getClassLoader());
        this.items = in.createTypedArrayList(RepoEntity.CREATOR);
    }

    public static final Creator<GithubApiResponse> CREATOR = new Creator<GithubApiResponse>() {
        @Override
        public GithubApiResponse createFromParcel(Parcel source) {
            return new GithubApiResponse(source);
        }

        @Override
        public GithubApiResponse[] newArray(int size) {
            return new GithubApiResponse[size];
        }
    };
}
