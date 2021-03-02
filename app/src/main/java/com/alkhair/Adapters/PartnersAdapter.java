package com.alkhair.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.Models.PartnersResponseModel;
import com.alkhair.R;
import com.alkhair.databinding.CampItemBinding;
import com.alkhair.databinding.PartnerItemBinding;
import com.alkhair.ui.campaign.CampaignDetailsFragment;
import com.alkhair.ui.campaign.CampaignFragment;
import com.alkhair.ui.partners.PartnersFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codesroots.tourismgroup.presentation.screens.details.ChairtyDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class PartnersAdapter extends RecyclerView.Adapter<PartnersAdapter.PartnerViewHolder> {
    FragmentActivity activity;
    PartnersFragment partnersFragment;
    List<PartnersResponseModel.ResultBean>  result;


    public PartnersAdapter(FragmentActivity activity, PartnersFragment partnersFragment, List<PartnersResponseModel.ResultBean> result) {
        this.activity =activity;
        this.partnersFragment = partnersFragment;
        this.result=result;
    }

    @NonNull
    @Override
    public PartnersAdapter.PartnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PartnerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.partner_item, parent, false);
        return new PartnersAdapter.PartnerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PartnersAdapter.PartnerViewHolder holder, int position) {

     //   Picasso.with(activity).load(result.get(position).getCharityLogoPath()).error(R.drawable.no_image).into(holder.binding.Img);
       loudImage(activity,holder.binding.Img,result.get(position).getCharityLogoPath());
        holder.binding.Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("chairty_id",  result.get(position).getCharityId());
                Fragment fragment  = new ChairtyDetailsFragment();
                fragment.setArguments(bundle);
                FragmentTransaction mFragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.fragment, fragment);
                mFragmentTransaction.addToBackStack("go_to_home");
                mFragmentTransaction.commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        return result == null ? 0 : result.size();
    }

    public class PartnerViewHolder extends RecyclerView.ViewHolder {

        final PartnerItemBinding binding;

        PartnerViewHolder(PartnerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    void  loudImage(Context context,ImageView imag,  String url) {
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions()
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)).load(url).into(imag);
    }

}
