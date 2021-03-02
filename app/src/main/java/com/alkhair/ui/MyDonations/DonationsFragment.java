package com.alkhair.ui.MyDonations;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alkhair.Adapters.ProjectDetailsAdapter;
import com.alkhair.Adapters.ProjectTypesAdapter;
import com.alkhair.Models.DonationsHistoryRespopnse;
import com.alkhair.Models.ProjecttypesResponseModel;
import com.alkhair.R;
import com.alkhair.ViewModels.DonationsViewModel;
import com.alkhair.databinding.FragmentDonationsBinding;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.helper.Utility;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.projects.ProjectDetailsFragment;
import com.alkhair.ui.projects.ProjectsFragment;
import com.alkhair.ui.ui.home.HomeFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.alkhair.helper.Utility.isConnectedToInternet;

public class DonationsFragment extends Fragment {
    FragmentDonationsBinding binding;
    PreferenceHelper helper;
    DonationsViewModel donationsViewModel;
    private long mLastClickTime=0;

    private ArrayList<DonationsHistoryRespopnse.ResultBean> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_donations, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        helper = new PreferenceHelper(getActivity());
        donationsViewModel = ViewModelProviders.of(this).get(DonationsViewModel.class);
        data = new ArrayList<>();

        ((MainActivity) getActivity()).hideImageIcon();
        ((MainActivity) getActivity()).setTittle(getResources().getString(R.string.my_donations));
        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

                }
                mLastClickTime = SystemClock.elapsedRealtime();
                HomeFragment homeFragment = new HomeFragment();
                ((MainActivity) getActivity()).showFragment(homeFragment);
            }

        });  getDonations(helper.getData("user_id"));

    }

    private void getDonations(String user_id) {
        Utility.showDialog(getActivity());
        donationsViewModel.getDonationsHistory(user_id, new GetCallBack() {
            @Override
            public void getCallBack(boolean isOk, int requestCode, Object o) {
                if (isOk) {
                    Utility.hideDialog();
                    if (isConnectedToInternet(getActivity())) {
                        if (o != null) {
                            DonationsHistoryRespopnse responseModel = (DonationsHistoryRespopnse) o;
                            if (responseModel.getResult().size() > 0) {
                                binding.details.setVisibility(View.VISIBLE);
                                data.addAll(responseModel.getResult());
                                ProjectDetailsAdapter adapter = new ProjectDetailsAdapter(DonationsFragment.this, data, getActivity());
                                binding.details.setAdapter(adapter);
                                binding.details.setLayoutManager(new LinearLayoutManager(DonationsFragment.this.getActivity()));
                            } else {
                                binding.details.setVisibility(View.GONE);
                                if (isAdded()) {
                                    binding.error.setVisibility(View.VISIBLE);
                                    binding.error.setText(getActivity().getResources().getString(R.string.no_result));
                                }
                            }
                        } else {
                            if (isAdded()) {
                                binding.failed.setVisibility(View.VISIBLE);
                                binding.failed.setText(getActivity().getResources().getString(R.string.failed));
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