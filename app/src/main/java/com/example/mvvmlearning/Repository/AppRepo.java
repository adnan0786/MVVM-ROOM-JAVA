package com.example.mvvmlearning.Repository;

import android.content.Context;

import com.example.mvvmlearning.Database.AppDatabase;
import com.example.mvvmlearning.ProjectModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepo {

    private AppDatabase appDatabase;
    private Executor executor = Executors.newSingleThreadExecutor();

    public AppRepo(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }


    public void insertProject(ProjectModel projectModel) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.projectDao().insertProject(projectModel);
            }
        });

    }

    public void updateProject(ProjectModel projectModel) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.projectDao().updateProject(projectModel);
            }
        });
    }

    public void deleteProject(ProjectModel projectModel) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.projectDao().deleteProject(projectModel);
            }
        });
    }

    public List<ProjectModel> getAllProject() {
        return appDatabase.projectDao().getAllProjects();
    }

}
