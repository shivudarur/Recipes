package com.shiva.recipes.ui.component.recipeList;

import com.shiva.recipes.model.Recipe;
import com.shiva.recipes.ui.component.TestHelper;
import com.shiva.recipes.ui.component.recipesList.RecipesPresenter;
import com.shiva.recipes.ui.component.recipesList.RecipesView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by shivananda.darura on 21/08/17.
 */

public class RecipesPresenterTest {
    @Mock
    RecipesView mockView;

    private RecipesPresenter recipesPresenter;
    private TestHelper testHelper;

    @Before
    public void setUp() {
        initMocks(this);
        testHelper = new TestHelper();
        recipesPresenter = new RecipesPresenter(testHelper.getAnyString());
        recipesPresenter.setView(mockView);
    }

    @Test
    public void renderUiTestOnNullRecipes() throws Exception {
        givenNoRecipes();
        recipesPresenter.renderUi();
        verify(mockView).showError(anyInt());
        verify(mockView, never()).renderRecipes(ArgumentMatchers.<Recipe>anyList());
    }

    @Test
    public void renderUiTestOnInvalidRecipes() throws Exception {
        givenInvalidRecipes();
        recipesPresenter.renderUi();
        verify(mockView).showError(anyInt());
        verify(mockView, never()).renderRecipes(ArgumentMatchers.<Recipe>anyList());
    }

    @Test
    public void renderUiTestOnValidRecipes() throws Exception {
        givenValidRecipes();
        recipesPresenter.renderUi();
        verify(mockView, never()).showError(anyInt());
        verify(mockView).renderRecipes(ArgumentMatchers.<Recipe>anyList());
    }

    private void givenNoRecipes() {
        recipesPresenter.setRecipes(null);
    }

    private void givenInvalidRecipes() {
        recipesPresenter.setRecipes(testHelper.getAnyString());
    }

    private void givenValidRecipes() {
        recipesPresenter.setRecipes(testHelper.getRecipes());
    }

}
