package com.alkhair.ui.partners;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alkhair.Adapters.PartnersAdapter;
import com.alkhair.Models.PartnersResponseModel;
import com.alkhair.R;
import com.alkhair.ViewModels.PartnersViewModel;
import com.alkhair.databinding.FragmentPartnersBinding;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.helper.Utility;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.ui.MainActivity;
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel;

import static com.alkhair.helper.Utility.isConnectedToInternet;


public class PartnersFragment extends Fragment {
    FragmentPartnersBinding binding;
    PreferenceHelper helper;
    private PartnersViewModel partnersViewModel;
    MainViewModel viewModel;
    private long mLastClickTime = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_partners, container, false);

        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        ((MainActivity) getActivity()).hideImageIcon();
        ((MainActivity) getActivity()).setTittle(getResources().getString(R.string.partners));

        helper = new PreferenceHelper(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.campagin.setLayoutManager(mLayoutManager);
        partnersViewModel = ViewModelProviders.of(this).get(PartnersViewModel.class);
        getpartners();
        viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(MainViewModel.class);
        viewModel.get_PartnerData();
    }

    private void getpartners() {
        Utility.showDialog(getActivity());
        partnersViewModel.getPartners("0", new GetCallBack() {
            @Override
            public void getCallBack(boolean isOk, int requestCode, Object o) {
                if (isOk) {
                    Utility.hideDialog();
                    if (isConnectedToInternet(getActivity())) {
                        if (o != null) {
                            PartnersResponseModel responseModel = (PartnersResponseModel) o;
                            if (responseModel.getResult().size() > 0) {
                                PartnersAdapter adapter = new PartnersAdapter(getActivity(), PartnersFragment.this, responseModel.getResult());
                                binding.campagin.setAdapter(adapter);
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

    private MainViewModelFactory getViewModelFactory() {
        return new MainViewModelFactory(this.getActivity().getApplication());
    }

    @Override
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