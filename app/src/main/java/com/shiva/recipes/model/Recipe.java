package com.shiva.recipes.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by shivananda.darura on 12/08/17.
 */

public class Recipe {
    private String calories;
    private String carbos;
    private String country;
    private List<String> deliverableIngredients = null;
    private String description;
    private Integer difficulty;
    private String fats;
    private Integer favorites;
    private String fibers;
    private String headline;
    private Boolean highlighted;
    private String id;
    private String image;
    private Object incompatibilities;
    private List<String> ingredients = null;
    private List<String> keywords = null;
    private String name;
    private List<String> products = null;
    private String proteins;
    private Double rating;
    private Integer ratings;
    private String thumb;
    private String time;
    private List<Object> undeliverableIngredients = null;
    private User user;
    private List<String> weeks = null;

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(@Nullable Double rating) {
        this.rating = rating;
    }

    @Nullable
    public Integer getFavorites() {
        return favorites;
    }

    public void setFavorites(@Nullable Integer favorites) {
        this.favorites = favorites;
    }
}
