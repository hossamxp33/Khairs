package com.alkhair;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alkhair.helper.PreferenceHelper;
import com.alkhair.helper.Utility;
import com.alkhair.ui.MainActivity;
import com.alkhair.ui.language.LanguageActivity;
import com.alkhair.ui.login.LoginFragment;

/**
 * Created by Hossam on 11/19/2020.
 */
public class Splash_View extends AppCompatActivity {
    PreferenceHelper helper;
    final Handler handler = new Handler();
    AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        helper = new PreferenceHelper(this);
//        Log.d("lang", "onCreate: " + helper.getLang());
        if (helper.getLang() != null) {
            if (helper.getuser_id() != null){
                if (helper.getLang().equals("ar")) {
                Utility.setLocale(this, "ar");
                helper.setLang("ar");
            } else {
                Utility.setLocale(this, "en");
                helper.setLang("en");
            }
            handler.postDelayed(() -> go_mainAcivity(), 1500);
        }else {
                if (helper.getLang().equals("ar")) {
                    Utility.setLocale(this, "ar");
                    helper.setLang("ar");
                } else {
                    Utility.setLocale(this, "en");
                    helper.setLang("en");
                }
                handler.postDelayed(() -> go_Login(), 1500);
            }
        }
        else {
            handler.postDelayed(() -> StartIntent(), 1500);
        }


    }

    private void StartIntent() {
        Intent intent = new Intent(this, LanguageActivity.class);
        startActivity(intent);
        finish();
    }

    private void go_mainAcivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void go_Login() {
        Intent intent = new Intent(this, LoginFragment.class);
        startActivity(intent);
        finish();
    }

}
