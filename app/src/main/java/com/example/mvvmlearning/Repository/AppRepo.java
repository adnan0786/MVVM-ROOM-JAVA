package com.example.mvvmlearning.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mvvmlearning.Database.AppDatabase;
import com.example.mvvmlearning.ProjectModel;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

    public List<ProjectModel> getAllProjectFuture() throws ExecutionException, InterruptedException {

        Callable<List<ProjectModel>> callable = new Callable<List<ProjectModel>>() {
            @Override
            public List<ProjectModel> call() throws Exception {
                return appDatabase.projectDao().getAllProjectsFuture();
            }
        };

        Future<List<ProjectModel>> future = Executors.newSingleThreadExecutor().submit(callable);
        return future.get();


    }

    public LiveData<List<ProjectModel>> getAllProjectLive()  {

        return appDatabase.projectDao().getAllProjectsLive();


    }

}
