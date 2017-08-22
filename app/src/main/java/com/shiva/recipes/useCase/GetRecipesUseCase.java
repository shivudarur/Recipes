package com.shiva.recipes.useCase;

import android.content.res.Resources;

import com.shiva.recipes.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.concurrent.Callable;

import io.reactivex.Single;

/**
 * Created by shivananda.darura on 12/08/17.
 */

public class GetRecipesUseCase {

    private final Resources resources;

    public GetRecipesUseCase(Resources resources) {
        this.resources = resources;
    }

    public Single<String> getRecipes() {
        return Single.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getRecipesList();
            }
        });
    }

    private String getRecipesList() throws IOException {

        InputStream is = resources.openRawResource(R.raw.recipes);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int length;
            while ((length = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, length);
            }
        } catch (IOException exception) {
            return "";
        } finally {
            is.close();
        }

        return writer.toString();
    }
}
