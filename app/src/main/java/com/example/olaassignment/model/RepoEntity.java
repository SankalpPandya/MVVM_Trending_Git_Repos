package com.example.olaassignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

@Entity
public class RepoEntity implements Parcelable {

    @NonNull
    @PrimaryKey
    private Long id;

    private Long page;

    private Long totalPages;

    @SerializedName("name")
    private String name;

    @SerializedName("author")
    private String fullName;

    @SerializedName("avatar")
    private String avatarUrl;

    @Embedded
    private RepoAuthor repoAuthor;

    @SerializedName("url")
    private String htmlUrl;

    @SerializedName("description")
    private String description;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("stars")
    private Long starsCount;

    private Long watchers;
    @SerializedName("forks")
    private Long forks;

    private String language;

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

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
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

    public RepoAuthor getRepoAuthor() {
        return repoAuthor;
    }

    public void setRepoAuthor(RepoAuthor repoAuthor) {
        this.repoAuthor = repoAuthor;
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

    public Long getWatchers() {
        return watchers;
    }

    public void setWatchers(Long watchers) {
        this.watchers = watchers;
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

    public boolean isLastPage() {
        return getPage() >= getTotalPages();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.page);
        dest.writeValue(this.totalPages);
        dest.writeString(this.name);
        dest.writeString(this.fullName);
        dest.writeParcelable(this.repoAuthor, flags);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.description);
        dest.writeString(this.createdAt);
        dest.writeValue(this.starsCount);
        dest.writeValue(this.watchers);
        dest.writeValue(this.forks);
        dest.writeString(this.language);
        dest.writeString(this.avatarUrl);

    }

    public RepoEntity() {
    }

    protected RepoEntity(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.page = (Long) in.readValue(Long.class.getClassLoader());
        this.totalPages = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.fullName = in.readString();
        this.repoAuthor = in.readParcelable(RepoAuthor.class.getClassLoader());
        this.htmlUrl = in.readString();
        this.description = in.readString();
        this.createdAt = in.readString();
        this.starsCount = (Long) in.readValue(Long.class.getClassLoader());
        this.watchers = (Long) in.readValue(Long.class.getClassLoader());
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
}
