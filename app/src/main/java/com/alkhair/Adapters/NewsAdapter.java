package com.alkhair.Adapters;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.Models.NewsResponseModel;
import com.alkhair.R;
import com.alkhair.databinding.CampItemBinding;
import com.alkhair.databinding.NewItemBinding;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.ui.campaign.CampaignDetailsFragment;
import com.alkhair.ui.campaign.CampaignFragment;
import com.alkhair.ui.news.NewsDetailsFragment;
import com.alkhair.ui.news.NewsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    FragmentActivity activity;
    NewsFragment newsFragment;
    List<NewsResponseModel.ResultBean> result;
    private long mLastClickTime=0;

    public NewsAdapter(FragmentActivity activity, NewsFragment newsFragment, List<NewsResponseModel.ResultBean> result) {
        this.activity=activity;
        this.newsFragment=newsFragment;
        this.result=result;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.new_item, parent, false);
        return new NewsAdapter.NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        PreferenceHelper helper = new PreferenceHelper(activity);
        Picasso.with(activity).load(result.get(position).getNewsImagePath()).error(R.drawable.no_image).into(holder.binding.Img);


        holder.binding.detailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",  result.get(position));
                Fragment fragment  = new NewsDetailsFragment();
                fragment.setArguments(bundle);
                FragmentTransaction mFragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.fragment, fragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();


            }
        });


        if (helper.getLang().equals("ar")) {
            if (result.get(position).getNewsHeadline_Ar() != null) {
                holder.binding.name.setText(result.get(position).getNewsHeadline_Ar());
                holder.binding.details.setText(result.get(position).getNewsShortDescription_Ar());
            }
        } else {
            if (result.get(position).getNewsHeadline_En() != null) {
                holder.binding.name.setText(result.get(position).getNewsHeadline_En().toString());
                holder.binding.details.setText(result.get(position).getNewsShortDescription_En().toString());
            } else {
                holder.binding.name.setText(result.get(position).getNewsHeadline_Ar());
                holder.binding.details.setText(result.get(position).getNewsShortDescription_Ar());

            }
        }

    }

    @Override
    public int getItemCount() {
        return result == null ? 0 : result.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        final NewItemBinding binding;

        NewsViewHolder(NewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
