package com.shiva.recipes.ui.component.recipesList;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shiva.recipes.R;
import com.shiva.recipes.model.Recipe;
import com.shiva.recipes.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

public class RecipesActivity extends AppCompatActivity implements RecipesView {

    @BindView(R.id.rv_recipes)
    RecyclerView rvRecipes;
    @BindView(R.id.cl_parent)
    CoordinatorLayout clParent;
    private RecipesPresenter recipesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);
        initializePresenter(getIntent().getStringExtra(Constants.RECIPES));
        recipesPresenter.onCreated();
    }

    @Override
    public void renderRecipes(List<Recipe> recipeList) {
        RecipesAdapter recipesAdapter = new RecipesAdapter(recipeList);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvRecipes.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            rvRecipes.setLayoutManager(new GridLayoutManager(this, 4));
        }
        rvRecipes.setAdapter(recipesAdapter);
    }

    @Override
    public void showError(int errorMessageResourceId) {
        Snackbar.make(clParent, getString(errorMessageResourceId), LENGTH_LONG).show();
    }

    @Override
    public void setRecipesListVisibility(boolean visible) {
        rvRecipes.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void initializePresenter(String recipes) {
        recipesPresenter = new RecipesPresenter(recipes);
        recipesPresenter.setView(this);
    }
}
