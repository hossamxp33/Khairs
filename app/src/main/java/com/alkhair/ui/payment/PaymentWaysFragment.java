package com.alkhair.ui.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import com.alkhair.Adapters.PaymentWaysAdapter;
import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.Models.CharityProjects;
import com.alkhair.Models.PaymentWaysResponse;
import com.alkhair.Models.ProjectDetailsResponseModel;
import com.alkhair.Models.Result;
import com.alkhair.R;
import com.alkhair.ViewModels.DonationsViewModel;
import com.alkhair.databinding.FragmentPaymentWaysBinding;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.helper.Utility;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.login.LoginFragment;
import com.alkhair.ui.partners.PartnersFragment;
import com.alkhair.ui.projects.ProjectDetailsFragment;
import com.alkhair.ui.projects.ProjectsFragment;
import com.dalil.dalilcom.presentation.homepage.morefragment.contactus.aboutdalil.WebPaymentFragment;

import java.util.ArrayList;
import java.util.Locale;

import static com.alkhair.helper.Utility.isConnectedToInternet;


public class PaymentWaysFragment extends Fragment {

    FragmentPaymentWaysBinding binding;
    PreferenceHelper helper ;
    DonationsViewModel donationsViewModel;
    private FragmentManager mFragmentTransaction;
    private FragmentManager mFragmentManager;
    private ProjectDetailsResponseModel.ResultBean result;
     public Integer radioIndex;
      public Result inside_data;
    String input_amount;
    ActionBar actionBar;
    private long mLastClickTime = 0;


  //  private ArrayList<CharityProjects> inside_result;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_ways, container, false);
        ((MainActivity) getActivity()).setTittle(getResources().getString(R.string.paymentWay));
        helper = new PreferenceHelper(getActivity());

        Log.i("lang", "onCreate: "+ Locale.getDefault().getDisplayLanguage());


        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).hideImageIcon();

        //helper = new PreferenceHelper(getActivity());

        donationsViewModel = ViewModelProviders.of(this).get(DonationsViewModel.class);

        mFragmentManager = getActivity().getSupportFragmentManager();

        result = (ProjectDetailsResponseModel.ResultBean ) getArguments().getSerializable("data");

        inside_data = (Result) getArguments().getSerializable("inside_data") ;
        input_amount =  getArguments().getSerializable("amount").toString();

        String id = helper.getuser_id();
        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                }
            mFragmentManager.popBackStack();
            }
        });

        if (id == null) {
            binding.loginConstraint.setVisibility(View.VISIBLE);
        }
        else {
            binding.loginConstraint.setVisibility(View.GONE);
        }

        binding.donation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (radioIndex == null){
                    Toast.makeText(getActivity(), "Please chose Payment way", Toast.LENGTH_LONG).show();
                }else{
                    StartActivity();
                }
            }
        });


        getWays();
        return binding.getRoot();
    }


    private void getWays() {
        Utility.showDialog(getActivity());
        donationsViewModel.getPayment(  new GetCallBack() {
            @Override
            public void getCallBack(boolean isOk, int requestCode, Object o) {
                if (isOk) {
                    Utility.hideDialog();
                    if (isConnectedToInternet(getActivity())) {
                        if (o != null) {
                            PaymentWaysResponse responseModel = (PaymentWaysResponse) o;
                            if (responseModel.getResult().size() > 0) {
                                PaymentWaysAdapter adapter = new PaymentWaysAdapter(getActivity(), PaymentWaysFragment.this, responseModel.getResult());

                                binding.ways.setAdapter(adapter);
                                binding.ways.setLayoutManager(new LinearLayoutManager(PaymentWaysFragment.this.getActivity()));
                            }
                        }
                    }
                } else {
                    Utility.hideDialog();
                    Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void StartActivity() {
        Intent intent = new Intent(getActivity(), WebPaymentFragment.class);
        intent.putExtra("data", result);
        intent.putExtra("radioIndex", radioIndex);
        intent.putExtra("amount",input_amount);
        startActivity(intent);
    }

}