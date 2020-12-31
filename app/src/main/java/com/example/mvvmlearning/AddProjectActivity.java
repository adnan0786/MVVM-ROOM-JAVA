package com.example.mvvmlearning;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
    private ProjectModel projectModel;
    private boolean isEdit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initDropDown();
        projectViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProjectViewModel.class);

        if (getIntent().hasExtra("model")) {
            projectModel = getIntent().getParcelableExtra("model");
            binding.edtTitle.setText(projectModel.title);
            binding.edtIssue.setText(String.valueOf(projectModel.issues));
            binding.edtWatcher.setText(String.valueOf(projectModel.watcher));
            isEdit = true;
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnAddProject.setOnClickListener(view -> {

            if (isEdit) {
                title = binding.edtTitle.getText().toString().trim();
                watcher = Integer.parseInt(binding.edtWatcher.getText().toString().trim());
                issues = Integer.parseInt(binding.edtIssue.getText().toString().trim());

                projectModel.title = title;
                projectModel.issues = issues;
                projectModel.watcher = watcher;
                projectModel.language = lang;

                projectViewModel.updateProject(projectModel);
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                finish();
            } else {

                title = binding.edtTitle.getText().toString().trim();
                watcher = Integer.parseInt(binding.edtWatcher.getText().toString().trim());
                issues = Integer.parseInt(binding.edtIssue.getText().toString().trim());

                projectModel = new ProjectModel();
                projectModel.title = title;
                projectModel.issues = issues;
                projectModel.watcher = watcher;
                projectModel.language = lang;
                projectViewModel.insertProject(projectModel);
                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();

                finish();
            }


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