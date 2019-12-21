package Basics;

import World.MapInitializer;

public class AnimalParams {
    public final int moveEnergy;
    public final int plantEnergy;
    public final int startEnergy;

    public AnimalParams(MapInitializer init){
        moveEnergy = init.getMoveEnergy();
        plantEnergy = init.getPlantEnergy();
        startEnergy = init.getStartEnergy();
    }
}
