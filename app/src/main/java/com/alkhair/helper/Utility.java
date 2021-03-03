package com.alkhair.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;

import com.alkhair.R;

import java.util.Locale;
import java.util.Objects;

public class Utility {
    private static ProgressDialog dialog;

    public static void showDialog(Context context) {
        dialog = ProgressDialog.show(context, "", context.getResources().getString(R.string.Loading), true);

    }

    public static void hideDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static boolean isConnectedToInternet(Context context) {
        NetworkInfo netInfo = null;
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            netInfo = cm.getActiveNetworkInfo();
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void hideKeyboard(Context context) {
        if (context instanceof Activity) {
            hideKeyboards((Activity) context);
        }
    }

    public static void hideKeyboards(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken() != null)
                Objects.requireNonNull(inputManager).hideSoftInputFromWindow(activity.getWindow().getDecorView().getRootView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

    }
}
