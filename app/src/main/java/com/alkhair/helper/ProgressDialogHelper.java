package com.alkhair.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.alkhair.R;

/**
 * Created by Hossam on 11/19/2020.
 */
public class ProgressDialogHelper {
    private static ProgressDialog mProgressDialog;

    public static void showSimpleProgressDialog(Context context, String MSG
            , boolean isCancelable) {
        try {

            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(context);
                mProgressDialog.show();
//                mProgressDialog = ProgressDialog.show(context, title, msg);

                mProgressDialog.setContentView(R.layout.progress_dialog_layout);
                mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                mProgressDialog.setCancelable(isCancelable);
                ProgressBar bar = mProgressDialog.findViewById(R.id.progressBar1);
                Drawable progressDrawable = bar.getIndeterminateDrawable().mutate();
                progressDrawable.setColorFilter(ContextCompat.getColor(context, R.color.primaryText), android.graphics.PorterDuff.Mode.SRC_IN);
                bar.setProgressDrawable(progressDrawable);
                TextView textView = mProgressDialog.findViewById(R.id.texts);
                textView.setText(MSG);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

