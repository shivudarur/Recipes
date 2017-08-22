package com.shiva.recipes;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.shiva.recipes.ui.component.login.LoginActivity;
import com.shiva.recipes.utils.Constants;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by shivananda.darura on 21/08/17.
 */

@RunWith(AndroidJUnit4.class)
public class LoginScreenTest {

    private TestDataGenerator testDataGenerator;

    @Before
    public void setUp() {
        testDataGenerator = new TestDataGenerator();
    }

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<LoginActivity>
        (LoginActivity.class, false, false) {
        @Override
        protected Intent getActivityIntent() {
            Intent intent = super.getActivityIntent();
            intent.putExtra(Constants.RECIPES, testDataGenerator.getRecipes());
            return intent;
        }
    };

    @Test
    public void testLoginWithInvalidCredentials() throws Exception {
        startActivity();
        givenInvalidCredentials();
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.txt_error_message)).check(matches(isDisplayed()));
    }

    private LoginActivity startActivity() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.RECIPES, testDataGenerator.getRecipes());
        return activityTestRule.launchActivity(intent);
    }

    private void givenInvalidCredentials() {
        final String anyString = testDataGenerator.getAnyString();
        onView(withId(R.id.edit_email)).perform(typeText(anyString));
        onView(withId(R.id.edit_password)).perform(typeText(anyString));
    }
}
