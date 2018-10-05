package com.example.sargiskh.dagger2.beginners;

import com.example.sargiskh.dagger2.beginners.braavos.BraavosModule;
import com.example.sargiskh.dagger2.beginners.braavos.Cash;
import com.example.sargiskh.dagger2.beginners.braavos.Soldiers;

import dagger.Component;

@Component(modules = BraavosModule.class)
interface BattleComponent {
    War getWar();
    Cash getCash();
    Soldiers getSoldiers();

    //adding more methods
//    Starks getStarks();
//    Boltons getBoltons();
}

public class BattleOfBastards {

    public static void mainFunction(){
        /*
        Starks starks = new Starks();
        Boltons boltons = new Boltons();

        War war = new War(starks, boltons);
        war.prepare();
        war.report();
        */

        //Using Dagger 2
//        BattleComponent component = DaggerBattleComponent.create();
        Cash cash = new Cash();
        Soldiers soldiers = new Soldiers();
        BattleComponent component = DaggerBattleComponent.builder().braavosModule(new BraavosModule(cash, soldiers)).build();
        War war = component.getWar();
        war.prepare();
        war.report();
        //using cash and soldiers
        component.getCash();
        component.getSoldiers();
    }

}
