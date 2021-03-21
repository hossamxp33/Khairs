package com.alkhair.ui.chairtydetails;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.alkhair.Models.Result;
import com.alkhair.R;
import com.alkhair.databinding.AboutChairtyBinding;
import com.alkhair.helper.PreferenceHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Hossam on 3/4/2021.
 */
public class AboutDonationFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;

    private long mLastClickTime = 0;
    private AboutChairtyBinding binding;
    PreferenceHelper helper;
    private Result companydetails;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        helper = new PreferenceHelper(getActivity());

        binding = DataBindingUtil.inflate(inflater, R.layout.about_chairty, container, false);

        Bundle args = getArguments();

        companydetails = (Result) args.getParcelable("companydetails");

        if (helper.getLang().equals("ar")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if ((companydetails != null && companydetails.getCharityAbout_Ar() != null)) {
                    binding.details.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityAbout_Ar() : null, Html.FROM_HTML_MODE_COMPACT));
                }
                if ((companydetails != null && companydetails.getCharityVision_Ar() != null)) {
                    binding.visionText.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityVision_Ar() : null, Html.FROM_HTML_MODE_COMPACT));
                }
                if ((companydetails != null && companydetails.getCharityAddress_Ar() != null)) {
                    binding.locationText.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityAddress_Ar() : null, Html.FROM_HTML_MODE_COMPACT));
                }
                if ((companydetails != null && companydetails.getCharityMission_Ar() != null)) {
                    binding.goalDetails.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityMission_Ar() : null, Html.FROM_HTML_MODE_COMPACT));
                }
            } else {
                if ((companydetails != null && companydetails.getCharityAbout_Ar() != null)) {
                    binding.details.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityAbout_Ar() : null));
                }
                if ((companydetails != null && companydetails.getCharityVision_Ar() != null)) {
                    binding.visionText.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityVision_Ar() : null));
                }
                if ((companydetails != null && companydetails.getCharityAddress_Ar() != null)) {
                    binding.locationText.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityAddress_Ar() : null));
                }

            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if ((companydetails != null && companydetails.getCharityAbout_En() != null)) {
                    binding.details.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityAbout_En() : null, Html.FROM_HTML_MODE_COMPACT));
                }
                if ((companydetails != null && companydetails.getCharityMission_En() != null)) {
                    binding.goalDetails.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityMission_En() : null, Html.FROM_HTML_MODE_COMPACT));
                }
                if ((companydetails != null && companydetails.getCharityAddress_En() != null)) {
                    binding.locationText.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityAddress_En() : null, Html.FROM_HTML_MODE_COMPACT));
                }
            } else {
                if ((companydetails != null && companydetails.getCharityAbout_En() != null)) {
                    binding.details.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityAbout_En() : null));
                }
                if ((companydetails != null && companydetails.getCharityMission_En() != null)) {
                    binding.goalDetails.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityMission_En() : null));
                }
                if ((companydetails != null && companydetails.getCharityAddress_En() != null)) {
                    binding.locationText.setText(Html.fromHtml(companydetails != null ? companydetails.getCharityAddress_En() : null));
                }
            }
        }
        binding.emailText.setText(companydetails.getCharityContactEmail());
        binding.phoneText.setText(companydetails.getCharityPhone1() + "  -  " + companydetails.getCharityPhone2());
        binding.progressBar.setVisibility(View.GONE);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return binding.getRoot();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng location = null;
        // Add a marker in Sydney and move the camera
        if (companydetails.getCharityLatitude() > 0 && companydetails.getCharityLongitude() > 0) {
            location = new LatLng(companydetails.getCharityLatitude(), companydetails.getCharityLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(location));
        } else {
            location = new LatLng(29.3117, 47.4818);
        }
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.getUiSettings().setScrollGesturesEnabled(false);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 8f));
    }
}
