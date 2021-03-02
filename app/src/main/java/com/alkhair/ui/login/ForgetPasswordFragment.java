package com.alkhair.ui.login;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alkhair.Models.LoginResponseModel;
import com.alkhair.Models.ResetPasswordResponseModel;
import com.alkhair.R;
import com.alkhair.ViewModels.LoginViewModel;
import com.alkhair.databinding.FragmentForgetPasswordBinding;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.helper.Utility;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class ForgetPasswordFragment extends Fragment {
    LoginViewModel viewModel;
    FragmentForgetPasswordBinding binding;
    private  long mLastClickTime = 0;
    PreferenceHelper helper ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forget_password, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        helper = new PreferenceHelper(getActivity());

        ((MainActivity) getActivity()).hideImageIcon();
        ((MainActivity) getActivity()).setTittle(getResources().getString(R.string.forget_password));
     
        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                reset();
            }
        });
    }

    private void reset() {
        String emil;

        if (binding.passwordConfirm.getText().length() == 0 ) {
            Snackbar.make(binding.getRoot(), getString(R.string.empty), Snackbar.LENGTH_LONG).setAction(getString(R.string.close), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            }).setActionTextColor(getResources().getColor(android.R.color.white)).show();
            return;
        }else
        {
            emil = binding.passwordConfirm.getText().toString().trim();

        }


                Utility.showDialog(getActivity());
                 viewModel.reset(emil , new GetCallBack() {
                    @Override
                    public void getCallBack(boolean isOk, int requestCode, Object o) {

                        if (isOk) {
                            Utility.hideKeyboard(getActivity());
                            Utility.hideDialog();
                            ResetPasswordResponseModel response = (ResetPasswordResponseModel) o;
                            if (response.getSuccess().equals("true")){
                                // save data object
                                BroadcastHelper.sendInform(getActivity(), "go_to_home");
                            }
                            else {

                                Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                            }

                        }
                        else {
                            Utility.hideKeyboard(getActivity());
                            Utility.hideDialog();
                            Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }


    }

