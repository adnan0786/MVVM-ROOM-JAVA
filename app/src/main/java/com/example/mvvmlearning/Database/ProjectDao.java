package com.example.mvvmlearning.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmlearning.ProjectModel;

import java.util.List;

@Dao
public interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProject(ProjectModel projectModel);

    @Update
    void updateProject(ProjectModel projectModel);

    @Delete
    void deleteProject(ProjectModel projectModel);

    @Query("SELECT * FROM project")
    List<ProjectModel> getAllProjects();

    @Query("SELECT * FROM project WHERE pId=:id")
    ProjectModel getProject(int id);

//    @Delete
//    List<int> deleteListOfProject(List<ProjectModel> projectModelList)
//
//    @Update
//    List<int> updateListOfProject(List<ProjectModel> projectModelList)
//
//    @Insert
//    List<int> insertListOfProject(List<ProjectModel> projectModelList)
}
