package com.alkhair.helper;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDex;

import com.facebook.stetho.Stetho;

/**
 * Created by Hossam on 11/19/2020.
 */
public class MyApplication extends Application implements AppLifeCycleHandler.AppLifeCycleCallback {
    public static final String TAG = MyApplication.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private static MyApplication mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Context context;


    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

//    public static Context getAppContext() {
//        return context;
//    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = this;

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        if (BuildConfig.DEBUG) {
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build());
        }

        AppLifeCycleHandler appLifeCycleHandler = new AppLifeCycleHandler(this);
        registerActivityLifecycleCallbacks(appLifeCycleHandler);
        registerComponentCallbacks(appLifeCycleHandler);

    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onAppBackground() {
    }

    @Override
    public void onAppForeground() {

    }

}
