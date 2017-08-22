package com.shiva.recipes.ui.component.recipesList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

import com.shiva.recipes.R;
import com.shiva.recipes.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.LayoutInflater.from;

/**
 * Created by shivananda.darura on 15/08/17.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;

    public RecipesAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext()).inflate(R.layout.list_item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_recipe)
        ImageView ivRecipe;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.iv_favorite)
        ImageButton ivFavorite;
        int position;

        RecipeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(final int position) {
            this.position = position;
            final Recipe recipe = recipeList.get(position);
            txtName.setText(recipe.getName());
            Picasso
                .with(ivRecipe.getContext())
                .load(recipe.getImage())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.ic_recipe)
                .fit()
                .into(ivRecipe);
            if (recipe.getFavorites() != null) {
                ivFavorite.setEnabled(true);
            }
            final Double rating = recipe.getRating();
            if (rating != null) {
                ratingBar.setRating((float) (rating%0.5));
            }

            ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                    if (!fromUser) {
                        return;
                    }

                    Recipe recipe = recipeList.get(position);
                    recipe.setRating((double) rating);
                }
            });
        }

        @OnClick({R.id.rl_favorite_container})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.rl_favorite_container:
                    toggleFavoriteStatus();
                    break;
            }
        }

        private void toggleFavoriteStatus() {
            final boolean enabled = ivFavorite.isEnabled();
            final Recipe recipe = recipeList.get(position);
            recipe.setFavorites(enabled ? null : 1);
            ivFavorite.setEnabled(!enabled);
        }
    }
}
