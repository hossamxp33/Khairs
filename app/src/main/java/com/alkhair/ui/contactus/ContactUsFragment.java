package com.alkhair.ui.contactus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Callback;

import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alkhair.Models.CommentRequestModel;
import com.alkhair.Models.CommentResponseModel;
import com.alkhair.Models.ContactUsDataResponseModel;
import com.alkhair.R;
import com.alkhair.ViewModels.CampaignViewModel;
import com.alkhair.ViewModels.ContatcUSViewModel;
import com.alkhair.databinding.FragmentContactUsBinding;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.Utility;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.helper.services.webApi.ApiClient;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;


public class ContactUsFragment extends Fragment implements  View.OnClickListener{
    FragmentContactUsBinding binding;
    private long mLastClickTime=0;

    ContatcUSViewModel viewModel;
    String facebookLink ;
    String twitterLink ;
    String instagramLink ;
    String youtubeLink ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_us, container, false);
        return binding.getRoot();


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ContatcUSViewModel.class);
        binding.send.setOnClickListener(this);
        getContactData();
        ((MainActivity) getActivity()).hideImageIcon();
        ((MainActivity) getActivity()).setTittle(getResources().getString(R.string.contact_us));
        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

                }
                mLastClickTime = SystemClock.elapsedRealtime();

                getFragmentManager().popBackStack();
                BroadcastHelper.sendInform(getActivity(), "go_to_home");
            }

        });
        binding.facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookLink));
                startActivity(browserIntent);

            }
        });
        binding.twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterLink));
                startActivity(browserIntent);

            }
        });
        binding.youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
                startActivity(browserIntent);
            }
        });
        binding.insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLink));
                startActivity(browserIntent);
            }
        });
    }

    private void getContactData() {
        Utility.showDialog(getActivity());
        viewModel.data(  new GetCallBack() {
            @Override
            public void getCallBack(boolean isOk, int requestCode, Object o) {
                if(isOk){
                    Utility.hideDialog();
                    ContactUsDataResponseModel responseModel = (ContactUsDataResponseModel)o;
                    if(responseModel.getSuccess().equals("true")){
                        binding.location.setText(responseModel.getResult().getCompanyAddress());
                        binding.phone.setText(responseModel.getResult().getPrimaryContactNo());
                        binding.fax.setText(responseModel.getResult().getContactNumbersWithExtn());
                        binding.emaill.setText(responseModel.getResult().getPrimaryEmailAddress());
                        facebookLink = responseModel.getResult().getKuwaitAlKhairSocialMediaHandles().getFacebookLink();
                        twitterLink = responseModel.getResult().getKuwaitAlKhairSocialMediaHandles().getTwitterLink();
                        youtubeLink = responseModel.getResult().getKuwaitAlKhairSocialMediaHandles().getYouTubeLink();
                        instagramLink = responseModel.getResult().getKuwaitAlKhairSocialMediaHandles().getInstagramLink();
                     }
                    else {
                        Utility.hideDialog();
                        Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    Utility.hideDialog();
                    Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.send:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                send();
                break;

        }
    }


    private void send() {
        CommentRequestModel requesteModel = new CommentRequestModel();
        if (binding.fristName.getText().length() == 0) {
            Snackbar.make(binding.getRoot(), getString(R.string.empty), Snackbar.LENGTH_LONG).setAction(getString(R.string.close), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            }).setActionTextColor(getResources().getColor(android.R.color.white)).show();
            return;
        }
        else {
            requesteModel.setName(binding.fristName.getText().toString());
        }

        if (binding.message.getText().length() == 0) {
            Snackbar.make(binding.getRoot(), getString(R.string.empty), Snackbar.LENGTH_LONG).setAction(getString(R.string.close), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            }).setActionTextColor(getResources().getColor(android.R.color.white)).show();
            return;
        }
        else {
            requesteModel.setMessageDetails(binding.message.getText().toString());
        }
        String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+";
        if (!binding.emill.getText().toString().trim().matches(emailPattern)) {
            Snackbar.make(binding.getRoot(), getString(R.string.emailError), Snackbar.LENGTH_LONG).setAction(getString(R.string.close), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            }).setActionTextColor(getResources().getColor(android.R.color.white)).show();
            return;
        } else {
            requesteModel.setEmail(binding.emill.getText().toString().trim());
        }
        Utility.showDialog(getActivity());
        viewModel.postComment(requesteModel, new GetCallBack() {
            @Override
            public void getCallBack(boolean isOk, int requestCode, Object o) {
                if(isOk){
                    Utility.hideDialog();
                    CommentResponseModel responseModel = (CommentResponseModel)o;
                    if(responseModel.getSuccess().equals("true")){
                        BroadcastHelper.sendInform(getActivity(), "go_to_home");
                    }
                    else {
                        Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    Utility.hideDialog();
                    Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_LONG).show();

                }
            }
        });

    }
    public void onResume() {
        super.onResume();
        MainActivity.backFromProjectDetails = false;
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