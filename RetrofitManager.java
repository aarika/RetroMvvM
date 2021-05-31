package com.nisyst.advanceindra.network;

import android.content.Context;

import com.google.gson.Gson;
import com.nisyst.advanceindra.BuildConfig;
import com.nisyst.advanceindra.app.GlobalApps;
import com.nisyst.advanceindra.model.res.TokenResponse;
import com.nisyst.advanceindra.network.api_services.ApiTokenService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    //region Constructor
    public RetrofitManager() {
    }
    //endregion

    private static boolean compileForDemo = false;

    //region Retrofit Networking Related Implementation
    private static String API_URL = compileForDemo ? "" : ""; 

    private static final int TIME_OUT = 60;

    AuthTokenInterceptor authTokenInterceptor = new AuthTokenInterceptor();
    RefreshTokenInterceptor refreshTokenInterceptor = new RefreshTokenInterceptor();
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build();

    private Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(new Gson()));

    private Retrofit retrofit = retrofitBuilder.build();

    private OkHttpClient okHttpClientRefresh = new OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build();

    private Retrofit.Builder retrofitBuilderRefresh = new Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClientRefresh)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(new Gson()));

    private Retrofit retrofitRefresh = retrofitBuilderRefresh.build();

    public void changeBaseURL(String newURL) {
        API_URL = newURL;

        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()));

        retrofit = retrofitBuilder.build();

        retrofitBuilderRefresh = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClientRefresh)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()));

        retrofitRefresh = retrofitBuilderRefresh.build();
    }


    public <S> S serviceAccessToken(Class<S> serviceClass) {

        if (!okHttpClient.interceptors().contains(loggingInterceptor)) {
            if (BuildConfig.DEBUG) {
                loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            } else {
                loggingInterceptor.level(HttpLoggingInterceptor.Level.NONE);
            }
            okHttpClient = okHttpClient.newBuilder().addInterceptor(loggingInterceptor).build();
            retrofitBuilder = retrofit.newBuilder();
            retrofit = retrofitBuilder.client(okHttpClient).build();
        }
        return retrofit.create(serviceClass);
    }

    public <S> S serviceRefreshToken(Class<S> serviceClass) {

        if (!okHttpClientRefresh.interceptors().contains(loggingInterceptor)) {
            if (BuildConfig.DEBUG) {
                loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            } else {
                loggingInterceptor.level(HttpLoggingInterceptor.Level.NONE);
            }
            okHttpClientRefresh = okHttpClientRefresh.newBuilder().addInterceptor(loggingInterceptor).build();
            retrofitBuilderRefresh = retrofitRefresh.newBuilder();
            retrofitRefresh = retrofitBuilderRefresh.client(okHttpClientRefresh).build();
        }
        return retrofitRefresh.create(serviceClass);
    }

    public <S> S serviceAuthCall(Class<S> serviceClass) {

        if (!okHttpClient.interceptors().contains(authTokenInterceptor)) {
            okHttpClient = okHttpClient.newBuilder().addInterceptor(authTokenInterceptor).build();
            retrofitBuilder = retrofit.newBuilder();
            retrofit = retrofitBuilder.client(okHttpClient).build();
        }
        if (!okHttpClient.interceptors().contains(refreshTokenInterceptor)) {
            okHttpClient = okHttpClient.newBuilder().addInterceptor(refreshTokenInterceptor).build();
            retrofitBuilder = retrofit.newBuilder();
            retrofit = retrofitBuilder.client(okHttpClient).build();
        }
        if (!okHttpClient.interceptors().contains(loggingInterceptor)) {
            if (BuildConfig.DEBUG) {
                loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            } else {
                loggingInterceptor.level(HttpLoggingInterceptor.Level.NONE);
            }
            okHttpClient = okHttpClient.newBuilder().addInterceptor(loggingInterceptor).build();
            retrofitBuilder = retrofit.newBuilder();
            retrofit = retrofitBuilder.client(okHttpClient).build();
        }
        return retrofit.create(serviceClass);
    }

    private class AuthTokenInterceptor implements Interceptor {

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer" + " " +
                            GlobalApps.getInstance().getTokenResponseObj().getAccessToken()) //change these to match your own Auth model
                    .method(original.method(), original.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }

    private class RefreshTokenInterceptor implements Interceptor {

        Response mainResponse = null;
        Request mainRequest = null;

        @Override
        public Response intercept(Chain chain) throws IOException {

            try {
                mainResponse = chain.proceed(chain.request());
                mainRequest = chain.request();

                // if response code is 401 or 403, 'mainRequest' has encountered authentication error
                if (mainResponse.code() == 401 || mainResponse.code() == 403) {
                    // request to login API to get fresh token

                    // synchronously calling login API
                    serviceRefreshToken(ApiTokenService.class)
                        .getRefreshToken(GlobalApps.getInstance().getTokenResponseObj().getRefreshToken(), "refresh_token")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TokenResponse>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull TokenResponse tokenResponse) {
                                // save the new token
                                GlobalApps.getInstance().setTokenResponseObj(tokenResponse);
                                // Close body becuase it gives exception
                                mainResponse.body().close();
                                // retry the 'mainRequest' which encountered an authentication error
                                // add new token into 'mainRequest' header and request again
                                Request.Builder builder = mainRequest.newBuilder()
                                        .header("Accept", "application/json")
                                        .header("Authorization", "Bearer" + " " +
                                                GlobalApps.getInstance().getTokenResponseObj().getAccessToken())
                                        .method(mainRequest.method(), mainRequest.body());
                                try {
                                    mainResponse = chain.proceed(builder.build());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                }

                return mainResponse;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mainResponse;
        }
    }
}
