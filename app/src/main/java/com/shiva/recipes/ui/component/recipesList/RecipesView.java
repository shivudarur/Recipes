package com.shiva.recipes.ui.component.recipesList;

import com.shiva.recipes.model.Recipe;
import com.shiva.recipes.ui.Presenter;

import java.util.List;

/**
 * Created by shivananda.darura on 13/08/17.
 */

public interface RecipesView extends Presenter.View {
    void renderRecipes(List<Recipe> recipeList);

    void showError(int errorMessageResourceId);

    void setRecipesListVisibility(boolean visible);
}
