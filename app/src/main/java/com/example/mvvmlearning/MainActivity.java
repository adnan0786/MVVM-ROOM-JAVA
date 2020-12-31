package com.example.mvvmlearning;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlearning.Adapter.ProjectAdapter;
import com.example.mvvmlearning.ViewModel.ProjectViewModel;
import com.example.mvvmlearning.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickItemInterface {

    private ActivityMainBinding binding;
    private ProjectViewModel projectViewModel;
    private ProjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.projectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectAdapter(this);
        binding.projectRecyclerView.setAdapter(adapter);

        binding.addProject.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddProjectActivity.class));
        });


        projectViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProjectViewModel.class);

//        try {
//            adapter.setProjects(projectViewModel.getAllProjectFuture());
//        } catch (Exception exception) {
//            Log.d("TAG", "onCreate: " + exception);
//        }

        projectViewModel.getAllProjectLive().observe(MainActivity.this, new Observer<List<ProjectModel>>() {
            @Override
            public void onChanged(List<ProjectModel> projectModelList) {
                if (projectModelList != null) {
                    adapter.setProjects(projectModelList);

                }
            }
        });


    }

    @Override
    public void onClickItem(ProjectModel projectModel, boolean isEdit) {


        if (isEdit) {
            Intent intent = new Intent(MainActivity.this, AddProjectActivity.class);
            intent.putExtra("model", projectModel);
            startActivity(intent);
        } else {
            projectViewModel.deleteProject(projectModel);
        }

    }
}