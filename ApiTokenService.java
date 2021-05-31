package com.nisyst.advanceindra.network.api_services;

import com.nisyst.advanceindra.model.res.TokenResponse;
import com.nisyst.advanceindra.model.res.TwoFactorAuthResponse;


import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiTokenService {

    public static final String API_VERSION = "v1";


    @FormUrlEncoded
    @POST("token")
    Observable<TokenResponse> getAccessToken(@Field("username") String username,
                                         @Field("password") String password,
                                         @Field("grant_type") String grant_type);

    @FormUrlEncoded
    @POST("token")
    Observable<TokenResponse> getAccessToken2FA(@Field("username") String username,
                                                @Field("password") String password,
                                                @Field("grant_type") String grant_type,
                                                @Field("2FACode") String code);

    @FormUrlEncoded
    @POST("token")
    Observable<TokenResponse> getRefreshToken(@Field("refresh_token") String refresh_token,
                                              @Field("grant_type") String grant_type);

    @GET(API_VERSION+"/Login/is2FAEnabled")
    Observable<TwoFactorAuthResponse> is2FAEnabled(@Query("username") String username);

    /*
    // Retrofit2
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.2.0'

    // gson
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'

    // OkHttp
    implementation 'com.squareup.okhttp3:okhttp:4.9.0'

    // OkHttp Logging Interceptor
    implementation 'com.squareup.okhttp3:logging-interceptor:4.9.0'

    // Rx
    implementation 'io.reactivex.rxjava2:rxjava:2.2.19'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'

    // Loader -> SpinKit & Lottie
    implementation 'com.github.ybq:Android-SpinKit:1.4.0'
    implementation 'com.airbnb.android:lottie:3.4.0'

    // ButterKnife
    implementation 'com.jakewharton:butterknife:10.2.3'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:10.2.1'    
    */

}
