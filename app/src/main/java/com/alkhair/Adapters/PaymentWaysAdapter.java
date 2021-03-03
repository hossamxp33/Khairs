package com.alkhair.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.Models.PaymentWaysResponse;
import com.alkhair.R;
import com.alkhair.databinding.CampItemBinding;
import com.alkhair.databinding.WayItemBinding;
import com.alkhair.helper.Application;
import com.alkhair.ui.campaign.CampaignFragment;
import com.alkhair.ui.payment.PaymentWaysFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentWaysAdapter extends RecyclerView.Adapter<PaymentWaysAdapter.WayViewHolder> {
    FragmentActivity activity;
    PaymentWaysFragment paymentWaysFragment;
    List<PaymentWaysResponse.ResultBean> result;
    private Application mCommunicator;
    private int selectedPosition = -1;
    private RadioButton selectedRadioButton;




    public PaymentWaysAdapter(FragmentActivity activity, PaymentWaysFragment paymentWaysFragment, List<PaymentWaysResponse.ResultBean> result) {
        this.activity =activity;
        this.paymentWaysFragment=paymentWaysFragment;
        this.result=result;

    }

    @NonNull
    @Override
    public PaymentWaysAdapter.WayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WayItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.way_item, parent, false);
        return new PaymentWaysAdapter.WayViewHolder(binding);
    }
    interface OnTextClickListener {
        void onTextClick(List<PaymentWaysResponse.ResultBean> result);
    }
    @Override
    public void onBindViewHolder(@NonNull PaymentWaysAdapter.WayViewHolder holder, int position) {

        Picasso.with(activity).load(result.get(position).getPaymentOptionImagePath()).error(R.drawable.no_image).into(holder.binding.path);


        RadioButton radioButton = holder.binding.name;
        radioButton.setChecked(result.get(position).getChecked());
        radioButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               // Set unchecked all other elements in the list, so to display only one selected radio button at a time
                                               for (PaymentWaysResponse.ResultBean model : result)
                                                   model.setChecked(false);
                                               // Set "checked" the model associated to the clicked radio button
                                               result.get(position).setChecked(true);

                                               // If current view (RadioButton) differs from previous selected radio button, then uncheck selectedRadioButton
                                               if (null != selectedRadioButton && !v.equals(selectedRadioButton))
                                                   selectedRadioButton.setChecked(false);

                                               // Replace the previous selected radio button with the current (clicked) one, and "check" it
                                               selectedRadioButton = (RadioButton) v;
                                               selectedRadioButton.setChecked(true);
                                               paymentWaysFragment.setRadioIndex(result.get(position).getPaymentTypeId());
                                           }
                                           });
                                       }


    @Override
    public int getItemCount() {
        return result == null ? 0 : result.size();
    }

    public class WayViewHolder extends RecyclerView.ViewHolder {

        final WayItemBinding binding;

        WayViewHolder(WayItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
