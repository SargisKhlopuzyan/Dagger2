package com.example.sargiskh.dagger2.beginners;

import com.example.sargiskh.dagger2.beginners.houses.Boltons;
import com.example.sargiskh.dagger2.beginners.houses.Starks;

import javax.inject.Inject;

public class War {

    private Starks starks;
    private Boltons boltons;

    //DI - getting dependencies from else where via constructor
    @Inject
    public War(Starks starks, Boltons bolton){
        this.starks = starks;
        this.boltons = bolton;
    }

    public void prepare(){
        starks.prepareForWar();
        boltons.prepareForWar();
    }

    public void report(){
        starks.reportForWar();
        boltons.reportForWar();
    }

}