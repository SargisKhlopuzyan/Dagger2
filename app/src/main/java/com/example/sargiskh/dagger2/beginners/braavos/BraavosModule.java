package com.example.sargiskh.dagger2.beginners.braavos;

import dagger.Module;
import dagger.Provides;

@Module
public class BraavosModule {

    Cash cash;
    Soldiers soldiers;

    public BraavosModule(Cash cash, Soldiers soldiers) {
        this.cash = cash;
        this.soldiers = soldiers;
    }

    @Provides //Provides cash dependency
    Cash provideCash() {
        return cash;
    }

    @Provides //Provides soldiers dependency
    Soldiers provideSoldiers() {
        return soldiers;
    }

}
