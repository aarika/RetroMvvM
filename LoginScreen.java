package com.nisyst.advanceindra.ui.auth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nisyst.advanceindra.R;
import com.nisyst.advanceindra.model.res.TokenResponse;
import com.nisyst.advanceindra.model.res.TwoFactorAuthResponse;
import com.nisyst.advanceindra.network.RetrofitManager;
import com.nisyst.advanceindra.ui.main.HomeScreen;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


import static com.nisyst.advanceindra.util.Utility.alertDialog;
import static com.nisyst.advanceindra.util.Utility.hideApiDialog;
import static com.nisyst.advanceindra.util.Utility.isNetworkAvailable;
import static com.nisyst.advanceindra.util.Utility.newIntentClearTop;
import static com.nisyst.advanceindra.util.Utility.showApiDialog;
import static com.nisyst.advanceindra.util.Utility.showMessageInternet;
import static com.nisyst.advanceindra.util.Utility.showToast;

public class LoginScreen extends AppCompatActivity implements LoginViewModel.LoginInterface {

    @BindView(R.id.txtUsername)
    EditText txtUsername;
    @BindView(R.id.txtPassword)
    EditText txtPassword;

    RetrofitManager retrofitManager;

    LoginViewModel mViewModel;

    String strUsername, strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        try {
            mViewModel = new LoginViewModel(this);

            retrofitManager = new RetrofitManager();

            txtUsername.setText("NJ");
            txtPassword.setText("Test@123");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Boolean Validate() {
        if (TextUtils.isEmpty(txtUsername.getText().toString().trim())) {
            txtUsername.setError(getString(R.string.error_txt_enter_username));
        } else if (TextUtils.isEmpty(txtPassword.getText().toString().trim())) {
            txtPassword.setError(getString(R.string.error_txt_enter_password));
        } else {
            strUsername = txtUsername.getText().toString().trim();
            strPassword = txtPassword.getText().toString().trim();
            return true;
        }

        return false;
    }

    @OnClick({R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (Validate())
                    is2FAEnable();
                break;
        }
    }

    private void is2FAEnable() {
        if (isNetworkAvailable(this)) {
            mViewModel.is2FAEnabled(strUsername);
        } else {
            showMessageInternet(this);
        }
    }

    private void authenticateUser() {
        if (isNetworkAvailable(this)) {
            mViewModel.authenticateUser(strUsername, strPassword);
        } else {
            showMessageInternet(this);
        }
    }

    private void authenticateUserUsing2FA(String code) {
        if (isNetworkAvailable(this)) {
            mViewModel.authenticateUserUsing2FA(strUsername, strPassword, code);
        } else {
            showMessageInternet(this);
        }
    }

    private void jumpToHomeScreen() {
        newIntentClearTop(this, HomeScreen.class, true);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void showDialog2FACode() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(LoginScreen.this);
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_2fa_code, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);

        TextView text = new TextView(this);
        text.setText("Google Authenticator Code");
        text.setGravity(Gravity.CENTER);
        text.setPadding(0, 40, 0, 40);
        text.setTextSize(25);
        text.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        text.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        builder.setCustomTitle(text);
        builder.setView(mView);

        final EditText txt2FACode = mView.findViewById(R.id.txt2FACode);

        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            String code = txt2FACode.getText().toString();
            authenticateUserUsing2FA(code);
        });

        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {

        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

        txt2FACode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(editable.length() == 6);
                }
            }
        });
    }

    @Override
    public void onApiStart() {
        showApiDialog(this);
    }

    @Override
    public void onApiIs2FAEnabledSuccess(TwoFactorAuthResponse response) {
        if (response.getEnable2FA()) {
            showDialog2FACode();
        } else {
            authenticateUser();
        }
    }

    @Override
    public void onApiAuthenticateUserSuccess(TokenResponse response) {
        jumpToHomeScreen();
    }

    @Override
    public void onApiError(String error) {
        showToast(this, error);
    }

    @Override
    public void onApiFinish() {
        hideApiDialog();
    }
}