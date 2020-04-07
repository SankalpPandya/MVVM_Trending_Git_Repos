package com.example.olaassignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;

@Entity
public class RepoAuthor implements Parcelable {

    private String login;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
    }

    public RepoAuthor() {
    }

    protected RepoAuthor(Parcel in) {
        this.login = in.readString();
    }

    public static final Creator<RepoAuthor> CREATOR = new Creator<RepoAuthor>() {
        @Override
        public RepoAuthor createFromParcel(Parcel source) {
            return new RepoAuthor(source);
        }

        @Override
        public RepoAuthor[] newArray(int size) {
            return new RepoAuthor[size];
        }
    };
}
