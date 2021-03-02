package com.alkhair.helper;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Hossam on 11/19/2020.
 */
public class BroadcastHelper {
    public static final String BROADCAST_EXTRA_METHOD_NAME = "nm";
    public static final String ACTION_NAME = "bc";
    public static final String BROADCAST_EXTRA_DATA = "bc_E";


    public static void sendInform(Context context, String method) {
        Intent intent = new Intent();
        intent.setAction(ACTION_NAME);
        intent.putExtra(BROADCAST_EXTRA_METHOD_NAME, method);
        try {
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendInform(Context context, String method, String data) {

        Intent intent = new Intent();

        intent.setAction(ACTION_NAME);

        intent.putExtra(BROADCAST_EXTRA_METHOD_NAME, method);
        intent.putExtra(BROADCAST_EXTRA_DATA, data);

        try {

            context.sendBroadcast(intent);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public static void sendInform(Context context, String method, Intent intent) {
        intent.setAction(ACTION_NAME);
        intent.putExtra(BROADCAST_EXTRA_METHOD_NAME, method);
        try {
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

