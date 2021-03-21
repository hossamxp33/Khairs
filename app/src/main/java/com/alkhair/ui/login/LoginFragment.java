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


public class LoginFragment extends AppCompatActivity implements View.OnClickListener {

    private long mLastClickTime = 0;
    private FragmentLoginBinding binding;
    LoginViewModel loginViewModel;
    PreferenceHelper helper ;
    private FragmentManager mFragmentTransaction;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        //// Check Login
        helper = new PreferenceHelper(getApplicationContext());

        binding = DataBindingUtil.setContentView(this, R.layout.fragment_login);
        binding.createAccount.setOnClickListener(this);
        binding.forgetPassword.setOnClickListener(this);
        binding.logIn.setOnClickListener(this);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
//            ((MainActivity) getApplicationContext()).setTittle(getResources().getString(R.string.login));

//        ((MainActivity) getApplicationContext()).setTittle(getResources().getString(R.string.login));
//        ((MainActivity) getApplicationContext()).hideImageIcon();
//        ((MainActivity) getApplicationContext()).findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
//        ((MainActivity) getApplicationContext()).findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
//
//                }
//                mLastClickTime = SystemClock.elapsedRealtime();
//
//                getFragmentManager().popBackStack();
//                BroadcastHelper.sendInform(getApplicationContext(), "go_to_home");
//            }

   //     });
        binding.guest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                    Intent intent = new Intent(LoginFragment.this, MainActivity.class);
                    startActivity(intent);
                    finish();

            }
        });

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
                    Intent intent = new Intent(LoginFragment.this, RegistrationFragment.class);
                    startActivity(intent);
                    finish();
                    break;
            case R.id.forgetPassword:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
//                ForgetPasswordFragment forgetPasswordFragment = new ForgetPasswordFragment();
//                  showFragment(forgetPasswordFragment);
               BroadcastHelper.sendInform(LoginFragment.this, "reset_password");
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


        Utility.showDialog(LoginFragment.this);
        loginViewModel.Login(emil, password, new GetCallBack() {
            @Override
            public void getCallBack(boolean isOk, int requestCode, Object o) {
                if (isOk) {
                    Utility.hideDialog();
                    LoginResponseModel response = (LoginResponseModel) o;
                    if (response.getSuccess().equals("true")) {
                        Utility.hideKeyboard(getApplicationContext());
                        helper.addData("email",response.getResult().getEmail());
                        helper.setIsLogin("true");
                        helper.setuser_id(String.valueOf(response.getResult().getRegistrationId()));

                     //   showFragment( new HomeFragment());

             //           ((Activity) getApplicationContext()).overridePendingTransition(0, 0);
                        Intent intent = new Intent(LoginFragment.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                //      BroadcastHelper.sendInform(LoginFragment.this, "go_to_home");

                    } else {
                        Utility.hideKeyboard(getApplicationContext());
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Utility.hideKeyboard(getApplicationContext());
                    Utility.hideDialog();
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.connection_error), Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();


        BroadcastHelper.sendInform(this, "go_to_home");

        ;
    }
}