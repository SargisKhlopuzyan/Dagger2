package com.example.sargiskh.dagger2.advanced.MainActivityFeature;

import com.example.sargiskh.dagger2.MainActivity;
import com.example.sargiskh.dagger2.advanced.adapter.RandomUserAdapter;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public RandomUserAdapter randomUserAdapter(Picasso picasso) {
//        return new RandomUserAdapter(mainActivity, picasso);
        return new RandomUserAdapter(picasso);
    }

}
