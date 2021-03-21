package com.alkhair.ui.news;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alkhair.Adapters.CampaignAdapter;
import com.alkhair.Adapters.NewsAdapter;
import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.Models.NewsResponseModel;
import com.alkhair.R;
import com.alkhair.ViewModels.CampaignViewModel;
import com.alkhair.ViewModels.NewsViewModel;
import com.alkhair.databinding.FragmentNewsBinding;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.helper.Utility;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.campaign.CampaignFragment;
import com.alkhair.ui.projects.ProjectsFragment;
import com.alkhair.ui.ui.home.HomeFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.alkhair.helper.Utility.isConnectedToInternet;

public class NewsFragment extends Fragment {
    FragmentNewsBinding binding;
    PreferenceHelper helper;
    NewsViewModel newsViewModel ;
    FragmentManager mFragmentManager;
    private long mLastClickTime = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        helper = new PreferenceHelper(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        binding.news.setLayoutManager(mLayoutManager);
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        ((MainActivity) getActivity()).hideImageIcon();
        ((MainActivity) getActivity()).setTittle(getResources().getString(R.string.news));
        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

                }
                mLastClickTime = SystemClock.elapsedRealtime();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
//                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                HomeFragment fragment = new HomeFragment();
//                ((MainActivity) getActivity()).showFragment(fragment);
                getFragmentManager().popBackStackImmediate();
                BroadcastHelper.sendInform(getActivity(), "go_to_home");
            }});
        getNews();
    }

    private void getNews() {

        Utility.showDialog(getActivity());
        newsViewModel.getNews( new GetCallBack() {
            @Override
            public void getCallBack(boolean isOk, int requestCode, Object o) {
                if (isOk) {
                    Utility.hideDialog();
                    if (isConnectedToInternet(getActivity())) {
                        if (o != null) {
                            NewsResponseModel responseModel = (NewsResponseModel) o;

                            if (responseModel.getResult().size() > 0) {
                                NewsAdapter adapter = new NewsAdapter(getActivity(), NewsFragment.this, responseModel.getResult());
                                binding.news.setAdapter(adapter);
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