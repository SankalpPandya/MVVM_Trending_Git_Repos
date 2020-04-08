package com.example.olaassignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

public class RepoEntity implements Parcelable {

    @NonNull
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("author")
    private String fullName;

    @SerializedName("avatar")
    private String avatarUrl;


    @SerializedName("url")
    private String htmlUrl;

    @SerializedName("description")
    private String description;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("stars")
    private Long starsCount;

    @SerializedName("forks")
    private Long forks;

    private String language;

    private boolean isExpanded;

    @NonNull
    public Long getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(Long starsCount) {
        this.starsCount = starsCount;
    }

    public Long getForks() {
        return forks;
    }

    public void setForks(Long forks) {
        this.forks = forks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.fullName);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.description);
        dest.writeString(this.createdAt);
        dest.writeValue(this.starsCount);
        dest.writeValue(this.forks);
        dest.writeString(this.language);
        dest.writeString(this.avatarUrl);

    }

    public RepoEntity() {
    }

    protected RepoEntity(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.fullName = in.readString();
        this.htmlUrl = in.readString();
        this.description = in.readString();
        this.createdAt = in.readString();
        this.starsCount = (Long) in.readValue(Long.class.getClassLoader());
        this.forks = (Long) in.readValue(Long.class.getClassLoader());
        this.language = in.readString();
        this.avatarUrl = in.readString();
    }

    public static final Creator<RepoEntity> CREATOR = new Creator<RepoEntity>() {
        @Override
        public RepoEntity createFromParcel(Parcel source) {
            return new RepoEntity(source);
        }

        @Override
        public RepoEntity[] newArray(int size) {
            return new RepoEntity[size];
        }
    };

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
