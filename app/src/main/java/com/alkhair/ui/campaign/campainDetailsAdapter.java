package com.alkhair.ui.campaign;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.Models.ProjectDetailsResponseModel;
import com.alkhair.R;
import com.alkhair.databinding.ProjectDetailsItemBinding;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.ui.MyDonations.DonationsFragment;
import com.alkhair.ui.payment.PaymentWaysFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class campainDetailsAdapter extends RecyclerView.Adapter<campainDetailsAdapter.ProjectiewHolder> {

    FragmentActivity activity;
    Fragment campaginFragment;
    ArrayList<CampaginResponseModel.ResultBean> result;
    PreferenceHelper helper;
    private long mLastClickTime = 0;
    DonationsFragment donationsFragment;

    public campainDetailsAdapter(Fragment campaginFragment, ArrayList<CampaginResponseModel.ResultBean>  result, FragmentActivity activity) {
        this.campaginFragment = campaginFragment;
        this.result = result;
        this.activity = activity;

    }



    @NonNull
    @Override
    public campainDetailsAdapter.ProjectiewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProjectDetailsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.project_details_item, parent, false);
        return new campainDetailsAdapter.ProjectiewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull campainDetailsAdapter.ProjectiewHolder holder, int position) {



        helper = new PreferenceHelper(activity);
        holder.binding.type.setVisibility(View.GONE);
        if (campaginFragment != null) {

           // holder.binding.linear.setVisibility(View.VISIBLE);
            holder.binding.date.setVisibility(View.GONE);
            holder.binding.donation.setVisibility(View.VISIBLE);
            holder.binding.detailstext.setVisibility(View.GONE);


            if (helper.getLang().equals("ar")) {
                if (result.get(position).getProjectName_Ar() != null) {
                    holder.binding.proName.setText(result.get(position).getProjectName_Ar());
                    holder.binding.name.setText(result.get(position).getProjectName_Ar());
                  //  holder.binding.typeValue.setText(String.valueOf(result.getCharityProjectsList().get(position).getCharityName_Ar()));
                }
            } else {
                if (result.get(position).getProjectName_En()  != null) {
                 //   holder.binding.proName.setText(result.getCharityProjectsList().get(position).getProjectName_En().toString());
                    holder.binding.proName.setText(result.get(position).getProjectName_En());
                   // holder.binding.typeValue.setText(String.valueOf(result.get(position).getCharityName_En()));
                } else {
                    holder.binding.proName.setText(result.get(position).getProjectName_Ar());
                    holder.binding.name.setText(result.get(position).getProjectName_Ar());
                  //  holder.binding.typeValue.setText(String.valueOf(result.get(position).getCharityName_Ar()));

                }
            }
            holder.binding.cashCollect.setText(String.valueOf(result.get(position).getCollectedAmount()));
            holder.binding.price.setText(String.valueOf(result.get(position).getTargetedAmount()));


             double project_cost = (result.get(position).getTargetedAmount());
             double  cash_Collect =  (result.get(position).getCollectedAmount());

             double res = project_cost - cash_Collect;
             holder.binding.remainingValue.setText(String.valueOf(res));


            holder.binding.donation.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    String input_amount = holder.binding.amount.getText().toString().trim(); ;
                if (input_amount == "0" || input_amount == ""){
                    Toast.makeText(view.getContext(), "Clicked Laugh Vote", Toast.LENGTH_SHORT).show();

                }
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("amount", input_amount);
                    bundle.putSerializable("data", result.get(position));

                    Fragment fragment  = new PaymentWaysFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction mFragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.fragment, fragment);
                    mFragmentTransaction.addToBackStack(null);
                    mFragmentTransaction.commit();
                    helper.setDonation_amount(String.valueOf(result.get(position).getCollectedAmount()));

                //    projectsFragment.selectPayment();
                }
            });
        }

//        if (projectsFragment  donationsFragment != null) {
//            holder.binding.linear.setVisibility(View.GONE);
//            holder.binding.date.setVisibility(View.VISIBLE);
//            holder.binding.donation.setVisibility(View.GONE);
//            holder.binding.proName.setText(data.getCharityProjectsList().get(position).getProjectName_Ar());
//            holder.binding.tID.setText(activity.getResources().getString(R.string.tID));
//            holder.binding.value.setText(activity.getResources().getString(R.string.value));
//
//
//        }
    }

    @Override
    public int getItemCount() {

            return result.size();



    }

    public class ProjectiewHolder extends RecyclerView.ViewHolder {

        final ProjectDetailsItemBinding binding;

        ProjectiewHolder(ProjectDetailsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}