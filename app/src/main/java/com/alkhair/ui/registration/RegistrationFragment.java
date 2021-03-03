package com.alkhair.ui.registration;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alkhair.Models.RegistrationResponseModel;
import com.alkhair.Models.RegistrationequestModel;
import com.alkhair.R;
import com.alkhair.ViewModels.LoginViewModel;
import com.alkhair.databinding.FragmentRegistrationBinding;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.helper.Utility;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;


public class RegistrationFragment extends Fragment {
    FragmentRegistrationBinding binding;
    private long mLastClickTime = 0;
    LoginViewModel viewModel;
    PreferenceHelper helper ;
    FragmentManager mFragmentManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        helper = new PreferenceHelper(getActivity());
        ((MainActivity) getActivity()).setTittle(getResources().getString(R.string.regeter));

        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

                }
                mLastClickTime = SystemClock.elapsedRealtime();

                getFragmentManager().popBackStack();
            //    BroadcastHelper.sendInform(getActivity(), "go_to_home");
            }

        });

        binding.logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Registration();
            }
        });
    }


    public boolean isEmpty(String value) {
        return value.matches("/s*");
    }

    private void Registration() {
        final RegistrationequestModel request = new RegistrationequestModel();
        // email
        String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+";
        if (!binding.email.getText().toString().trim().matches(emailPattern)) {
            Snackbar.make(binding.getRoot(), getString(R.string.emailError), Snackbar.LENGTH_LONG).setAction(getString(R.string.close), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            }).setActionTextColor(getResources().getColor(android.R.color.white)).show();
            return;
        } else {

            request.setEmail(binding.email.getText().toString().trim());
        }
        // f name
//        if (binding.fristName.getText().length() == 0) {
//            Snackbar.make(binding.getRoot(), getString(R.string.empty), Snackbar.LENGTH_LONG).setAction(getString(R.string.close), new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                }
//            }).setActionTextColor(getResources().getColor(android.R.color.white)).show();
//            return;
//        } else {
//            request.setFirstName(binding.fristName.getText().toString().trim());
//        }
//        // l name
//        if (binding.lastName.getText().length() == 0) {
//            Snackbar.make(binding.getRoot(), getString(R.string.empty), Snackbar.LENGTH_LONG).setAction(getString(R.string.close), new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                }
//            }).setActionTextColor(getResources().getColor(android.R.color.white)).show();
//            return;
//        } else {
//            request.setLastName(binding.lastName.getText().toString().trim());
//        }
        // phone
        if (binding.phone.getText().length() != 8 || isEmpty(binding.phone.getText().toString().trim())) {
            Snackbar.make(binding.getRoot(), getString(R.string.phoneError), Snackbar.LENGTH_LONG).setAction(getString(R.string.close), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            }).setActionTextColor(getResources().getColor(android.R.color.white)).show();
            return;

        } else {
            request.setMobile(binding.phone.getText().toString().trim());
        }
      //password
        if (binding.password.getText().length() == 0) {
            Snackbar.make(binding.getRoot(), getString(R.string.empty), Snackbar.LENGTH_LONG).setAction(getString(R.string.close), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            }).setActionTextColor(getResources().getColor(android.R.color.white)).show();
            return;
        } else {
            if (!binding.confirm.getText().toString().trim().equals(binding.password.getText().toString().trim())) {
                Snackbar.make(binding.getRoot(), getString(R.string.passwordNotMatched), Snackbar.LENGTH_LONG).setAction(getString(R.string.close), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                }).setActionTextColor(getResources().getColor(android.R.color.white)).show();
                return;
            } else {

                request.setPassword(binding.password.getText().toString());
            }
        }


        Utility.showDialog(getActivity());
        viewModel.Registration(request, new GetCallBack() {
            @Override
            public void getCallBack(boolean isOk, int requestCode, Object o) {
                if (isOk) {
                    Utility.hideDialog();
                    RegistrationResponseModel response = (RegistrationResponseModel) o;
                    if (response.getSuccess().equals("true")) {
                        helper.setIsLogin("true");
                        helper.setuser_id(String.valueOf(response.getResult().getRegistrationId()));
                        Utility.hideKeyboard(getActivity());
                        BroadcastHelper.sendInform(getActivity(), "go_to_home");
                    } else {
                        Utility.hideKeyboard(getActivity());
                        Toast.makeText(getActivity(),response.getResult().getMessageToUser(), Toast.LENGTH_LONG).show();
                    }

                } else {
                    Utility.hideDialog();
                    Utility.hideKeyboard(getActivity());
                    Toast.makeText(getActivity(),getActivity().getResources(). getString(R.string.connection_error), Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction() ;
        mFragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.pop_out, R.anim.pop_in);
        mFragmentTransaction.replace(R.id.fragment, fragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
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
                  //  BroadcastHelper.sendInform(getActivity(), "go_to_home");

                }

                return false;

            }
        });
    }
}