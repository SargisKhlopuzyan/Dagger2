package com.example.sargiskh.dagger2.advanced.module;

import android.app.Activity;
import android.content.Context;

import com.example.sargiskh.dagger2.advanced.annotation.ApplicationContext;
import com.example.sargiskh.dagger2.advanced.annotation.RandomUserApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Context context;

    ActivityModule(Activity context){
        this.context = context;
    }

//    @Named("activity_context")
//    @ActivityContext
    @ApplicationContext
    @RandomUserApplicationScope
    @Provides
    public Context context(){ return context; }

}
