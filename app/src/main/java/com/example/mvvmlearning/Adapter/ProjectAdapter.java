package com.example.mvvmlearning.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlearning.OnClickItemInterface;
import com.example.mvvmlearning.ProjectModel;
import com.example.mvvmlearning.R;
import com.example.mvvmlearning.databinding.ProjectItemLayoutBinding;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    List<ProjectModel> projectModelList;
    private OnClickItemInterface onClickItemInterface;

    public ProjectAdapter(OnClickItemInterface onClickItemInterface) {
        this.onClickItemInterface = onClickItemInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProjectItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.project_item_layout, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (projectModelList != null) {
            ProjectModel projectModel = projectModelList.get(position);

            holder.binding.setProjectModel(projectModel);
            holder.binding.setListener(onClickItemInterface);
        }


    }

    @Override
    public int getItemCount() {

        if (projectModelList != null)
            return projectModelList.size();
        else return 0;
    }

    public void setProjects(List<ProjectModel> projects) {
        projectModelList = projects;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ProjectItemLayoutBinding binding;

        public ViewHolder(@NonNull ProjectItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
