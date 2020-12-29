package com.example.mvvmlearning;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmlearning.ViewModel.ProjectViewModel;
import com.example.mvvmlearning.databinding.ActivityAddProjectBinding;

public class AddProjectActivity extends AppCompatActivity {

    private ActivityAddProjectBinding binding;
    private String title, lang;
    private int watcher, issues;
    private String[] languages = {"Java", "Kotlin", "PHP", "Dart", "Flutter"};
    private ProjectViewModel projectViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initDropDown();
        projectViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProjectViewModel.class);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnAddProject.setOnClickListener(view -> {

            title = binding.edtTitle.getText().toString().trim();
            watcher = Integer.parseInt(binding.edtWatcher.getText().toString().trim());
            issues = Integer.parseInt(binding.edtIssue.getText().toString().trim());

            ProjectModel projectModel = new ProjectModel();
            projectModel.title = title;
            projectModel.issues = issues;
            projectModel.watcher = watcher;
            projectModel.language = lang;
            projectViewModel.insertProject(projectModel);

            finish();

        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();

    }

    private void initDropDown() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, languages);
        binding.edtLanguage.setAdapter(arrayAdapter);
        binding.edtLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                lang = (String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}