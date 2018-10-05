package com.example.sargiskh.dagger2.advanced.component;

import com.example.sargiskh.dagger2.advanced.interfaces.RandomUserApplicationScope;
import com.example.sargiskh.dagger2.advanced.interfaces.RandomUsersApi;
import com.example.sargiskh.dagger2.advanced.module.PicassoModule;
import com.example.sargiskh.dagger2.advanced.module.RandomUsersModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

@RandomUserApplicationScope
@Component(modules = {RandomUsersModule.class, PicassoModule.class})
public interface RandomUserComponent {

    RandomUsersApi getRandomUsersService();

    Picasso getPicasso();

}
