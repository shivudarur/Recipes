package com.shiva.recipes.ui.component.recipesList;

import android.support.annotation.VisibleForTesting;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.shiva.recipes.R;
import com.shiva.recipes.model.Recipe;
import com.shiva.recipes.ui.Presenter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shivananda.darura on 13/08/17.
 */

public class RecipesPresenter extends Presenter<RecipesView> {

    private String recipes;

    public RecipesPresenter(String recipes) {
        this.recipes = recipes;
    }

    public void onCreated() {
        renderUi();
    }

    public void renderUi() {
        view.setRecipesListVisibility(false);
        if (recipes == null || recipes.trim().isEmpty()) {
            view.showError(R.string.json_error);
            return;
        }

        final Recipe[] recipesArray;
        try {
            Gson gson = new Gson();
            recipesArray = gson.fromJson(this.recipes, Recipe[].class);
        } catch (JsonSyntaxException e) {
            view.showError(R.string.json_error);
            return;
        }

        final List<Recipe> recipeList = Arrays.asList(recipesArray);

        if (recipeList.isEmpty()) {
            view.showError(R.string.json_error);
        } else {
            view.setRecipesListVisibility(true);
            view.renderRecipes(recipeList);
        }
    }

    @VisibleForTesting
    public void setRecipes(String recipes) {
        this.recipes = recipes;
    }
}
