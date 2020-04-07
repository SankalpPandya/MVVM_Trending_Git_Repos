package com.example.olaassignment.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.olaassignment.model.RepoEntity;

import java.util.List;

@Dao
public interface GithubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertRepositories(List<RepoEntity> githubEntities);

    @Query("SELECT * FROM RepoEntity where page = :page")
    List<RepoEntity> getRepositoriesByPage(Long page);
}
