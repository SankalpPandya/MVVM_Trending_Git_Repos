package com.example.olaassignment;

import com.example.olaassignment.model.RepoEntity;

import java.util.ArrayList;
import java.util.List;

public class MockTestUtil {

    public static List<RepoEntity> mockRepositories() {
        List<RepoEntity> repositories = new ArrayList<>();
        RepoEntity repository1 = new RepoEntity();
        repository1.setId(1l);
        repository1.setName("Test1");
        repository1.setForks(555L);
        repository1.setName("AndroidTest1");
        repositories.add(repository1);
        RepoEntity repository2 = new RepoEntity();
        repository2.setId(2l);
        repository1.setName("Test2");
        repository1.setForks(542455L);
        repository2.setName("AndroidTest2");
        repositories.add(repository2);

        RepoEntity repository3 = new RepoEntity();
        repository3.setId(3l);
        repository1.setName("Test3");
        repository1.setForks(5565L);
        repository3.setName("AndroidTest3");
        repositories.add(repository3);
        return repositories;
    }
}
