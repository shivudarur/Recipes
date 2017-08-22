package com.shiva.recipes.ui.component.login;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.shiva.recipes.R;
import com.shiva.recipes.ui.Presenter;

/**
 * Created by shivananda.darura on 13/08/17.
 */

public class LoginPresenter extends Presenter<LoginView> {

    private String recipes;

    public LoginPresenter(@Nullable String recipes) {
        this.recipes = recipes;
    }

    public void onLoginClick(String email, String password) {

        boolean shouldEnableLoginButton = false;

        if (isEmptyString(recipes)) {
            view.showError(R.string.json_error);
        } else if (isEmptyString(email)) {
            view.showError(R.string.invalid_email);
        } else if (isEmptyString(password)) {
            view.showError(R.string.invalid_password);
        } else if (!isValidEmail(email)) {
            view.showError(R.string.invalid_email);
        } else if (!isValidPassword(password)) {
            view.showError(R.string.invalid_password);
        } else {
            view.openRecipesView(recipes);
            shouldEnableLoginButton = true;
        }

        view.setLoginButtonEnabled(shouldEnableLoginButton);
    }

    public void onViewCreated() {
        view.setTextWatchers();
        view.setLoginButtonEnabled(false);
    }

    @VisibleForTesting
    public void setRecipes(String recipes) {
        this.recipes = recipes;
    }

    @VisibleForTesting
    public boolean isValidPassword(String password) {
        return password != null && !password.trim().isEmpty();
    }

    public void onEmailTextChanged(String email) {
        if (!isEmptyString(email)) {
            view.setErrorViewVisibility(false);
            view.setLoginButtonEnabled(true);
        }
    }

    public void onPasswordTextChanged(String password) {
        if (!isEmptyString(password)) {
            view.setErrorViewVisibility(false);
            view.setLoginButtonEnabled(true);
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isEmptyString(String string) {
        return string == null || string.isEmpty();
    }
}
