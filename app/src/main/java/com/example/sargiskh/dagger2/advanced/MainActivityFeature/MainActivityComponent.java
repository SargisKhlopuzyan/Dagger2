package com.example.sargiskh.dagger2.advanced.MainActivityFeature;

import com.example.sargiskh.dagger2.MainActivity;
import com.example.sargiskh.dagger2.advanced.adapter.RandomUserAdapter;
import com.example.sargiskh.dagger2.advanced.component.RandomUserComponent;
import com.example.sargiskh.dagger2.advanced.interfaces.RandomUsersApi;

import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = RandomUserComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

    /*
    RandomUserAdapter getRandomUserAdapter();

    RandomUsersApi getRandomUsersService();
    */

}
