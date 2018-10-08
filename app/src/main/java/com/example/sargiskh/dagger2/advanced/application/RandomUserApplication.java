package com.example.sargiskh.dagger2.advanced.application;

import android.app.Activity;
import android.app.Application;

import com.example.sargiskh.dagger2.advanced.component.DaggerRandomUserComponent;
import com.example.sargiskh.dagger2.advanced.component.RandomUserComponent;
import com.example.sargiskh.dagger2.advanced.module.ContextModule;

import timber.log.Timber;

public class RandomUserApplication extends Application {

    //add application name in Manifest file
    private RandomUserComponent randomUserComponent;

    public static RandomUserApplication get(Activity activity) {
        return (RandomUserApplication)activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        randomUserComponent = DaggerRandomUserComponent.builder().contextModule(new ContextModule(this)).build();
    }

    public RandomUserComponent getRandomUserComponent() {
        return randomUserComponent;
    }
}
