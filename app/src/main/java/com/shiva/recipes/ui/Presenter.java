package com.shiva.recipes.ui;

/**
 * Created by shivananda.darura on 14/08/17.
 */

public class Presenter<T extends Presenter.View> {

    protected T view;

    public void setView(T view) {
        this.view = view;
    }

    public interface View {

    }
}
