package com.shiva.recipes.ui.component.login;

import com.shiva.recipes.ui.Presenter;

/**
 * Created by shivananda.darura on 13/08/17.
 */

public interface LoginView extends Presenter.View {
    void showError(int errorMessageResourceId);

    void openRecipesView(String recipes);

    void setLoginButtonEnabled(boolean enable);

    void setTextWatchers();

    void setErrorViewVisibility(boolean visible);
}
