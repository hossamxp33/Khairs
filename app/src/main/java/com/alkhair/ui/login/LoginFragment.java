package com.alkhair.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alkhair.Models.LoginResponseModel;
import com.alkhair.R;
import com.alkhair.ViewModels.LoginViewModel;
import com.alkhair.databinding.FragmentLoginBinding;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.helper.Utility;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.registration.RegistrationFragment;
import com.alkhair.ui.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;


public class LoginFragment extends Fragment implements View.OnClickListener {

    private long mLastClickTime = 0;
    private FragmentLoginBinding binding;
    LoginViewModel loginViewModel;
    PreferenceHelper helper ;
    private FragmentManager mFragmentTransaction;
    private FragmentManager mFragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //// Check Login
        helper = new PreferenceHelper(getActivity());

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        binding.guest.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

                }
                mLastClickTime = SystemClock.elapsedRealtime();
                showFragment( new HomeFragment());

            }
        });
        mFragmentManager = getActivity().getSupportFragmentManager();
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        binding.createAccount.setOnClickListener(this);
        binding.forgetPassword.setOnClickListener(this);
        binding.logIn.setOnClickListener(this);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.logIn:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Login();

                break;
                case R.id.createAccount:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
               // BroadcastHelper.sendInform(getActivity(), "registration");
                    RegistrationFragment register = new RegistrationFragment();
                    showFragment(register);

                    break;
            case R.id.forgetPassword:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                ForgetPasswordFragment forgetPasswordFragment = new ForgetPasswordFragment();
                  showFragment(forgetPasswordFragment);
             //   BroadcastHelper.sendInform(getActivity(), "reset_password");
                break;

        }

    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.pop_out, R.anim.pop_in);
        mFragmentTransaction.replace(R.id.fragment, fragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }


    private void Login() {
        String emil;
        String password;
        if (binding.email.getText().length() == 0) {
            Snackbar.make(binding.getRoot(), getString(R.string.empty), Snackbar.LENGTH_LONG).setAction(getString(R.string.close), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            }).setActionTextColor(getResources().getColor(android.R.color.white)).show();
            return;
        } else {
            emil = binding.email.getText().toString().trim();
        }

        if (binding.password.getText().length() == 0) {
            Snackbar.make(binding.getRoot(), getString(R.string.empty), Snackbar.LENGTH_LONG).setAction(getString(R.string.close), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            }).setActionTextColor(getResources().getColor(android.R.color.white)).show();
            return;
        } else {
            password = binding.password.getText().toString().trim();
        }


        Utility.showDialog(getActivity());
        loginViewModel.Login(emil, password, new GetCallBack() {
            @Override
            public void getCallBack(boolean isOk, int requestCode, Object o) {
                if (isOk) {
                    Utility.hideDialog();
                    LoginResponseModel response = (LoginResponseModel) o;
                    if (response.getSuccess().equals("true")) {
                        Utility.hideKeyboard(getActivity());
                        helper.addData("email",response.getResult().getEmail());
                        helper.setIsLogin("true");
                        helper.setuser_id(String.valueOf(response.getResult().getRegistrationId()));

                        showFragment( new HomeFragment());

                        ((Activity) getActivity()).overridePendingTransition(0, 0);
                    //    BroadcastHelper.sendInform(getActivity(), "go_to_home");

                    } else {
                        Utility.hideKeyboard(getActivity());
                        Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Utility.hideKeyboard(getActivity());
                    Utility.hideDialog();
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.connection_error), Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() == null) {
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    ((AppCompatActivity) getActivity()).getSupportActionBar().show();
                  BroadcastHelper.sendInform(getActivity(), "go_to_home");

                }

                return false;

            }
        });
    }
}