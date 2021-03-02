package com.alkhair.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.Models.ProjectDetailsResponseModel;
import com.alkhair.R;
import com.alkhair.databinding.CampItemBinding;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.campaign.CampaignDetailsFragment;
import com.alkhair.ui.campaign.CampaignFragment;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public  class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.CampViewHolder> {
    FragmentActivity activity;
    CampaignFragment campaignFragment;
    List<ProjectDetailsResponseModel.ResultBean> result;


    PreferenceHelper helper;
    private long mLastClickTime=0;
    private Context context;

    public CampaignAdapter(FragmentActivity activity, CampaignFragment campaignFragment, List<ProjectDetailsResponseModel.ResultBean> result) {
        this.activity=activity;
        this.campaignFragment=campaignFragment;
        this.result=result;
    }

    @NonNull
    @Override
    public CampaignAdapter.CampViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CampItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.camp_item, parent, false);
        return new CampaignAdapter.CampViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CampaignAdapter.CampViewHolder holder, int position) {

      Picasso.with(activity).load(result.get(position).getMediaPath()).error(R.drawable.no_image).into(holder.binding.Img);

        holder.binding.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                Bundle bundle = new Bundle();
                 bundle.putSerializable("data",  result.get(position));
                Fragment fragment  = new CampaignDetailsFragment();
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

   public class CampViewHolder extends RecyclerView.ViewHolder {

        final CampItemBinding binding;

        CampViewHolder(CampItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
