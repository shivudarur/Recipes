package com.shiva.recipes;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.shiva.recipes.ui.component.login.LoginActivity;
import com.shiva.recipes.ui.component.recipesList.RecipesActivity;
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
import static org.hamcrest.Matchers.not;

/**
 * Created by shivananda.darura on 21/08/17.
 */

@RunWith(AndroidJUnit4.class)
public class RecipesScreenTest {

    private TestDataGenerator testDataGenerator;

    @Before
    public void setUp() {
        testDataGenerator = new TestDataGenerator();
    }

    @Rule
    public ActivityTestRule<RecipesActivity> activityTestRule = new ActivityTestRule<RecipesActivity>
        (RecipesActivity.class, false, false) {
        @Override
        protected Intent getActivityIntent() {
            Intent intent = super.getActivityIntent();
            intent.putExtra(Constants.RECIPES, testDataGenerator.getRecipes());
            return intent;
        }
    };

    @Test
    public void testValidRecipes() throws Exception {
        givenValidRecipes();
        onView(withId(R.id.rv_recipes)).check(matches(isDisplayed()));
    }

    @Test
    public void testInvalidRecipes() throws Exception {
        givenInvalidRecipes();
        onView(withId(R.id.rv_recipes)).check(matches(not((isDisplayed()))));
    }

    private RecipesActivity givenValidRecipes() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(context, RecipesActivity.class);
        intent.putExtra(Constants.RECIPES, testDataGenerator.getRecipes());
        return activityTestRule.launchActivity(intent);
    }

    private RecipesActivity givenInvalidRecipes() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(context, RecipesActivity.class);
        intent.putExtra(Constants.RECIPES, testDataGenerator.getAnyString());
        return activityTestRule.launchActivity(intent);
    }
}
