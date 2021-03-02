package com.alkhair.helper.services.webApi;

import androidx.annotation.NonNull;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit = null;


    public static ApiInterface create() {

        /*-------------------*/
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .setPrettyPrinting() // Pretty print
                .create();
        /*-------------------*/

        okHttpClient = buildClient();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StoreService.Base_Url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiInterface.class);
    }


    public static Retrofit getClient() {
        okHttpClient = buildClientWithAthu();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(StoreService.Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory( RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
    private static OkHttpClient buildClient() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient.Builder builder = new OkHttpClient.Builder();


        builder.addInterceptor(new Interceptor() {
            Request request;

            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {

                request = chain.request().newBuilder().addHeader("Accept", "application/json").build();

                return chain.proceed(request);
            }
        }).addNetworkInterceptor(httpLoggingInterceptor);


        return builder.connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).build();

    }

    private static OkHttpClient buildClientWithAthu() {


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            Request request;

            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {

                request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json").build();


                return chain.proceed(request);
            }
        }).addNetworkInterceptor(httpLoggingInterceptor);


        return builder.connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).build();

    }

    public static ApiInterface createWithAthu() {

        /*-------------------*/
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .setPrettyPrinting() // Pretty print
                .create();
        /*-------------------*/

        okHttpClient = buildClientWithAthu();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StoreService.Base_Url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiInterface.class);
    }



}
