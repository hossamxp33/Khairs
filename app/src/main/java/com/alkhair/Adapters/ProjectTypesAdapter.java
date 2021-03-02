package com.alkhair.Adapters;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alkhair.Models.ProjecttypesResponseModel;
import com.alkhair.R;
import com.alkhair.databinding.ProjectTypeItemBinding;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.ui.payment.PaymentWaysFragment;
import com.alkhair.ui.projects.ProjectDetailsFragment;
import com.alkhair.ui.projects.ProjectsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectTypesAdapter extends RecyclerView.Adapter<ProjectTypesAdapter.ProjectiewHolder> {
    FragmentActivity activity;
    ProjectsFragment projectsFragment;
    List<ProjecttypesResponseModel.ResultBean> result;
    PreferenceHelper helper;
    private long mLastClickTime=0;

    public ProjectTypesAdapter(FragmentActivity activity, ProjectsFragment projectsFragment, List<ProjecttypesResponseModel.ResultBean> result) {
        this.activity = activity;
        this.projectsFragment = projectsFragment;
        this.result = result;
    }

    @NonNull
    @Override
    public ProjectTypesAdapter.ProjectiewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProjectTypeItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.project_type_item, parent, false);
        return new ProjectTypesAdapter.ProjectiewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectTypesAdapter.ProjectiewHolder holder, int position) {
        helper = new PreferenceHelper(activity);
        if (result.get(position).getLogo() != null) {
            holder.binding.progressBar.setVisibility(View.GONE);
            Picasso.with(activity).load(result.get(position).getLogo()).into(holder.binding.proImage);
        }
        if (helper.getLang().equals("ar")) {
            if (result.get(position).getName_ar() != null) {
                holder.binding.name.setText(result.get(position).getName_ar());
            }
        } else {
            if (result.get(position).getName_en() != null) {
                holder.binding.name.setText(result.get(position).getName_en());
            } else {
                holder.binding.name.setText(result.get(position).getName_ar());

            }
        }
        holder.binding.proImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }

                mLastClickTime = SystemClock.elapsedRealtime();
                helper.addData("projectID",String.valueOf(result.get(position).getID()));
                helper.addData("campaignDetailsEN",result.get(position).getName_en());
                helper.addData("campaignDetailsAR",result.get(position).getName_ar());
                Bundle bundle = new Bundle();
                Fragment fragment  = new ProjectDetailsFragment();
                bundle.putSerializable("project_id",result.get(position).getID());
                fragment.setArguments(bundle);
                FragmentTransaction mFragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.fragment, fragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return result == null ? 0 : result.size();
    }

    public class ProjectiewHolder extends RecyclerView.ViewHolder {

        final ProjectTypeItemBinding binding;

        ProjectiewHolder(ProjectTypeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
