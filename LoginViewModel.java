package com.nisyst.advanceindra.ui.auth;

import com.nisyst.advanceindra.app.GlobalApps;
import com.nisyst.advanceindra.model.res.TokenResponse;
import com.nisyst.advanceindra.model.res.TwoFactorAuthResponse;
import com.nisyst.advanceindra.network.RetrofitManager;
import com.nisyst.advanceindra.network.api_services.ApiTokenService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel {

    LoginInterface myInterface;
    RetrofitManager retrofitManager;

    public LoginViewModel(LoginInterface mInterface) {
        myInterface = mInterface;
        retrofitManager = new RetrofitManager();
    }

    public void is2FAEnabled(String user) {
        try {
            retrofitManager.serviceAccessToken(ApiTokenService.class)
                .is2FAEnabled(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TwoFactorAuthResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        myInterface.onApiStart();
                    }

                    @Override
                    public void onNext(@NonNull TwoFactorAuthResponse twoFactorAuthResponse) {
                        myInterface.onApiIs2FAEnabledSuccess(twoFactorAuthResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        myInterface.onApiFinish();
                        myInterface.onApiError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        myInterface.onApiFinish();
                    }
                });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void authenticateUser(String user, String pass) {
        try {
            retrofitManager.serviceAccessToken(ApiTokenService.class)
                    .getAccessToken(user, pass, "password")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TokenResponse>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            myInterface.onApiStart();
                        }

                        @Override
                        public void onNext(@NonNull TokenResponse tokenResponse) {
                            myInterface.onApiAuthenticateUserSuccess(tokenResponse);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            myInterface.onApiFinish();
                            myInterface.onApiError(e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            myInterface.onApiFinish();
                        }
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void authenticateUserUsing2FA(String user, String pass, String code) {
        try {
            retrofitManager.serviceAccessToken(ApiTokenService.class)
                    .getAccessToken2FA(user, pass, "password", code)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TokenResponse>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            myInterface.onApiStart();
                        }

                        @Override
                        public void onNext(@NonNull TokenResponse tokenResponse) {
                            myInterface.onApiAuthenticateUserSuccess(tokenResponse);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            myInterface.onApiFinish();
                            myInterface.onApiError(e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            myInterface.onApiFinish();
                        }
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public interface LoginInterface {
        void onApiStart();

        void onApiIs2FAEnabledSuccess(TwoFactorAuthResponse response);

        void onApiAuthenticateUserSuccess(TokenResponse tokenResponse);

        void onApiError(String error);

        void onApiFinish();
    }
}
