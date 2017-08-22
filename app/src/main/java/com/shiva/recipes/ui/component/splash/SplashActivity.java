package com.shiva.recipes.ui.component.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.shiva.recipes.R;
import com.shiva.recipes.ui.component.login.LoginActivity;
import com.shiva.recipes.useCase.GetRecipesUseCase;
import com.shiva.recipes.utils.Constants;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    private Disposable recipesDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getRecipes();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recipesDisposable.dispose();
    }

    private void getRecipes() {
        GetRecipesUseCase getRecipesUseCase = new GetRecipesUseCase(getResources());
        getRecipesUseCase.getRecipes()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<String>() {
                @Override
                public void onSubscribe(@NonNull Disposable disposable) {
                    SplashActivity.this.recipesDisposable = disposable;
                }

                @Override
                public void onSuccess(@NonNull String recipes) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    intent.putExtra(Constants.RECIPES, recipes);
                    startActivity(intent);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.e(getClass().getName(), e.toString());
                }
            });
    }
}
