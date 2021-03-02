package com.alkhair.ui.payment;

 import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.alkhair.Models.CampaginResponseModel;
 import com.alkhair.R;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.PreferenceHelper;

public class PaymentActivity extends AppCompatActivity {
    Toolbar toolbar;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    PreferenceHelper helper;
    private long mLastClickTime = 0;
    private Receiver receiver;
    private boolean isReciverRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        toolbar = findViewById(R.id.toolbar);
        helper = new PreferenceHelper(this);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        toolbar.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                BroadcastHelper.sendInform(PaymentActivity.this, "hide_main_icon");
                finish();
            }
        });

        PaymentWaysFragment coachDetailsFragment = new PaymentWaysFragment();
        showFragment(coachDetailsFragment);
        setTittle(getResources().getString(R.string.paymentWay));
    }


    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            String methodName = arg1.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME);
            if (methodName != null && methodName.length() > 0) {
                switch (methodName) {



                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        if (isReciverRegistered) {

            if (receiver != null)
                unregisterReceiver(receiver);
        }
        super.onDestroy();
    }



    private void showFragment(Fragment fragment) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.contentFragment, fragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commitAllowingStateLoss();

    }

    public void setTittle(String tittle) {
        TextView tooltext = toolbar.findViewById(R.id.toolbar_title);
        tooltext.setVisibility(View.VISIBLE);
        tooltext.setText(tittle);
    }


}