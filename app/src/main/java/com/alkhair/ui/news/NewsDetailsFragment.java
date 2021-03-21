package com.alkhair.ui.news;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alkhair.Adapters.NewsAdapter;
import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.Models.NewsResponseModel;
import com.alkhair.R;
import com.alkhair.ViewModels.NewsViewModel;
import com.alkhair.ViewModels.ProjectsViewModel;
import com.alkhair.databinding.CampaignDetailsFragmentBinding;
import com.alkhair.databinding.FragmentNewsBinding;
import com.alkhair.databinding.NewsDetailsFragmentBinding;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.helper.Utility;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.alkhair.helper.Utility.isConnectedToInternet;

public class NewsDetailsFragment extends Fragment{
    NewsDetailsFragmentBinding binding;
    PreferenceHelper helper ;
    ProjectsViewModel projectsViewModel;
    private NewsResponseModel.ResultBean result;
    private long mLastClickTime=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.news_details_fragment, container, false);

        result = (NewsResponseModel.ResultBean) getArguments().getSerializable("data");

        binding.campaginName.setText(result.getNewsHeadline_Ar());

        Picasso.with(getContext()).load(result.getNewsImagePath()).error(R.drawable.no_image).into(binding.imageView2);

        binding.description.setText(result.getNewsLongDescription_Ar());

        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        helper = new PreferenceHelper(getActivity());
        ((MainActivity) getActivity()).setTittle(getResources().getString(R.string.newsdetails));


        ((MainActivity) getActivity()).hideImageIcon();
        ((MainActivity) getActivity()).setTittle(getResources().getString(R.string.newsdetails));
        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

                }
                mLastClickTime = SystemClock.elapsedRealtime();
                NewsFragment newsFragment = new NewsFragment();
                ((MainActivity) getActivity()).showFragment(newsFragment);
            }

        });

    }




}
