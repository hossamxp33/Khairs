package com.alkhair.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alkhair.Models.DonationsHistoryRespopnse;
import com.alkhair.Models.ProjectDetailsResponseModel;
import com.alkhair.R;
import com.alkhair.databinding.ProjectDetailsItemBinding;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.MyDonations.DonationsFragment;
import com.alkhair.ui.campaign.CampaignDetailsFragment;
import com.alkhair.ui.payment.PaymentWaysFragment;
import com.alkhair.ui.projects.ProjectDetailsFragment;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProjectDetailsAdapter extends RecyclerView.Adapter<ProjectDetailsAdapter.ProjectiewHolder> {
    SweetAlertDialog pDialog  ;

    FragmentActivity activity;
    ProjectDetailsFragment projectsFragment;
    ArrayList<ProjectDetailsResponseModel.ResultBean> result;
    PreferenceHelper helper;
    private long mLastClickTime = 0;
    DonationsFragment donationsFragment;
    ArrayList<DonationsHistoryRespopnse.ResultBean> data;
    String input_amount;
    public ProjectDetailsAdapter(ProjectDetailsFragment projectsFragment, ArrayList<ProjectDetailsResponseModel.ResultBean> result, FragmentActivity activity) {
        this.projectsFragment = projectsFragment;
        this.result = result;
        this.activity = activity;
    }

    public ProjectDetailsAdapter(DonationsFragment donationsFragment, ArrayList<DonationsHistoryRespopnse.ResultBean> data, FragmentActivity activity) {
        this.activity = activity;
        this.donationsFragment = donationsFragment;
        this.data = data;
    }

    @NonNull
    @Override
    public ProjectDetailsAdapter.ProjectiewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ProjectDetailsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.project_details_item, parent, false);

        return new ProjectDetailsAdapter.ProjectiewHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull ProjectDetailsAdapter.ProjectiewHolder holder, int position) {

        helper = new PreferenceHelper(activity);

        if (projectsFragment != null) {

           // holder.binding.linear.setVisibility(View.VISIBLE);
            holder.binding.date.setVisibility(View.GONE);
            holder.binding.donation.setVisibility(View.VISIBLE);
            if (helper.getLang().equals("ar")) {
                if (result.get(position).getCharityName_Ar() != null) {
                    holder.binding.proName.setText(result.get(position).getProjectName_Ar());
                    holder.binding.name.setText(result.get(position).getProjectName_Ar());
                    holder.binding.typeValue.setText(String.valueOf(result.get(position).getCharityName_Ar()));
                }
            } else {
                if (result.get(position).getCharityName_En() != null) {
                    holder.binding.proName.setText(result.get(position).getProjectName_En().toString());
                    holder.binding.name.setText(result.get(position).getProjectName_En().toString());
                    holder.binding.typeValue.setText(String.valueOf(result.get(position).getCharityName_En()));
                } else {
                    holder.binding.proName.setText(result.get(position).getProjectName_Ar());
                    holder.binding.name.setText(result.get(position).getProjectName_Ar());
                    holder.binding.typeValue.setText(String.valueOf(result.get(position).getCharityName_Ar()));

                }
            }
            holder.binding.cashCollect.setText(String.valueOf(result.get(position).getCollectedAmount()));
            holder.binding.price.setText(String.valueOf(result.get(position).getTargetedAmount()));


             Double  project_cost = (result.get(position).getTargetedAmount());

             Double  cash_Collect =  (result.get(position).getCollectedAmount());

             double res = project_cost - cash_Collect;
             holder.binding.remainingValue.setText(String.valueOf(res));
            holder.binding.detailstext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  alertDialogBuilder.setMessage(result.get(position).getProjectDescription_Ar())
                    pDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
                    pDialog.setTitleText(R.string.details);
                    pDialog.setContentText(result.get(position).getProjectDescription_Ar());
                    pDialog.setConfirmText("OK");
                    pDialog.show();




                }
            });
            holder.binding.donation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     input_amount = holder.binding.amount.getText().toString().trim(); ;
                    if (input_amount.equals("0")  || input_amount.equals("")){

                        Toast.makeText(activity, R.string.entervalue, Toast.LENGTH_SHORT).show();

                    }else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", result.get(position));
                    bundle.putSerializable("amount", input_amount);
                    Fragment fragment  = new PaymentWaysFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction mFragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.fragment, fragment);
                    mFragmentTransaction.commit();
                    mFragmentTransaction.addToBackStack(null);
                    helper.setDonation_amount(String.valueOf(result.get(position).getCollectedAmount()));
                //    projectsFragment.selectPayment();
                }
                }
            });
        }

        if (donationsFragment != null) {
//            holder.binding.linear.setVisibility(View.GONE);
            holder.binding.date.setVisibility(View.VISIBLE);
            holder.binding.donation.setVisibility(View.GONE);
            holder.binding.amount.setVisibility(View.GONE);
            holder.binding.type.setVisibility(View.GONE);
            holder.binding.typeValue.setVisibility(View.GONE);
            holder.binding.remainingValue.setVisibility(View.GONE);
            holder.binding.remainingAmount.setVisibility(View.GONE);
            holder.binding.detailstext.setVisibility(View.GONE);
            holder.binding.cashCollect.setText(data.get(position).getPaymentTransactionId());
            holder.binding.date.setText(data.get(position).getPaymentDateDisplay());
            holder.binding.proName.setText(data.get(position).getProjectName());
            holder.binding.name.setText(String.valueOf(data.get(position).getPaymentTransactionId()));
             holder.binding.tID.setText(activity.getResources().getString(R.string.tID));
            holder.binding.value.setText(activity.getResources().getString(R.string.value));
            holder.binding.price.setText(String.valueOf(data.get(position).getDonationAmount()));

        }
    }

    @Override
    public int getItemCount() {
        if (projectsFragment != null) {
            return result.size();
        }
        if (donationsFragment != null) {
            return data.size();
        }
        return 0;
    }

    public class ProjectiewHolder extends RecyclerView.ViewHolder {

        final ProjectDetailsItemBinding binding;

        ProjectiewHolder(ProjectDetailsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}