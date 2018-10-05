package com.example.sargiskh.dagger2.advanced.module;

import android.content.Context;

import com.example.sargiskh.dagger2.advanced.interfaces.ActivityContext;
import com.example.sargiskh.dagger2.advanced.interfaces.ApplicationContext;
import com.example.sargiskh.dagger2.advanced.interfaces.RandomUserApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

//    @Named("application_context")
//    @ActivityContext
    @ApplicationContext
    @RandomUserApplicationScope
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }

}
