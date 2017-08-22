package com.shiva.recipes.ui.component.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shiva.recipes.R;
import com.shiva.recipes.ui.component.recipesList.RecipesActivity;
import com.shiva.recipes.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.txt_error_message)
    TextView txtErrorMessage;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initializePresenter(getIntent().getStringExtra(Constants.RECIPES));
        loginPresenter.onViewCreated();
    }

    private void initializePresenter(@Nullable String recipes) {
        loginPresenter = new LoginPresenter(recipes);
        loginPresenter.setView(this);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                txtErrorMessage.setVisibility(View.GONE);
                loginPresenter.onLoginClick(editEmail.getText().toString(), editPassword.getText().toString());
                break;
        }
    }

    @Override
    public void showError(int errorMessageResourceId) {
        setErrorViewVisibility(true);
        txtErrorMessage.setText(getString(errorMessageResourceId));
    }

    @Override
    public void openRecipesView(String recipes) {
        Intent intent = new Intent(this, RecipesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Constants.RECIPES, recipes);
        startActivity(intent);
    }

    @Override
    public void setLoginButtonEnabled(boolean enable) {
        btnLogin.setEnabled(enable);
    }

    @Override
    public void setTextWatchers() {
        editEmail.addTextChangedListener(emailWatcher);
        editPassword.addTextChangedListener(passwordWatcher);
    }

    @Override
    public void setErrorViewVisibility(boolean visible) {
        txtErrorMessage.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private final TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            loginPresenter.onEmailTextChanged(editEmail.getText().toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            loginPresenter.onPasswordTextChanged(editEmail.getText().toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
