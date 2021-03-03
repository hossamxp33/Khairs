package com.alkhair.ui.kawuitElkhir;


import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.alkhair.Adapters.CampaignAdapter;
import com.alkhair.Models.AboutResponse;
import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.R;
import com.alkhair.databinding.FragmentKawuitElkhairBinding;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.Utility;
import com.alkhair.helper.services.webApi.ApiClient;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.campaign.CampaignFragment;
import com.alkhair.ui.ui.home.HomeFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alkhair.helper.Utility.isConnectedToInternet;


public class KawuitElkhairFragment extends Fragment {
    FragmentKawuitElkhairBinding binding;
    String TextFileURL = "";
    URL url;
    String TextHolder = "", TextHolder2 = "";
    BufferedReader bufferReader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kawuit_elkhair, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.txt.setMovementMethod(new ScrollingMovementMethod());
        setTextData();
        ((MainActivity) getActivity()).hideImageIcon();
        ((MainActivity) getActivity()).setTittle(getResources().getString(R.string.app_name));
        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getFragmentManager().popBackStack();
                BroadcastHelper.sendInform(getContext(), "go_to_home");
            }

        });
    }

    private void setTextData() {
        Utility.showDialog(getActivity());
        ApiClient.create().getAbout().enqueue(new Callback<AboutResponse>() {
            @Override
            public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {
                if (isConnectedToInternet(getActivity())) {
                    Utility.hideDialog();
                    if (response.body().getSuccess().equals("true")) {
                        TextFileURL = response.body().getResult();
                        new GetNotePadFileFromServer().execute();


                    } else {
                        binding.txt.setText(getActivity().getResources().getText(R.string.failed));
                    }
                }
                else {
                    Utility.hideDialog();
                    Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<AboutResponse> call, Throwable t) {
                binding.txt.setText(getActivity().getResources().getText(R.string.failed));
            }
        });


    }


    public class GetNotePadFileFromServer extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                url = new URL(TextFileURL);

                bufferReader = new BufferedReader(new InputStreamReader(url.openStream()));

                while ((TextHolder2 = bufferReader.readLine()) != null) {

                    TextHolder += TextHolder2;
                }
                bufferReader.close();

            } catch (MalformedURLException malformedURLException) {

                // TODO Auto-generated catch block
                malformedURLException.printStackTrace();
                TextHolder = malformedURLException.toString();

            } catch (IOException iOException) {

                // TODO Auto-generated catch block
                iOException.printStackTrace();

                TextHolder = iOException.toString();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void finalTextHolder) {

            binding.txt.setText(TextHolder);

            super.onPostExecute(finalTextHolder);
        }
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