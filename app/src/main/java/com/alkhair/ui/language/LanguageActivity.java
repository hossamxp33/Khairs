package com.alkhair.ui.language;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.view.View;

import com.alkhair.R;
import com.alkhair.databinding.FragmentLanguageBinding;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.helper.Utility;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.login.LoginFragment;

import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class LanguageActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentLanguageBinding binding;
    private long mLastClickTime = 0;
    PreferenceHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_language);
        binding.arabic.setOnClickListener(this);
        binding.english.setOnClickListener(this);
        helper = new PreferenceHelper(this);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.arabic:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Utility.setLocale(this, "ar");
                helper.setLang("ar");
                StartIntent();

                break;
            case R.id.english:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Utility.setLocale(this, "en");
                helper.setLang("en");
                StartIntent();
                break;
        }

    }
    private void StartIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}