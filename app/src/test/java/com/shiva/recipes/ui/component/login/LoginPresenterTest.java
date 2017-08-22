package com.shiva.recipes.ui.component.login;

import com.shiva.recipes.ui.component.TestHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by shivananda.darura on 27/04/17.
 */
public class LoginPresenterTest {

    @Mock
    LoginView mockView;

    private LoginPresenter loginPresenter;
    private TestHelper testHelper;

    @Before
    public void setUp() {
        initMocks(this);
        testHelper = new TestHelper();
        loginPresenter = new LoginPresenter(testHelper.getAnyString());
        loginPresenter.setView(mockView);
    }

    @Test
    public void testNoRecipesCase() throws Exception {
        givenNoRecipes();
        givenValidCredentials();
        verify(mockView, never()).openRecipesView(anyString());
        verify(mockView).showError(anyInt());
    }

    @Test
    public void testIsValidPassword() throws Exception {

        String password = givenEmptyPassword();
        Assert.assertFalse(loginPresenter.isValidPassword(password));

        password = givenInvalidPassword();
        Assert.assertFalse(loginPresenter.isValidPassword(password));

        password = givenValidPassword();
        Assert.assertTrue(loginPresenter.isValidPassword(password));
    }

    @Test
    public void testOnEmailTextChanged() throws Exception {

        String email = givenNullEmail();
        loginPresenter.onEmailTextChanged(email);
        verify(mockView, never()).setErrorViewVisibility(false);
        verify(mockView, never()).setLoginButtonEnabled(true);

        email = givenEmptyEmail();
        loginPresenter.onEmailTextChanged(email);
        verify(mockView, never()).setErrorViewVisibility(false);
        verify(mockView, never()).setLoginButtonEnabled(true);

        email = givenNonEmptyEmail();
        loginPresenter.onEmailTextChanged(email);
        verify(mockView).setErrorViewVisibility(false);
        verify(mockView).setLoginButtonEnabled(true);
    }

    @Test
    public void testOnPasswordTextChanged() throws Exception {

        String password = givenEmptyPassword();
        loginPresenter.onPasswordTextChanged(password);
        verify(mockView, never()).setErrorViewVisibility(false);

        password = givenInvalidPassword();
        loginPresenter.onPasswordTextChanged(password);
        verify(mockView, never()).setErrorViewVisibility(false);

        password = givenValidPassword();
        loginPresenter.onPasswordTextChanged(password);
        verify(mockView).setErrorViewVisibility(false);
    }

    private String givenEmptyPassword() {
        return null;
    }

    private String givenInvalidPassword() {
        return "";
    }

    private String givenValidPassword() {
        return "password";
    }

    private String givenNullEmail() {
        return null;
    }

    private String givenEmptyEmail() {
        return "";
    }

    private String givenNonEmptyEmail() {
        return testHelper.getValidEmailId();
    }

    private void givenValidCredentials() {
        final String anyString = anyString();
        loginPresenter.onLoginClick(anyString, anyString);
    }

    private void givenNoRecipes() {
        loginPresenter.setRecipes(null);
    }
}
