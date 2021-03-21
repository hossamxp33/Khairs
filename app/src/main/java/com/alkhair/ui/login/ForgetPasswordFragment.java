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
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class ForgetPasswordFragment extends AppCompatActivity {
    LoginViewModel viewModel;
    FragmentForgetPasswordBinding binding;
    private  long mLastClickTime = 0;
    PreferenceHelper helper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_forget_password);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        helper = new PreferenceHelper(this);
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


                Utility.showDialog(this);
                 viewModel.reset(emil , new GetCallBack() {
                    @Override
                    public void getCallBack(boolean isOk, int requestCode, Object o) {

                        if (isOk) {
                            Utility.hideKeyboard(ForgetPasswordFragment.this);
                            Utility.hideDialog();
                            ResetPasswordResponseModel response = (ResetPasswordResponseModel) o;
                            if (response.getSuccess().equals("true")){
                                // save data object
                                BroadcastHelper.sendInform(ForgetPasswordFragment.this, "go_to_home");
                            }
                            else {

                                Toast.makeText(ForgetPasswordFragment.this, getApplicationContext().getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                            }

                        }
                        else {
                            Utility.hideKeyboard(ForgetPasswordFragment.this);
                            Utility.hideDialog();
                            Toast.makeText(ForgetPasswordFragment.this, getString(R.string.connection_error), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }


    }

